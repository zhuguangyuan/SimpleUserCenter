package com.bruce.grpc;

import com.bruce.grpc.proto.common.Genre;
import com.bruce.grpc.proto.moviecontroller.MovieControllerServiceGrpc;
import com.bruce.grpc.proto.moviecontroller.MovieRequest;
import com.bruce.grpc.proto.moviecontroller.MovieResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class MovieFinderClient {
    public static final int MOVIE_CONTROLLER_SERVICE_PORT = 50051;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",
                        MOVIE_CONTROLLER_SERVICE_PORT)
                .usePlaintext()
                .build();
        MovieControllerServiceGrpc.MovieControllerServiceBlockingStub
                movieFinderClient = MovieControllerServiceGrpc
                .newBlockingStub(channel);
        try {
            MovieResponse movieResponse = movieFinderClient
                    .getMovie(MovieRequest.newBuilder()
                            .setGenre(Genre.ACTION)
                            .setUserid("abc")
                            .build());
            System.out.println("Recommended movie " +
                    movieResponse.getMovie());
        } catch (StatusRuntimeException e) {
            System.out.println("Recommended movie not found!");
            e.printStackTrace();
        }
    }
}