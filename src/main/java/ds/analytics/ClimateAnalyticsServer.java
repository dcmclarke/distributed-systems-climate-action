package ds.analytics;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class ClimateAnalyticsServer {
    private final int port = 50053;
    private final Server server;
    private JmDNS jmdns;

    public ClimateAnalyticsServer() {
        this.server = ServerBuilder.forPort(port)
                .addService(new ClimateAnalyticsImpl())
                .build();
    }
    public void start() throws IOException {
        server.start();
        System.out.println("Climate Analytics Server started on port " + port);

        registerService();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down Climate Analytics Server...");
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
                    "_analytics._tcp.local.",
                    "Climate Analytics Service",
                    port,
                    "gRPC Climate Analytics Service"
            );

            jmdns.registerService(serviceInfo);
            System.out.println("Service registered with jmDNS: _analytics._tcp.local.");

        } catch (IOException e) {
            System.err.println("Failed to register service with jmDNS: " + e.getMessage());
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
        ClimateAnalyticsServer server = new ClimateAnalyticsServer();
        server.start();
        server.blockUntilShutdown();
    }
}