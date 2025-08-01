package ds.environmental;

import io.grpc.stub.StreamObserver;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnvironmentalMonitoringImpl extends EnvironmentalMonitoringGrpc.EnvironmentalMonitoringImplBase {

    private final Random random = new Random();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    @Override
    public void getCurrentReadings(SensorRequest request, StreamObserver<SensorData> responseObserver) {
        System.out.println("Getting current readings for location: " + request.getLocation());

        //fake sensor data
        SensorData data = SensorData.newBuilder()
                .setTemperature(18.0f + random.nextFloat() * 10.0f) // 18-28°C
                .setHumidity(40.0f + random.nextFloat() * 40.0f)    // 40-80%
                .setCo2Level(300 + random.nextInt(500))             // 300-800 ppm
                .setAirQualityIndex(20 + random.nextInt(80))        // 20-100 AQI
                .setTimestamp(System.currentTimeMillis())
                .build();

        responseObserver.onNext(data);
        responseObserver.onCompleted();
    }

    @Override
    public void streamSensorData(SensorRequest request, StreamObserver<SensorData> responseObserver) {
        System.out.println("Starting sensor data stream for location: " + request.getLocation());

        //stream sensor data every 2secs
        scheduler.scheduleAtFixedRate(() -> {
            try {
                SensorData data = SensorData.newBuilder()
                        .setTemperature(18.0f + random.nextFloat() * 10.0f)
                        .setHumidity(40.0f + random.nextFloat() * 40.0f)
                        .setCo2Level(300 + random.nextInt(500))
                        .setAirQualityIndex(20 + random.nextInt(80))
                        .setTimestamp(System.currentTimeMillis())
                        .build();

                responseObserver.onNext(data);
                System.out.println("Streamed data: " + data.getTemperature() + "°C");

            } catch (Exception e) {
                responseObserver.onError(e);
            }
        }, 0, 2, TimeUnit.SECONDS);

        //stop stream after 30secs
        scheduler.schedule(() -> {
            responseObserver.onCompleted();
            System.out.println("Stream completed for " + request.getLocation());
        }, 30, TimeUnit.SECONDS);
    }
}