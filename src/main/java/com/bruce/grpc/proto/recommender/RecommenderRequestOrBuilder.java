// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: recommender/recommender.proto

package com.bruce.grpc.proto.recommender;

import com.bruce.grpc.proto.common.Movie;
import com.bruce.grpc.proto.common.MovieOrBuilder;

public interface RecommenderRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:recommender.RecommenderRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string userid = 1;</code>
   * @return The userid.
   */
  java.lang.String getUserid();
  /**
   * <code>string userid = 1;</code>
   * @return The bytes for userid.
   */
  com.google.protobuf.ByteString
      getUseridBytes();

  /**
   * <code>.common.Movie movie = 2;</code>
   * @return Whether the movie field is set.
   */
  boolean hasMovie();
  /**
   * <code>.common.Movie movie = 2;</code>
   * @return The movie.
   */
  Movie getMovie();
  /**
   * <code>.common.Movie movie = 2;</code>
   */
  MovieOrBuilder getMovieOrBuilder();
}
