package ds.analytics;

import io.grpc.stub.StreamObserver;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClimateAnalyticsImpl extends ClimateAnalyticsGrpc.ClimateAnalyticsImplBase {

    //recent data store for analysis
    private final Queue<AnalysisData> recentData = new ConcurrentLinkedQueue<>();
    private final Random random = new Random();

    @Override
    public void analyzeClimateData(AnalysisRequest request, StreamObserver<AnalysisResult> responseObserver) {
        System.out.println("Analyzing climate data for period: " + request.getTimePeriod() +
                " at location: " + request.getLocation());

        //simulate analysis calculations
        float temperatureTrend = -2.0f + random.nextFloat() * 4.0f; // -2 to +2 degree trend
        String pollutionLevel = determinePollutionLevel();
        List<String> recommendations = generateRecommendations(temperatureTrend, pollutionLevel);
        float efficiencyScore = 60.0f + random.nextFloat() * 40.0f; // 60-100% efficiency

        AnalysisResult result = AnalysisResult.newBuilder()
                .setTemperatureTrend(temperatureTrend)
                .setPollutionLevel(pollutionLevel)
                .addAllRecommendations(recommendations)
                .setEnergyEfficiencyScore(efficiencyScore)
                .build();

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<AnalysisData> realTimeAnalysis(StreamObserver<AnalysisResult> responseObserver) {
        return new StreamObserver<AnalysisData>() {
            private final List<AnalysisData> currentBatch = new ArrayList<>();

            @Override
            public void onNext(AnalysisData data) {
                currentBatch.add(data);
                recentData.offer(data);

                //keep last 100 data points
                while (recentData.size() > 100) {
                    recentData.poll();
                }

                System.out.println("Received data point: " + data.getTemperature() + "°C from " + data.getLocation());

                //analyse every 5 data points
                if (currentBatch.size() >= 5) {
                    AnalysisResult analysis = analyzeDataBatch(currentBatch);
                    responseObserver.onNext(analysis);
                    currentBatch.clear();
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in real-time analysis: " + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                //for analyse remaining data
                if (!currentBatch.isEmpty()) {
                    AnalysisResult finalAnalysis = analyzeDataBatch(currentBatch);
                    responseObserver.onNext(finalAnalysis);
                }

                System.out.println("Real-time analysis completed");
                responseObserver.onCompleted();
            }
        };
    }

    private AnalysisResult analyzeDataBatch(List<AnalysisData> batch) {
        float avgTemp = (float) batch.stream()
                .mapToDouble(AnalysisData::getTemperature)
                .average()
                .orElse(20.0);

        float trend = batch.size() > 1 ?
                batch.get(batch.size()-1).getTemperature() - batch.get(0).getTemperature() : 0.0f;
        int avgCo2 = (int) batch.stream()
                .mapToInt(AnalysisData::getCo2Level)
                .average()
                .orElse(400);

        String pollutionLevel = avgCo2 > 600 ? "high" : avgCo2 > 450 ? "medium" : "low";

        List<String> recommendations = generateRecommendations(trend, pollutionLevel);
        float efficiency = calculateEfficiencyScore(avgTemp, avgCo2);

        return AnalysisResult.newBuilder()
                .setTemperatureTrend(trend)
                .setPollutionLevel(pollutionLevel)
                .addAllRecommendations(recommendations)
                .setEnergyEfficiencyScore(efficiency)
                .build();
    }

    private String determinePollutionLevel() {
        String[] levels = {"low", "medium", "high"};
        return levels[random.nextInt(levels.length)];
    }

    private List<String> generateRecommendations(float temperatureTrend, String pollutionLevel) {
        List<String> recommendations = new ArrayList<>();

        if (temperatureTrend > 1.0f) {
            recommendations.add("Increase cooling system efficiency");
            recommendations.add("Consider solar shading for windows");
        } else if (temperatureTrend < -1.0f) {
            recommendations.add("Optimize heating system settings");
            recommendations.add("Check insulation effectiveness");
        }

        if ("high".equals(pollutionLevel)) {
            recommendations.add("Activate air purification systems");
            recommendations.add("Increase ventilation rate");
        } else if ("medium".equals(pollutionLevel)) {
            recommendations.add("Monitor air quality closely");
        }

        if (recommendations.isEmpty()) {
            recommendations.add("Current conditions are optimal");
        }

        return recommendations;
    }

    private float calculateEfficiencyScore(float temperature, int co2Level) {
        float baseScore = 80.0f;

        //temp efficiency (optimal around 22°C)
        float tempDiff = Math.abs(temperature - 22.0f);
        baseScore -= tempDiff * 2.0f;

        //c02 efficiency (lower better)
        if (co2Level > 600) baseScore -= 15.0f;
        else if (co2Level > 450) baseScore -= 5.0f;

        return Math.max(40.0f, Math.min(100.0f, baseScore));
    }
}