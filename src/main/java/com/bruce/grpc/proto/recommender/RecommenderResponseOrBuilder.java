// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: recommender/recommender.proto

package com.bruce.grpc.proto.recommender;

import com.bruce.grpc.proto.common.Movie;
import com.bruce.grpc.proto.common.MovieOrBuilder;

public interface RecommenderResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:recommender.RecommenderResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.common.Movie movie = 1;</code>
   * @return Whether the movie field is set.
   */
  boolean hasMovie();
  /**
   * <code>.common.Movie movie = 1;</code>
   * @return The movie.
   */
  Movie getMovie();
  /**
   * <code>.common.Movie movie = 1;</code>
   */
  MovieOrBuilder getMovieOrBuilder();
}