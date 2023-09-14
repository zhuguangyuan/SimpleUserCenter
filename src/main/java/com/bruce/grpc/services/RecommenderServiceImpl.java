package com.bruce.grpc.services;

import com.bruce.grpc.proto.common.Movie;
import com.bruce.grpc.proto.recommender.RecommenderRequest;
import com.bruce.grpc.proto.recommender.RecommenderResponse;
import com.bruce.grpc.proto.recommender.RecommenderServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RecommenderServiceImpl extends
        RecommenderServiceGrpc.RecommenderServiceImplBase {
    @Override
    public StreamObserver<RecommenderRequest>
    getRecommendedMovie(StreamObserver<RecommenderResponse> responseObserver) {
        StreamObserver<RecommenderRequest> streamObserver = new StreamObserver<RecommenderRequest>() {
            final List<Movie> movies = new ArrayList<>();

            public void onNext(RecommenderRequest value) {
                log.info("RecommenderService stream observer onNext:{}", value);
                movies.add(value.getMovie());
            }

            public void onError(Throwable t) {
                log.info("RecommenderService stream observer onNext:", t);
                responseObserver.onError(Status.INTERNAL
                        .withDescription("Internal server error")
                        .asRuntimeException());
            }

            public void onCompleted() {
                log.info("RecommenderService stream observer onCompleted.");
                if (!movies.isEmpty()) {
                    responseObserver.onNext(RecommenderResponse.newBuilder()
                            .setMovie(findMovieForRecommendation(movies))
                            .build());
                    responseObserver.onCompleted();
                } else {
                    responseObserver.onError(Status.NOT_FOUND
                            .withDescription("Sorry, found no movies to recommend!").asRuntimeException());
                }
            }
        };
        return streamObserver;
    }

    private Movie findMovieForRecommendation(List<Movie> movies) {
        int random = new SecureRandom().nextInt(movies.size());
        return movies.stream().skip(random).findAny().get();
    }
}