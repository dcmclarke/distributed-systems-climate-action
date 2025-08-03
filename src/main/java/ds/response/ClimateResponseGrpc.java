package ds.response;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: src/main/resources/climate_response.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClimateResponseGrpc {

  private ClimateResponseGrpc() {}

  public static final String SERVICE_NAME = "response.ClimateResponse";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.response.DeviceCommand,
      ds.response.DeviceStatus> getActivateDeviceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActivateDevice",
      requestType = ds.response.DeviceCommand.class,
      responseType = ds.response.DeviceStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.response.DeviceCommand,
      ds.response.DeviceStatus> getActivateDeviceMethod() {
    io.grpc.MethodDescriptor<ds.response.DeviceCommand, ds.response.DeviceStatus> getActivateDeviceMethod;
    if ((getActivateDeviceMethod = ClimateResponseGrpc.getActivateDeviceMethod) == null) {
      synchronized (ClimateResponseGrpc.class) {
        if ((getActivateDeviceMethod = ClimateResponseGrpc.getActivateDeviceMethod) == null) {
          ClimateResponseGrpc.getActivateDeviceMethod = getActivateDeviceMethod =
              io.grpc.MethodDescriptor.<ds.response.DeviceCommand, ds.response.DeviceStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActivateDevice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.response.DeviceCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.response.DeviceStatus.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateResponseMethodDescriptorSupplier("ActivateDevice"))
              .build();
        }
      }
    }
    return getActivateDeviceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ds.response.DeviceCommand,
      ds.response.BatchCommandResponse> getProcessDeviceCommandsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProcessDeviceCommands",
      requestType = ds.response.DeviceCommand.class,
      responseType = ds.response.BatchCommandResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<ds.response.DeviceCommand,
      ds.response.BatchCommandResponse> getProcessDeviceCommandsMethod() {
    io.grpc.MethodDescriptor<ds.response.DeviceCommand, ds.response.BatchCommandResponse> getProcessDeviceCommandsMethod;
    if ((getProcessDeviceCommandsMethod = ClimateResponseGrpc.getProcessDeviceCommandsMethod) == null) {
      synchronized (ClimateResponseGrpc.class) {
        if ((getProcessDeviceCommandsMethod = ClimateResponseGrpc.getProcessDeviceCommandsMethod) == null) {
          ClimateResponseGrpc.getProcessDeviceCommandsMethod = getProcessDeviceCommandsMethod =
              io.grpc.MethodDescriptor.<ds.response.DeviceCommand, ds.response.BatchCommandResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProcessDeviceCommands"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.response.DeviceCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.response.BatchCommandResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateResponseMethodDescriptorSupplier("ProcessDeviceCommands"))
              .build();
        }
      }
    }
    return getProcessDeviceCommandsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClimateResponseStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateResponseStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateResponseStub>() {
        @java.lang.Override
        public ClimateResponseStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateResponseStub(channel, callOptions);
        }
      };
    return ClimateResponseStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClimateResponseBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateResponseBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateResponseBlockingStub>() {
        @java.lang.Override
        public ClimateResponseBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateResponseBlockingStub(channel, callOptions);
        }
      };
    return ClimateResponseBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClimateResponseFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateResponseFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateResponseFutureStub>() {
        @java.lang.Override
        public ClimateResponseFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateResponseFutureStub(channel, callOptions);
        }
      };
    return ClimateResponseFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ClimateResponseImplBase implements io.grpc.BindableService {

    /**
     */
    public void activateDevice(ds.response.DeviceCommand request,
        io.grpc.stub.StreamObserver<ds.response.DeviceStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getActivateDeviceMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ds.response.DeviceCommand> processDeviceCommands(
        io.grpc.stub.StreamObserver<ds.response.BatchCommandResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getProcessDeviceCommandsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getActivateDeviceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.response.DeviceCommand,
                ds.response.DeviceStatus>(
                  this, METHODID_ACTIVATE_DEVICE)))
          .addMethod(
            getProcessDeviceCommandsMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                ds.response.DeviceCommand,
                ds.response.BatchCommandResponse>(
                  this, METHODID_PROCESS_DEVICE_COMMANDS)))
          .build();
    }
  }

  /**
   */
  public static final class ClimateResponseStub extends io.grpc.stub.AbstractAsyncStub<ClimateResponseStub> {
    private ClimateResponseStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateResponseStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateResponseStub(channel, callOptions);
    }

    /**
     */
    public void activateDevice(ds.response.DeviceCommand request,
        io.grpc.stub.StreamObserver<ds.response.DeviceStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getActivateDeviceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ds.response.DeviceCommand> processDeviceCommands(
        io.grpc.stub.StreamObserver<ds.response.BatchCommandResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getProcessDeviceCommandsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ClimateResponseBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClimateResponseBlockingStub> {
    private ClimateResponseBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateResponseBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateResponseBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.response.DeviceStatus activateDevice(ds.response.DeviceCommand request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getActivateDeviceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClimateResponseFutureStub extends io.grpc.stub.AbstractFutureStub<ClimateResponseFutureStub> {
    private ClimateResponseFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateResponseFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateResponseFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.response.DeviceStatus> activateDevice(
        ds.response.DeviceCommand request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getActivateDeviceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACTIVATE_DEVICE = 0;
  private static final int METHODID_PROCESS_DEVICE_COMMANDS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClimateResponseImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClimateResponseImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ACTIVATE_DEVICE:
          serviceImpl.activateDevice((ds.response.DeviceCommand) request,
              (io.grpc.stub.StreamObserver<ds.response.DeviceStatus>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PROCESS_DEVICE_COMMANDS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.processDeviceCommands(
              (io.grpc.stub.StreamObserver<ds.response.BatchCommandResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ClimateResponseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClimateResponseBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.response.ClimateResponseOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClimateResponse");
    }
  }

  private static final class ClimateResponseFileDescriptorSupplier
      extends ClimateResponseBaseDescriptorSupplier {
    ClimateResponseFileDescriptorSupplier() {}
  }

  private static final class ClimateResponseMethodDescriptorSupplier
      extends ClimateResponseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClimateResponseMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ClimateResponseGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClimateResponseFileDescriptorSupplier())
              .addMethod(getActivateDeviceMethod())
              .addMethod(getProcessDeviceCommandsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
