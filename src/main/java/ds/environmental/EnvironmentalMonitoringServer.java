package ds.environmental;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class EnvironmentalMonitoringServer {
    private final int port = 50051;
    private final Server server;
    private JmDNS jmdns;

    public EnvironmentalMonitoringServer() {
        this.server = ServerBuilder.forPort(port)
                .addService(new EnvironmentalMonitoringImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Environmental Monitoring Server started on port " + port);
        registerService();

        //for shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down Environmental Monitoring Server...");
            try {
                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
    private void registerService() {
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());

            ServiceInfo serviceInfo = ServiceInfo.create(
                    "_envmonitor._tcp.local.",
                    "Environmental Monitoring Service",
                    port,
                    "gRPC Environmental Monitoring Service"
            );

            jmdns.registerService(serviceInfo);
            System.out.println("Service registered with jmDNS: _envmonitor._tcp.local.");

        } catch (IOException e) {
            System.err.println("Failed to register service with jmDNS: " + e.getMessage());
        }
    }

    public void stop() throws InterruptedException {
        if (server !=null) {
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
        if (server !=null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        EnvironmentalMonitoringServer server = new EnvironmentalMonitoringServer();
        server.start();
        server.blockUntilShutdown();
    }
}