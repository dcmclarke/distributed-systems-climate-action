package ds.response;

import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ClimateResponseImpl extends ClimateResponseGrpc.ClimateResponseImplBase {

    //to simulate device states
    private final Map<String, DeviceStatus> deviceStates = new ConcurrentHashMap<>();

    @Override
    public void activateDevice(DeviceCommand request, StreamObserver<DeviceStatus> responseObserver) {
        System.out.println("Activating device: " + request.getDeviceId() +
                " with intensity: " + request.getIntensityLevel());

        //then create or update device status
        DeviceStatus status = DeviceStatus.newBuilder()
                .setDeviceId(request.getDeviceId())
                .setIsActive(true)
                .setCurrentLevel(request.getIntensityLevel())
                .setStatusMessage("Device activated successfully")
                .build();

        //store device state
        deviceStates.put(request.getDeviceId(), status);

        responseObserver.onNext(status);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DeviceCommand> processDeviceCommands(StreamObserver<BatchCommandResponse> responseObserver) {
        return new StreamObserver<DeviceCommand>() {
            private final List<DeviceStatus> processedDevices = new ArrayList<>();
            private int commandCount = 0;

            @Override
            public void onNext(DeviceCommand command) {
                commandCount++;
                System.out.println("Processing command " + commandCount +
                        " for device: " + command.getDeviceId());
                boolean isActive = "activate".equals(command.getCommandType());
                int level = isActive ? command.getIntensityLevel() : 0;

                DeviceStatus status = DeviceStatus.newBuilder()
                        .setDeviceId(command.getDeviceId())
                        .setIsActive(isActive)
                        .setCurrentLevel(level)
                        .setStatusMessage("Command processed: " + command.getCommandType())
                        .build();

                deviceStates.put(command.getDeviceId(), status);
                processedDevices.add(status);
            }

            @Override
            public void onError(Throwable t){
                System.err.println("Error processing device commands: " + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                BatchCommandResponse response = BatchCommandResponse.newBuilder()
                        .setCommandsProcessed(commandCount)
                        .setOverallStatus("All commands processed successfully")
                        .addAllDeviceStatuses(processedDevices)
                        .build();

                System.out.println("Completed processing " + commandCount + " commands");
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}