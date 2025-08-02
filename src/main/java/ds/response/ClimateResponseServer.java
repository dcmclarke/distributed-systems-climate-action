package ds.response;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class ClimateResponseServer {
    private final int port = 50052;
    private final Server server;
    private JmDNS jmdns;

    public ClimateResponseServer() {
        this.server = ServerBuilder.forPort(port)
                .addService(new ClimateResponseImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Climate Response Server started on port " + port);

        registerService();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down Climate Response Server...");
            try {
                stop();
            } catch (Exception e){
                e.printStackTrace();
            }
        }));
    }

    private void registerService() {
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());

            ServiceInfo serviceInfo = ServiceInfo.create(
                    "_climateresponse._tcp.local.",
                    "Climate Response Service",
                    port,
                    "gRPC Climate Response Service"
            );

            jmdns.registerService(serviceInfo);
            System.out.println("Service registered with jmDNS: _climateresponse._tcp.local.");

        } catch (IOException e) {
            System.err.println("Failed register service with jmDNS: " + e.getMessage());
        }
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination();
        }
        if (jmdns != null) {
            jmdns.unregisterAllServices();
            try {
                jmdns.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ClimateResponseServer server = new ClimateResponseServer();
        server.start();
        server.blockUntilShutdown();
    }
}