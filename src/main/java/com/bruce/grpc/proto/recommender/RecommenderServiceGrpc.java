package com.bruce.grpc.proto.recommender;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.34.1)",
    comments = "Source: recommender/recommender.proto")
public final class RecommenderServiceGrpc {

  private RecommenderServiceGrpc() {}

  public static final String SERVICE_NAME = "recommender.RecommenderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<RecommenderRequest,
          RecommenderResponse> getGetRecommendedMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRecommendedMovie",
      requestType = RecommenderRequest.class,
      responseType = RecommenderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<RecommenderRequest,
          RecommenderResponse> getGetRecommendedMovieMethod() {
    io.grpc.MethodDescriptor<RecommenderRequest, RecommenderResponse> getGetRecommendedMovieMethod;
    if ((getGetRecommendedMovieMethod = RecommenderServiceGrpc.getGetRecommendedMovieMethod) == null) {
      synchronized (RecommenderServiceGrpc.class) {
        if ((getGetRecommendedMovieMethod = RecommenderServiceGrpc.getGetRecommendedMovieMethod) == null) {
          RecommenderServiceGrpc.getGetRecommendedMovieMethod = getGetRecommendedMovieMethod =
              io.grpc.MethodDescriptor.<RecommenderRequest, RecommenderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getRecommendedMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RecommenderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RecommenderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RecommenderServiceMethodDescriptorSupplier("getRecommendedMovie"))
              .build();
        }
      }
    }
    return getGetRecommendedMovieMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RecommenderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecommenderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecommenderServiceStub>() {
        @java.lang.Override
        public RecommenderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecommenderServiceStub(channel, callOptions);
        }
      };
    return RecommenderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RecommenderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecommenderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecommenderServiceBlockingStub>() {
        @java.lang.Override
        public RecommenderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecommenderServiceBlockingStub(channel, callOptions);
        }
      };
    return RecommenderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RecommenderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecommenderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecommenderServiceFutureStub>() {
        @java.lang.Override
        public RecommenderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecommenderServiceFutureStub(channel, callOptions);
        }
      };
    return RecommenderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RecommenderServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * client streaming request that receives a stream of movies and recommends one
     * </pre>
     */
    public io.grpc.stub.StreamObserver<RecommenderRequest> getRecommendedMovie(
        io.grpc.stub.StreamObserver<RecommenderResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetRecommendedMovieMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRecommendedMovieMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                      RecommenderRequest,
                      RecommenderResponse>(
                  this, METHODID_GET_RECOMMENDED_MOVIE)))
          .build();
    }
  }

  /**
   */
  public static final class RecommenderServiceStub extends io.grpc.stub.AbstractAsyncStub<RecommenderServiceStub> {
    private RecommenderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommenderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecommenderServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * client streaming request that receives a stream of movies and recommends one
     * </pre>
     */
    public io.grpc.stub.StreamObserver<RecommenderRequest> getRecommendedMovie(
        io.grpc.stub.StreamObserver<RecommenderResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetRecommendedMovieMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class RecommenderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RecommenderServiceBlockingStub> {
    private RecommenderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommenderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecommenderServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class RecommenderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RecommenderServiceFutureStub> {
    private RecommenderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommenderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecommenderServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_RECOMMENDED_MOVIE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RecommenderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RecommenderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RECOMMENDED_MOVIE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getRecommendedMovie(
              (io.grpc.stub.StreamObserver<RecommenderResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RecommenderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RecommenderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Recommender.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RecommenderService");
    }
  }

  private static final class RecommenderServiceFileDescriptorSupplier
      extends RecommenderServiceBaseDescriptorSupplier {
    RecommenderServiceFileDescriptorSupplier() {}
  }

  private static final class RecommenderServiceMethodDescriptorSupplier
      extends RecommenderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RecommenderServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RecommenderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RecommenderServiceFileDescriptorSupplier())
              .addMethod(getGetRecommendedMovieMethod())
              .build();
        }
      }
    }
    return result;
  }
}
