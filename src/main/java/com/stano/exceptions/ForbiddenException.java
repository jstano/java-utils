package com.stano.exceptions;

public class ForbiddenException extends RuntimeException {

   public ForbiddenException() {

   }

   public ForbiddenException(String message) {

      super(message);
   }

   public ForbiddenException(String message, Throwable cause) {

      super(message, cause);
   }
}
