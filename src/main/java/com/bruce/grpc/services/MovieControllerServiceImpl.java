package com.bruce.grpc.services;

import com.bruce.grpc.proto.moviecontroller.MovieControllerServiceGrpc;
import com.bruce.grpc.proto.moviecontroller.MovieRequest;
import com.bruce.grpc.proto.moviecontroller.MovieResponse;
import com.bruce.grpc.proto.moviestore.MovieStoreRequest;
import com.bruce.grpc.proto.moviestore.MovieStoreServiceGrpc;
import com.bruce.grpc.proto.recommender.RecommenderRequest;
import com.bruce.grpc.proto.recommender.RecommenderResponse;
import com.bruce.grpc.proto.recommender.RecommenderServiceGrpc;
import com.bruce.grpc.proto.userpreferences.UserPreferencesRequest;
import com.bruce.grpc.proto.userpreferences.UserPreferencesResponse;
import com.bruce.grpc.proto.userpreferences.UserPreferencesServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MovieControllerServiceImpl extends
        MovieControllerServiceGrpc.MovieControllerServiceImplBase {
    public static final int MOVIES_SERVICE_PORT = 50052;
    public static final int USER_PREFERENCES_SERVICE_PORT = 50053;
    public static final int RECOMMENDER_SERVICE_PORT = 50054;

    @Override
    public void getMovie(MovieRequest request, StreamObserver<MovieResponse> responseObserver) {
        log.info("MovieControllerServiceImpl getMovie request:{}", request);

        /**
         * store get list
         * ==> stream
         * ==> preference
         * ==> recommend
         */
        String userId = request.getUserid();
        MovieStoreServiceGrpc.MovieStoreServiceBlockingStub
                movieStoreClient = MovieStoreServiceGrpc.newBlockingStub(getChannel(MOVIES_SERVICE_PORT));
        UserPreferencesServiceGrpc.UserPreferencesServiceStub
                userPreferencesClient = UserPreferencesServiceGrpc.newStub(getChannel(USER_PREFERENCES_SERVICE_PORT));
        RecommenderServiceGrpc.RecommenderServiceStub
                recommenderClient = RecommenderServiceGrpc.newStub(getChannel(RECOMMENDER_SERVICE_PORT));

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<RecommenderRequest> recommenderRequestObserver = recommenderClient.getRecommendedMovie(new StreamObserver<RecommenderResponse>() {
            public void onNext(RecommenderResponse value) {
                log.warn("recommend movie on next:{}", value.getMovie());
                responseObserver.onNext(MovieResponse
                        .newBuilder()
                        .setMovie(value.getMovie()).build());
            }

            public void onError(Throwable t) {
                log.warn("recommend movie on onError.", t);
                responseObserver.onError(t);
                latch.countDown();
            }

            public void onCompleted() {
                log.warn("recommend movie on completed.");
                responseObserver.onCompleted();
                latch.countDown();
            }
        });

        StreamObserver<UserPreferencesRequest> streamObserver = userPreferencesClient.getShortlistedMovies(new StreamObserver<UserPreferencesResponse>() {
            public void onNext(UserPreferencesResponse value) {
                log.warn("prefer movie on next:{}", value.getMovie());
                recommenderRequestObserver
                        .onNext(RecommenderRequest.newBuilder()
                                .setUserid(userId)
                                .setMovie(value.getMovie()).build());
            }

            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
                log.warn("prefer movie on completed.");
                recommenderRequestObserver.onCompleted();
            }
        });

        movieStoreClient
                .getMovies(MovieStoreRequest.newBuilder().setGenre(request.getGenre()).build())
                .forEachRemaining(response -> {
                    log.warn("got movie from storeService, will put it to StreamObserver:{}", response);
                    streamObserver.onNext(UserPreferencesRequest.newBuilder()
                            .setUserid(userId)
                            .setMovie(response.getMovie())
                            .build());
                });
        streamObserver.onCompleted();
        try {
            latch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ManagedChannel getChannel(int port) {
        return ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();
    }
}