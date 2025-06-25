package com.stano.check;

import org.joor.Reflect;
import org.slf4j.Logger;

@FunctionalInterface
public interface CheckFailureHandler {

   void run();

   static CheckFailureHandler logAndThrow(Logger logger, Class<? extends RuntimeException> exceptionClass, String message, Object... values) {

      return () -> {
         String msg = String.format(message, values);

         logger.error(msg);

         throw (RuntimeException)Reflect.on(exceptionClass).create(msg).get();
      };
   }
}
