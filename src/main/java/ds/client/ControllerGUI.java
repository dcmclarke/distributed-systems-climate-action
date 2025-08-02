package ds.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import ds.environmental.*;
import ds.response.*;
import ds.analytics.*;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class ControllerGUI extends Application {

    //server instances to auto start without starting each server alone
    private EnvironmentalMonitoringServer envServer;
    private ClimateResponseServer responseServer;
    private ClimateAnalyticsServer analyticsServer;

    //gRPC channels, stubs
    private ManagedChannel envChannel;
    private ManagedChannel responseChannel;
    private ManagedChannel analyticsChannel;

    private EnvironmentalMonitoringGrpc.EnvironmentalMonitoringBlockingStub envStub;
    private EnvironmentalMonitoringGrpc.EnvironmentalMonitoringStub envAsyncStub;
    private ClimateResponseGrpc.ClimateResponseBlockingStub responseStub;
    private ClimateAnalyticsGrpc.ClimateAnalyticsBlockingStub analyticsStub;

    //UI components
    private TextArea logArea;
    private Label tempLabel, humidityLabel, co2Label, aqiLabel;
    private Button getSensorDataBtn, startStreamBtn, stopStreamBtn;
    private Button activateDeviceBtn, analyzeDataBtn;
    private TextField deviceIdField, intensityField, locationField;
    private ListView<String> discoveredServicesList;

    //service discovery
    private JmDNS jmdns;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Climate Monitoring System - Control Dashboard");

        startAllServers();
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        //start gRPC connections
        setupGrpcConnections();

        //start service discovery
        setupServiceDiscovery();

        //UI create
        VBox root = createUI();

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> shutdown());
    }

    //method to start all servers
    private void startAllServers() {
        System.out.println("Starting all servers...");

        try {
            // Start Environmental Monitoring Server
            envServer = new EnvironmentalMonitoringServer();
            new Thread(() -> {
                try {
                    envServer.start();
                    envServer.blockUntilShutdown();
                } catch (Exception e) {
                    System.err.println("Environmental server error: " + e.getMessage());
                }
            }).start();

            //first start climate response server
            responseServer = new ClimateResponseServer();
            new Thread(() -> {
                try {
                    responseServer.start();
                    responseServer.blockUntilShutdown();
                } catch (Exception e) {
                    System.err.println("Response server error: " + e.getMessage());
                }
            }).start();

            //start climate analytics server
            analyticsServer = new ClimateAnalyticsServer();
            new Thread(() -> {
                try {
                    analyticsServer.start();
                    analyticsServer.blockUntilShutdown();
                } catch (Exception e) {
                    System.err.println("Analytics server error: " + e.getMessage());
                }
            }).start();

            System.out.println("All servers starting in background...");

        } catch (Exception e) {
            System.err.println("Failed to start servers: " + e.getMessage());
        }
    }

    private VBox createUI() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        Label title = new Label("Smart Climate Monitoring System");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox discoverySection = createDiscoverySection();
        VBox sensorSection = createSensorSection();
        VBox deviceSection = createDeviceSection();
        VBox analyticsSection = createAnalyticsSection();

        //log area
        logArea = new TextArea();
        logArea.setPrefRowCount(8);
        logArea.setEditable(false);
        logArea.appendText("SMART CLIMATE SYSTEM STARTED:\n");
        logArea.appendText("All servers started automatically\n");

        root.getChildren().addAll(
                title,
                discoverySection,
                sensorSection,
                deviceSection,
                analyticsSection,
                new Label("System Log:"),
                logArea
        );

        return root;
    }

    private VBox createDiscoverySection() {
        VBox section = new VBox(5);
        section.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        section.setPadding(new Insets(10));

        Label sectionTitle = new Label("Discovered Services");
        sectionTitle.setStyle("-fx-font-weight: bold;");

        discoveredServicesList = new ListView<>();
        discoveredServicesList.setPrefHeight(80);

        section.getChildren().addAll(sectionTitle, discoveredServicesList);
        return section;
    }

    private VBox createSensorSection() {
        VBox section = new VBox(5);
        section.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        section.setPadding(new Insets(10));

        Label sectionTitle = new Label("Environmental Monitoring");
        sectionTitle.setStyle("-fx-font-weight: bold;");

        //location input
        HBox locationBox = new HBox(5);
        locationField = new TextField("room1");
        locationField.setPrefWidth(100);
        locationBox.getChildren().addAll(new Label("Location:"), locationField);

        //sensor rdings display
        GridPane readingsGrid = new GridPane();
        readingsGrid.setHgap(15);
        readingsGrid.setVgap(5);

        tempLabel = new Label("--°C");
        humidityLabel = new Label("--%");
        co2Label = new Label("-- ppm");
        aqiLabel = new Label("--");

        readingsGrid.add(new Label("Temperature:"), 0, 0);
        readingsGrid.add(tempLabel, 1, 0);
        readingsGrid.add(new Label("Humidity:"), 2, 0);
        readingsGrid.add(humidityLabel, 3, 0);
        readingsGrid.add(new Label("CO2:"), 0, 1);
        readingsGrid.add(co2Label, 1, 1);
        readingsGrid.add(new Label("Air Quality:"), 2, 1);
        readingsGrid.add(aqiLabel, 3, 1);

        //control buttons
        HBox buttonBox = new HBox(10);
        getSensorDataBtn = new Button("Get Current Data");
        startStreamBtn = new Button("Start Stream");
        stopStreamBtn = new Button("Stop Stream");
        stopStreamBtn.setDisable(true);

        getSensorDataBtn.setOnAction(e -> getCurrentSensorData());
        startStreamBtn.setOnAction(e -> startSensorStream());
        stopStreamBtn.setOnAction(e -> stopSensorStream());

        buttonBox.getChildren().addAll(getSensorDataBtn, startStreamBtn, stopStreamBtn);

        section.getChildren().addAll(sectionTitle, locationBox, readingsGrid, buttonBox);
        return section;
    }

    private VBox createDeviceSection() {
        VBox section = new VBox(5);
        section.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        section.setPadding(new Insets(10));

        Label sectionTitle = new Label("Device Control");
        sectionTitle.setStyle("-fx-font-weight: bold;");

        HBox controlBox = new HBox(10);
        deviceIdField = new TextField("airpurifier1");
        intensityField = new TextField("75");
        activateDeviceBtn = new Button("Activate Device");

        activateDeviceBtn.setOnAction(e -> activateDevice());

        controlBox.getChildren().addAll(
                new Label("Device ID:"), deviceIdField,
                new Label("Intensity:"), intensityField,
                activateDeviceBtn
        );

        section.getChildren().addAll(sectionTitle, controlBox);
        return section;
    }

    private VBox createAnalyticsSection() {
        VBox section = new VBox(5);
        section.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        section.setPadding(new Insets(10));

        Label sectionTitle = new Label("Climate Analytics");
        sectionTitle.setStyle("-fx-font-weight: bold;");

        analyzeDataBtn = new Button("Analyze Climate Data");
        analyzeDataBtn.setOnAction(e -> analyzeClimateData());

        section.getChildren().addAll(sectionTitle, analyzeDataBtn);
        return section;
    }

    private void setupGrpcConnections() {
        //environmental monitoring service
        envChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        envStub = EnvironmentalMonitoringGrpc.newBlockingStub(envChannel);
        envAsyncStub = EnvironmentalMonitoringGrpc.newStub(envChannel);

        //climate response service
        responseChannel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        responseStub = ClimateResponseGrpc.newBlockingStub(responseChannel);

        //climate analytics service
        analyticsChannel = ManagedChannelBuilder.forAddress("localhost", 50053)
                .usePlaintext()
                .build();
        analyticsStub = ClimateAnalyticsGrpc.newBlockingStub(analyticsChannel);

        logMessage("Connected to all gRPC services");
    }

    private void setupServiceDiscovery() {
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());

            //listen for environmental monitoring service
            jmdns.addServiceListener("_envmonitor._tcp.local.", new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    Platform.runLater(() -> {
                        discoveredServicesList.getItems().add("Environmental Monitoring - Added");
                        logMessage("Discovered: Environmental Monitoring Service");
                    });
                }

                @Override
                public void serviceRemoved(ServiceEvent event) {
                    Platform.runLater(() -> logMessage("Environmental Monitoring Service - Removed"));
                }

                @Override
                public void serviceResolved(ServiceEvent event) {
                    ServiceInfo info = event.getInfo();
                    Platform.runLater(() -> {
                        discoveredServicesList.getItems().add("Environmental: " + info.getHostAddress() + ":" + info.getPort());
                        logMessage("Resolved: Environmental Monitoring at " + info.getHostAddress() + ":" + info.getPort());
                    });
                }
            });

            jmdns.addServiceListener("_climateresponse._tcp.local.", createServiceListener("Climate Response"));
            jmdns.addServiceListener("_analytics._tcp.local.", createServiceListener("Climate Analytics"));

        } catch (IOException e) {
            logMessage("Failed to setup service discovery: " + e.getMessage());
        }
    }

    private ServiceListener createServiceListener(String serviceName) {
        return new ServiceListener() {
            @Override
            public void serviceAdded(ServiceEvent event) {
                Platform.runLater(() -> logMessage("Discovered: " + serviceName + " Service"));
            }

            @Override
            public void serviceRemoved(ServiceEvent event) {
                Platform.runLater(() -> logMessage(serviceName + " Service - Removed"));
            }

            @Override
            public void serviceResolved(ServiceEvent event) {
                ServiceInfo info = event.getInfo();
                Platform.runLater(() -> {
                    discoveredServicesList.getItems().add(serviceName + ": " + info.getHostAddress() + ":" + info.getPort());
                    logMessage("Resolved: " + serviceName + " at " + info.getHostAddress() + ":" + info.getPort());
                });
            }
        };
    }

    private void getCurrentSensorData() {
        try {
            SensorRequest request = SensorRequest.newBuilder()
                    .setLocation(locationField.getText())
                    .build();

            SensorData response = envStub.getCurrentReadings(request);
            updateSensorDisplay(response);
            logMessage("Retrieved current sensor data for " + locationField.getText());

        } catch (Exception e) {
            logMessage("Error getting sensor data: " + e.getMessage());
        }
    }

    private StreamObserver<SensorData> sensorStreamObserver;

    private void startSensorStream() {
        try {
            SensorRequest request = SensorRequest.newBuilder()
                    .setLocation(locationField.getText())
                    .build();

            sensorStreamObserver = new StreamObserver<SensorData>() {
                @Override
                public void onNext(SensorData data) {
                    Platform.runLater(() -> {
                        updateSensorDisplay(data);
                        logMessage("Streamed data: " + data.getTemperature() + "°C");
                    });
                }

                @Override
                public void onError(Throwable t) {
                    Platform.runLater(() -> {
                        logMessage("Stream error: " + t.getMessage());
                        startStreamBtn.setDisable(false);
                        stopStreamBtn.setDisable(true);
                    });
                }

                @Override
                public void onCompleted() {
                    Platform.runLater(() -> {
                        logMessage("Sensor stream completed");
                        startStreamBtn.setDisable(false);
                        stopStreamBtn.setDisable(true);
                    });
                }
            };

            envAsyncStub.streamSensorData(request, sensorStreamObserver);
            startStreamBtn.setDisable(true);
            stopStreamBtn.setDisable(false);
            logMessage("Started sensor data stream for " + locationField.getText());

        } catch (Exception e) {
            logMessage("Error starting stream: " + e.getMessage());
        }
    }

    private void stopSensorStream() {
        startStreamBtn.setDisable(false);
        stopStreamBtn.setDisable(true);
        logMessage("Stopped sensor stream");
    }

    private void activateDevice() {
        try {
            DeviceCommand command = DeviceCommand.newBuilder()
                    .setDeviceId(deviceIdField.getText())
                    .setIntensityLevel(Integer.parseInt(intensityField.getText()))
                    .setCommandType("activate")
                    .build();

            DeviceStatus response = responseStub.activateDevice(command);
            logMessage("Device activated: " + response.getDeviceId() +
                    " (Level: " + response.getCurrentLevel() + ")");

        } catch (Exception e) {
            logMessage("Error activating device: " + e.getMessage());
        }
    }

    private void analyzeClimateData() {
        try {
            AnalysisRequest request = AnalysisRequest.newBuilder()
                    .setTimePeriod("last_24_hours")
                    .addDataTypes("temperature")
                    .addDataTypes("co2")
                    .setLocation(locationField.getText())
                    .build();

            AnalysisResult response = analyticsStub.analyzeClimateData(request);

            logMessage("=== CLIMATE ANALYSIS RESULTS ===");
            logMessage("Temperature Trend: " + response.getTemperatureTrend() + "°C");
            logMessage("Pollution Level: " + response.getPollutionLevel());
            logMessage("Energy Efficiency: " + response.getEnergyEfficiencyScore() + "%");
            logMessage("Recommendations:");
            for (String rec : response.getRecommendationsList()) {
                logMessage("  - " + rec);
            }
            logMessage("================================");

        } catch (Exception e) {
            logMessage("Error analyzing data: " + e.getMessage());
        }
    }

    private void updateSensorDisplay(SensorData data) {
        tempLabel.setText(String.format("%.1f°C", data.getTemperature()));
        humidityLabel.setText(String.format("%.1f%%", data.getHumidity()));
        co2Label.setText(data.getCo2Level() + " ppm");
        aqiLabel.setText(String.valueOf(data.getAirQualityIndex()));
    }

    private void logMessage(String message) {
        Platform.runLater(() -> {
            logArea.appendText("[" + java.time.LocalTime.now().toString().substring(0, 8) + "] " + message + "\n");
            logArea.setScrollTop(Double.MAX_VALUE);
        });
    }

    //shutdown to stop servers
    private void shutdown() {
        try {
            if (envChannel != null) envChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            if (responseChannel != null) responseChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            if (analyticsChannel != null) analyticsChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            if (jmdns != null) jmdns.close();

            //stop servers
            if (envServer != null) envServer.stop();
            if (responseServer != null) responseServer.stop();
            if (analyticsServer != null) analyticsServer.stop();

            System.out.println("All services shut down gracefully");

        } catch (Exception e) {
            System.err.println("Error during shutdown: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting Smart Climate Monitoring System...");
        launch(args);
    }
}