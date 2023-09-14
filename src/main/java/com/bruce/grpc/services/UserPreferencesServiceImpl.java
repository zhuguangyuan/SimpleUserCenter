package com.bruce.grpc.services;

import com.bruce.grpc.proto.common.Movie;
import com.bruce.grpc.proto.userpreferences.UserPreferencesRequest;
import com.bruce.grpc.proto.userpreferences.UserPreferencesResponse;
import com.bruce.grpc.proto.userpreferences.UserPreferencesServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

@Slf4j
public class UserPreferencesServiceImpl extends
        UserPreferencesServiceGrpc.UserPreferencesServiceImplBase {
    @Override
    public StreamObserver<UserPreferencesRequest>
    getShortlistedMovies(StreamObserver<UserPreferencesResponse> responseObserver) {
        StreamObserver<UserPreferencesRequest> streamObserver = new StreamObserver<UserPreferencesRequest>() {
            @Override
            public void onNext(UserPreferencesRequest value) {
                log.info("userPreferenceService stream observer onNext:{}", value);
                if (isEligible(value.getMovie())) {
                    responseObserver.onNext(UserPreferencesResponse
                            .newBuilder()
                            .setMovie(value.getMovie()).build());
                }
            }

            @Override
            public void onError(Throwable t) {
                log.info("userPreferenceService stream observer onNext:", t);
                responseObserver.onError(Status.INTERNAL
                        .withDescription("Internal server error")
                        .asRuntimeException());
            }

            @Override
            public void onCompleted() {
                log.info("userPreferenceService stream observer onCompleted.");
                responseObserver.onCompleted();
            }
        };
        return streamObserver;
    }

    /**
     * In the real world, the logic used for matching user preferences would  be complex.
     * It will involve tasks that track user activities such as  movies watched, bookmarked,
     * rated, liked, disliked and so on. In this  case, we will free ourselves of all such
     * complexities and implement a  rather trivial isEligible() method that uses a simple
     * random  calculation to mark an input movie as eligible or not.
     *
     * @param movie
     * @return
     */
    private boolean isEligible(Movie movie) {
        return (new SecureRandom().nextInt() % 4 != 0);
    }
}