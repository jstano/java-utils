package com.stano.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

public final class ExceptionUtils {

   public static boolean exceptionIsOfType(Throwable exception, Set<String> exceptionTypes) {

      for (String exceptionTypeClassName : exceptionTypes) {
         Class<? extends Throwable> exceptionType = getClassForName(exceptionTypeClassName);

         if (exceptionIsOfType(exception, exceptionType)) {
            return true;
         }
      }

      return false;
   }

   public static boolean exceptionIsOfType(Throwable exception, Class<? extends Throwable> exceptionType) {

      if (exceptionType.isAssignableFrom(exception.getClass())) {
         return true;
      }

      if (exception.getCause() != null) {
         return exceptionIsOfType(exception.getCause(), exceptionType);
      }

      return false;
   }

   public static String getStackTrace(Throwable x) {

      return getStackTrace(x, false);
   }

   public static String getStackTraceWithTranslation(Throwable x) {

      return getStackTrace(x, true);
   }

   private static String getStackTrace(Throwable x, boolean translate) {

      StringWriter stackInfo = new StringWriter(1000);
      PrintWriter stackInfoDump = new PrintWriter(stackInfo);

      x.printStackTrace(stackInfoDump);

      stackInfoDump.close();

      if (translate) {
         StringBuffer stackTrace = stackInfo.getBuffer();
         StringBuilder stackTrace2 = new StringBuilder(stackTrace.length());

         for (int i = 0; i < stackTrace.length(); i++) {
            char ch = stackTrace.charAt(i);

            if (ch != '\r' && ch != '\t') {
               stackTrace2.append(ch);
            }
         }

         return stackTrace2.toString();
      }

      return stackInfo.toString();
   }

   @SuppressWarnings("unchecked")
   private static <T> Class<T> getClassForName(String name) {

      try {
         return (Class<T>)Class.forName(name);
      }
      catch (ClassNotFoundException x) {
         throw new RuntimeException(x);
      }
   }

   private ExceptionUtils() {

   }
}
