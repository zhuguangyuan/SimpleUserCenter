// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: moviecontroller/moviecontroller.proto

package com.bruce.grpc.proto.moviecontroller;

import com.bruce.grpc.proto.common.Movie;
import com.bruce.grpc.proto.common.MovieOrBuilder;

public interface MovieResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:moviecontroller.MovieResponse)
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