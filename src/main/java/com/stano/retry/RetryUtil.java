package com.stano.retry;

import java.util.function.Supplier;

public final class RetryUtil {

   public static <T> T retry(int numberOfRetries, int millisBetweenRetries, Supplier<T> supplier) {

      while (numberOfRetries-- > 0) {

         try {
            return supplier.get();
         }
         catch (Exception x) {
            if (numberOfRetries == 0) {
               break;
            }
         }

         sleep(millisBetweenRetries);
      }

      throw new RetriesExceededException("Tried too many times, giving up");
   }

   public static void retry(int numberOfRetries, int millisBetweenRetries, Runnable runnable) {

      while (numberOfRetries-- > 0) {

         try {
            runnable.run();
            return;
         }
         catch (Exception x) {
            if (numberOfRetries == 0) {
               break;
            }
         }

         sleep(millisBetweenRetries);
      }

      throw new RetriesExceededException("Tried too many times, giving up");
   }

   private static void sleep(int millisBetweenTries) {

      try {
         Thread.sleep(millisBetweenTries);
      }
      catch (InterruptedException ignored) {
      }
   }

   private RetryUtil() {

   }
}
