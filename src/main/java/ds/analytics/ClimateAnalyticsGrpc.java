package ds.analytics;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: src/main/resources/climate_analytics.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClimateAnalyticsGrpc {

  private ClimateAnalyticsGrpc() {}

  public static final String SERVICE_NAME = "analytics.ClimateAnalytics";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.analytics.AnalysisRequest,
      ds.analytics.AnalysisResult> getAnalyzeClimateDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnalyzeClimateData",
      requestType = ds.analytics.AnalysisRequest.class,
      responseType = ds.analytics.AnalysisResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.analytics.AnalysisRequest,
      ds.analytics.AnalysisResult> getAnalyzeClimateDataMethod() {
    io.grpc.MethodDescriptor<ds.analytics.AnalysisRequest, ds.analytics.AnalysisResult> getAnalyzeClimateDataMethod;
    if ((getAnalyzeClimateDataMethod = ClimateAnalyticsGrpc.getAnalyzeClimateDataMethod) == null) {
      synchronized (ClimateAnalyticsGrpc.class) {
        if ((getAnalyzeClimateDataMethod = ClimateAnalyticsGrpc.getAnalyzeClimateDataMethod) == null) {
          ClimateAnalyticsGrpc.getAnalyzeClimateDataMethod = getAnalyzeClimateDataMethod =
              io.grpc.MethodDescriptor.<ds.analytics.AnalysisRequest, ds.analytics.AnalysisResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnalyzeClimateData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.analytics.AnalysisRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.analytics.AnalysisResult.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateAnalyticsMethodDescriptorSupplier("AnalyzeClimateData"))
              .build();
        }
      }
    }
    return getAnalyzeClimateDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ds.analytics.AnalysisData,
      ds.analytics.AnalysisResult> getRealTimeAnalysisMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RealTimeAnalysis",
      requestType = ds.analytics.AnalysisData.class,
      responseType = ds.analytics.AnalysisResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<ds.analytics.AnalysisData,
      ds.analytics.AnalysisResult> getRealTimeAnalysisMethod() {
    io.grpc.MethodDescriptor<ds.analytics.AnalysisData, ds.analytics.AnalysisResult> getRealTimeAnalysisMethod;
    if ((getRealTimeAnalysisMethod = ClimateAnalyticsGrpc.getRealTimeAnalysisMethod) == null) {
      synchronized (ClimateAnalyticsGrpc.class) {
        if ((getRealTimeAnalysisMethod = ClimateAnalyticsGrpc.getRealTimeAnalysisMethod) == null) {
          ClimateAnalyticsGrpc.getRealTimeAnalysisMethod = getRealTimeAnalysisMethod =
              io.grpc.MethodDescriptor.<ds.analytics.AnalysisData, ds.analytics.AnalysisResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RealTimeAnalysis"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.analytics.AnalysisData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.analytics.AnalysisResult.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateAnalyticsMethodDescriptorSupplier("RealTimeAnalysis"))
              .build();
        }
      }
    }
    return getRealTimeAnalysisMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClimateAnalyticsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateAnalyticsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateAnalyticsStub>() {
        @java.lang.Override
        public ClimateAnalyticsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateAnalyticsStub(channel, callOptions);
        }
      };
    return ClimateAnalyticsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClimateAnalyticsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateAnalyticsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateAnalyticsBlockingStub>() {
        @java.lang.Override
        public ClimateAnalyticsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateAnalyticsBlockingStub(channel, callOptions);
        }
      };
    return ClimateAnalyticsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClimateAnalyticsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateAnalyticsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateAnalyticsFutureStub>() {
        @java.lang.Override
        public ClimateAnalyticsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateAnalyticsFutureStub(channel, callOptions);
        }
      };
    return ClimateAnalyticsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ClimateAnalyticsImplBase implements io.grpc.BindableService {

    /**
     */
    public void analyzeClimateData(ds.analytics.AnalysisRequest request,
        io.grpc.stub.StreamObserver<ds.analytics.AnalysisResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnalyzeClimateDataMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ds.analytics.AnalysisData> realTimeAnalysis(
        io.grpc.stub.StreamObserver<ds.analytics.AnalysisResult> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getRealTimeAnalysisMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAnalyzeClimateDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.analytics.AnalysisRequest,
                ds.analytics.AnalysisResult>(
                  this, METHODID_ANALYZE_CLIMATE_DATA)))
          .addMethod(
            getRealTimeAnalysisMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                ds.analytics.AnalysisData,
                ds.analytics.AnalysisResult>(
                  this, METHODID_REAL_TIME_ANALYSIS)))
          .build();
    }
  }

  /**
   */
  public static final class ClimateAnalyticsStub extends io.grpc.stub.AbstractAsyncStub<ClimateAnalyticsStub> {
    private ClimateAnalyticsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateAnalyticsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateAnalyticsStub(channel, callOptions);
    }

    /**
     */
    public void analyzeClimateData(ds.analytics.AnalysisRequest request,
        io.grpc.stub.StreamObserver<ds.analytics.AnalysisResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnalyzeClimateDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ds.analytics.AnalysisData> realTimeAnalysis(
        io.grpc.stub.StreamObserver<ds.analytics.AnalysisResult> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getRealTimeAnalysisMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ClimateAnalyticsBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClimateAnalyticsBlockingStub> {
    private ClimateAnalyticsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateAnalyticsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateAnalyticsBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.analytics.AnalysisResult analyzeClimateData(ds.analytics.AnalysisRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnalyzeClimateDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClimateAnalyticsFutureStub extends io.grpc.stub.AbstractFutureStub<ClimateAnalyticsFutureStub> {
    private ClimateAnalyticsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateAnalyticsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateAnalyticsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.analytics.AnalysisResult> analyzeClimateData(
        ds.analytics.AnalysisRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnalyzeClimateDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ANALYZE_CLIMATE_DATA = 0;
  private static final int METHODID_REAL_TIME_ANALYSIS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClimateAnalyticsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClimateAnalyticsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ANALYZE_CLIMATE_DATA:
          serviceImpl.analyzeClimateData((ds.analytics.AnalysisRequest) request,
              (io.grpc.stub.StreamObserver<ds.analytics.AnalysisResult>) responseObserver);
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
        case METHODID_REAL_TIME_ANALYSIS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.realTimeAnalysis(
              (io.grpc.stub.StreamObserver<ds.analytics.AnalysisResult>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ClimateAnalyticsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClimateAnalyticsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.analytics.ClimateAnalyticsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClimateAnalytics");
    }
  }

  private static final class ClimateAnalyticsFileDescriptorSupplier
      extends ClimateAnalyticsBaseDescriptorSupplier {
    ClimateAnalyticsFileDescriptorSupplier() {}
  }

  private static final class ClimateAnalyticsMethodDescriptorSupplier
      extends ClimateAnalyticsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClimateAnalyticsMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClimateAnalyticsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClimateAnalyticsFileDescriptorSupplier())
              .addMethod(getAnalyzeClimateDataMethod())
              .addMethod(getRealTimeAnalysisMethod())
              .build();
        }
      }
    }
    return result;
  }
}
