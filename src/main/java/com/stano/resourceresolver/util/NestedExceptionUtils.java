package com.stano.resourceresolver.util;

public abstract class NestedExceptionUtils {
  public static String buildMessage(String message, Throwable cause) {
    if (cause != null) {
      StringBuilder sb = new StringBuilder();
      if (message != null) {
        sb.append(message).append("; ");
      }
      sb.append("nested exception is ").append(cause);
      return sb.toString();
    }
    else {
      return message;
    }
  }
}
