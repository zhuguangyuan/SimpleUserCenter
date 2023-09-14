// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: userpreferences/userpreferences.proto

package com.bruce.grpc.proto.userpreferences;

import com.bruce.grpc.proto.common.Movie;
import com.bruce.grpc.proto.common.MovieOrBuilder;

/**
 * Protobuf type {@code userpreferences.UserPreferencesRequest}
 */
public final class UserPreferencesRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:userpreferences.UserPreferencesRequest)
    UserPreferencesRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserPreferencesRequest.newBuilder() to construct.
  private UserPreferencesRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserPreferencesRequest() {
    userid_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UserPreferencesRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserPreferencesRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            userid_ = s;
            break;
          }
          case 18: {
            Movie.Builder subBuilder = null;
            if (movie_ != null) {
              subBuilder = movie_.toBuilder();
            }
            movie_ = input.readMessage(Movie.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(movie_);
              movie_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return Userpreferences.internal_static_userpreferences_UserPreferencesRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Userpreferences.internal_static_userpreferences_UserPreferencesRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UserPreferencesRequest.class, UserPreferencesRequest.Builder.class);
  }

  public static final int USERID_FIELD_NUMBER = 1;
  private volatile java.lang.Object userid_;
  /**
   * <code>string userid = 1;</code>
   * @return The userid.
   */
  @java.lang.Override
  public java.lang.String getUserid() {
    java.lang.Object ref = userid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userid_ = s;
      return s;
    }
  }
  /**
   * <code>string userid = 1;</code>
   * @return The bytes for userid.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUseridBytes() {
    java.lang.Object ref = userid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MOVIE_FIELD_NUMBER = 2;
  private Movie movie_;
  /**
   * <code>.common.Movie movie = 2;</code>
   * @return Whether the movie field is set.
   */
  @java.lang.Override
  public boolean hasMovie() {
    return movie_ != null;
  }
  /**
   * <code>.common.Movie movie = 2;</code>
   * @return The movie.
   */
  @java.lang.Override
  public Movie getMovie() {
    return movie_ == null ? Movie.getDefaultInstance() : movie_;
  }
  /**
   * <code>.common.Movie movie = 2;</code>
   */
  @java.lang.Override
  public MovieOrBuilder getMovieOrBuilder() {
    return getMovie();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getUseridBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userid_);
    }
    if (movie_ != null) {
      output.writeMessage(2, getMovie());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getUseridBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, userid_);
    }
    if (movie_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getMovie());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof UserPreferencesRequest)) {
      return super.equals(obj);
    }
    UserPreferencesRequest other = (UserPreferencesRequest) obj;

    if (!getUserid()
        .equals(other.getUserid())) return false;
    if (hasMovie() != other.hasMovie()) return false;
    if (hasMovie()) {
      if (!getMovie()
          .equals(other.getMovie())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + USERID_FIELD_NUMBER;
    hash = (53 * hash) + getUserid().hashCode();
    if (hasMovie()) {
      hash = (37 * hash) + MOVIE_FIELD_NUMBER;
      hash = (53 * hash) + getMovie().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UserPreferencesRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserPreferencesRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserPreferencesRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserPreferencesRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserPreferencesRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserPreferencesRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserPreferencesRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserPreferencesRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserPreferencesRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UserPreferencesRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserPreferencesRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserPreferencesRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(UserPreferencesRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code userpreferences.UserPreferencesRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:userpreferences.UserPreferencesRequest)
          UserPreferencesRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Userpreferences.internal_static_userpreferences_UserPreferencesRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Userpreferences.internal_static_userpreferences_UserPreferencesRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserPreferencesRequest.class, UserPreferencesRequest.Builder.class);
    }

    // Construct using com.bruce.grpc.proto.userpreferences.UserPreferencesRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      userid_ = "";

      if (movieBuilder_ == null) {
        movie_ = null;
      } else {
        movie_ = null;
        movieBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return Userpreferences.internal_static_userpreferences_UserPreferencesRequest_descriptor;
    }

    @java.lang.Override
    public UserPreferencesRequest getDefaultInstanceForType() {
      return UserPreferencesRequest.getDefaultInstance();
    }

    @java.lang.Override
    public UserPreferencesRequest build() {
      UserPreferencesRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public UserPreferencesRequest buildPartial() {
      UserPreferencesRequest result = new UserPreferencesRequest(this);
      result.userid_ = userid_;
      if (movieBuilder_ == null) {
        result.movie_ = movie_;
      } else {
        result.movie_ = movieBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof UserPreferencesRequest) {
        return mergeFrom((UserPreferencesRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UserPreferencesRequest other) {
      if (other == UserPreferencesRequest.getDefaultInstance()) return this;
      if (!other.getUserid().isEmpty()) {
        userid_ = other.userid_;
        onChanged();
      }
      if (other.hasMovie()) {
        mergeMovie(other.getMovie());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      UserPreferencesRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (UserPreferencesRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object userid_ = "";
    /**
     * <code>string userid = 1;</code>
     * @return The userid.
     */
    public java.lang.String getUserid() {
      java.lang.Object ref = userid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userid_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string userid = 1;</code>
     * @return The bytes for userid.
     */
    public com.google.protobuf.ByteString
        getUseridBytes() {
      java.lang.Object ref = userid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string userid = 1;</code>
     * @param value The userid to set.
     * @return This builder for chaining.
     */
    public Builder setUserid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      userid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string userid = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserid() {
      
      userid_ = getDefaultInstance().getUserid();
      onChanged();
      return this;
    }
    /**
     * <code>string userid = 1;</code>
     * @param value The bytes for userid to set.
     * @return This builder for chaining.
     */
    public Builder setUseridBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      userid_ = value;
      onChanged();
      return this;
    }

    private Movie movie_;
    private com.google.protobuf.SingleFieldBuilderV3<
            Movie, Movie.Builder, MovieOrBuilder> movieBuilder_;
    /**
     * <code>.common.Movie movie = 2;</code>
     * @return Whether the movie field is set.
     */
    public boolean hasMovie() {
      return movieBuilder_ != null || movie_ != null;
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     * @return The movie.
     */
    public Movie getMovie() {
      if (movieBuilder_ == null) {
        return movie_ == null ? Movie.getDefaultInstance() : movie_;
      } else {
        return movieBuilder_.getMessage();
      }
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     */
    public Builder setMovie(Movie value) {
      if (movieBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        movie_ = value;
        onChanged();
      } else {
        movieBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     */
    public Builder setMovie(
        Movie.Builder builderForValue) {
      if (movieBuilder_ == null) {
        movie_ = builderForValue.build();
        onChanged();
      } else {
        movieBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     */
    public Builder mergeMovie(Movie value) {
      if (movieBuilder_ == null) {
        if (movie_ != null) {
          movie_ =
            Movie.newBuilder(movie_).mergeFrom(value).buildPartial();
        } else {
          movie_ = value;
        }
        onChanged();
      } else {
        movieBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     */
    public Builder clearMovie() {
      if (movieBuilder_ == null) {
        movie_ = null;
        onChanged();
      } else {
        movie_ = null;
        movieBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     */
    public Movie.Builder getMovieBuilder() {
      
      onChanged();
      return getMovieFieldBuilder().getBuilder();
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     */
    public MovieOrBuilder getMovieOrBuilder() {
      if (movieBuilder_ != null) {
        return movieBuilder_.getMessageOrBuilder();
      } else {
        return movie_ == null ?
            Movie.getDefaultInstance() : movie_;
      }
    }
    /**
     * <code>.common.Movie movie = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
            Movie, Movie.Builder, MovieOrBuilder>
        getMovieFieldBuilder() {
      if (movieBuilder_ == null) {
        movieBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                Movie, Movie.Builder, MovieOrBuilder>(
                getMovie(),
                getParentForChildren(),
                isClean());
        movie_ = null;
      }
      return movieBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:userpreferences.UserPreferencesRequest)
  }

  // @@protoc_insertion_point(class_scope:userpreferences.UserPreferencesRequest)
  private static final UserPreferencesRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UserPreferencesRequest();
  }

  public static UserPreferencesRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserPreferencesRequest>
      PARSER = new com.google.protobuf.AbstractParser<UserPreferencesRequest>() {
    @java.lang.Override
    public UserPreferencesRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserPreferencesRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserPreferencesRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UserPreferencesRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public UserPreferencesRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

