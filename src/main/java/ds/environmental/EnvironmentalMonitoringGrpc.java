package ds.environmental;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: src/main/resources/environmental_monitoring.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EnvironmentalMonitoringGrpc {

  private EnvironmentalMonitoringGrpc() {}

  public static final String SERVICE_NAME = "environmental.EnvironmentalMonitoring";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.environmental.SensorRequest,
      ds.environmental.SensorData> getGetCurrentReadingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCurrentReadings",
      requestType = ds.environmental.SensorRequest.class,
      responseType = ds.environmental.SensorData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.environmental.SensorRequest,
      ds.environmental.SensorData> getGetCurrentReadingsMethod() {
    io.grpc.MethodDescriptor<ds.environmental.SensorRequest, ds.environmental.SensorData> getGetCurrentReadingsMethod;
    if ((getGetCurrentReadingsMethod = EnvironmentalMonitoringGrpc.getGetCurrentReadingsMethod) == null) {
      synchronized (EnvironmentalMonitoringGrpc.class) {
        if ((getGetCurrentReadingsMethod = EnvironmentalMonitoringGrpc.getGetCurrentReadingsMethod) == null) {
          EnvironmentalMonitoringGrpc.getGetCurrentReadingsMethod = getGetCurrentReadingsMethod =
              io.grpc.MethodDescriptor.<ds.environmental.SensorRequest, ds.environmental.SensorData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCurrentReadings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.environmental.SensorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.environmental.SensorData.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentalMonitoringMethodDescriptorSupplier("GetCurrentReadings"))
              .build();
        }
      }
    }
    return getGetCurrentReadingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ds.environmental.SensorRequest,
      ds.environmental.SensorData> getStreamSensorDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamSensorData",
      requestType = ds.environmental.SensorRequest.class,
      responseType = ds.environmental.SensorData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ds.environmental.SensorRequest,
      ds.environmental.SensorData> getStreamSensorDataMethod() {
    io.grpc.MethodDescriptor<ds.environmental.SensorRequest, ds.environmental.SensorData> getStreamSensorDataMethod;
    if ((getStreamSensorDataMethod = EnvironmentalMonitoringGrpc.getStreamSensorDataMethod) == null) {
      synchronized (EnvironmentalMonitoringGrpc.class) {
        if ((getStreamSensorDataMethod = EnvironmentalMonitoringGrpc.getStreamSensorDataMethod) == null) {
          EnvironmentalMonitoringGrpc.getStreamSensorDataMethod = getStreamSensorDataMethod =
              io.grpc.MethodDescriptor.<ds.environmental.SensorRequest, ds.environmental.SensorData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamSensorData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.environmental.SensorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.environmental.SensorData.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentalMonitoringMethodDescriptorSupplier("StreamSensorData"))
              .build();
        }
      }
    }
    return getStreamSensorDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnvironmentalMonitoringStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnvironmentalMonitoringStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnvironmentalMonitoringStub>() {
        @java.lang.Override
        public EnvironmentalMonitoringStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnvironmentalMonitoringStub(channel, callOptions);
        }
      };
    return EnvironmentalMonitoringStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnvironmentalMonitoringBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnvironmentalMonitoringBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnvironmentalMonitoringBlockingStub>() {
        @java.lang.Override
        public EnvironmentalMonitoringBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnvironmentalMonitoringBlockingStub(channel, callOptions);
        }
      };
    return EnvironmentalMonitoringBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EnvironmentalMonitoringFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnvironmentalMonitoringFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnvironmentalMonitoringFutureStub>() {
        @java.lang.Override
        public EnvironmentalMonitoringFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnvironmentalMonitoringFutureStub(channel, callOptions);
        }
      };
    return EnvironmentalMonitoringFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class EnvironmentalMonitoringImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCurrentReadings(ds.environmental.SensorRequest request,
        io.grpc.stub.StreamObserver<ds.environmental.SensorData> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCurrentReadingsMethod(), responseObserver);
    }

    /**
     */
    public void streamSensorData(ds.environmental.SensorRequest request,
        io.grpc.stub.StreamObserver<ds.environmental.SensorData> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamSensorDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCurrentReadingsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.environmental.SensorRequest,
                ds.environmental.SensorData>(
                  this, METHODID_GET_CURRENT_READINGS)))
          .addMethod(
            getStreamSensorDataMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                ds.environmental.SensorRequest,
                ds.environmental.SensorData>(
                  this, METHODID_STREAM_SENSOR_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class EnvironmentalMonitoringStub extends io.grpc.stub.AbstractAsyncStub<EnvironmentalMonitoringStub> {
    private EnvironmentalMonitoringStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnvironmentalMonitoringStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnvironmentalMonitoringStub(channel, callOptions);
    }

    /**
     */
    public void getCurrentReadings(ds.environmental.SensorRequest request,
        io.grpc.stub.StreamObserver<ds.environmental.SensorData> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCurrentReadingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamSensorData(ds.environmental.SensorRequest request,
        io.grpc.stub.StreamObserver<ds.environmental.SensorData> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamSensorDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnvironmentalMonitoringBlockingStub extends io.grpc.stub.AbstractBlockingStub<EnvironmentalMonitoringBlockingStub> {
    private EnvironmentalMonitoringBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnvironmentalMonitoringBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnvironmentalMonitoringBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.environmental.SensorData getCurrentReadings(ds.environmental.SensorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCurrentReadingsMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<ds.environmental.SensorData> streamSensorData(
        ds.environmental.SensorRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamSensorDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnvironmentalMonitoringFutureStub extends io.grpc.stub.AbstractFutureStub<EnvironmentalMonitoringFutureStub> {
    private EnvironmentalMonitoringFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnvironmentalMonitoringFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnvironmentalMonitoringFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.environmental.SensorData> getCurrentReadings(
        ds.environmental.SensorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCurrentReadingsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CURRENT_READINGS = 0;
  private static final int METHODID_STREAM_SENSOR_DATA = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnvironmentalMonitoringImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EnvironmentalMonitoringImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CURRENT_READINGS:
          serviceImpl.getCurrentReadings((ds.environmental.SensorRequest) request,
              (io.grpc.stub.StreamObserver<ds.environmental.SensorData>) responseObserver);
          break;
        case METHODID_STREAM_SENSOR_DATA:
          serviceImpl.streamSensorData((ds.environmental.SensorRequest) request,
              (io.grpc.stub.StreamObserver<ds.environmental.SensorData>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EnvironmentalMonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EnvironmentalMonitoringBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.environmental.EnvironmentalMonitoringOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EnvironmentalMonitoring");
    }
  }

  private static final class EnvironmentalMonitoringFileDescriptorSupplier
      extends EnvironmentalMonitoringBaseDescriptorSupplier {
    EnvironmentalMonitoringFileDescriptorSupplier() {}
  }

  private static final class EnvironmentalMonitoringMethodDescriptorSupplier
      extends EnvironmentalMonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EnvironmentalMonitoringMethodDescriptorSupplier(String methodName) {
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
      synchronized (EnvironmentalMonitoringGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EnvironmentalMonitoringFileDescriptorSupplier())
              .addMethod(getGetCurrentReadingsMethod())
              .addMethod(getStreamSensorDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
