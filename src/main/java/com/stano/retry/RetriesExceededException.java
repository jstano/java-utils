package com.stano.retry;

public class RetriesExceededException extends RuntimeException {

   public RetriesExceededException(String message) {

      super(message);
   }
}
