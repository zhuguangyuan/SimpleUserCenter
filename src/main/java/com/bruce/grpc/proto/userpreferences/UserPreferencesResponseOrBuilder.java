// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: userpreferences/userpreferences.proto

package com.bruce.grpc.proto.userpreferences;

import com.bruce.grpc.proto.common.Movie;
import com.bruce.grpc.proto.common.MovieOrBuilder;

public interface UserPreferencesResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:userpreferences.UserPreferencesResponse)
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
