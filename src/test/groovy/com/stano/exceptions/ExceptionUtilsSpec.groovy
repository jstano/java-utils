package com.stano.exceptions

import spock.lang.Specification

class ExceptionUtilsSpec extends Specification {

   static oldLineSeparator

   void setupSpec() {

      oldLineSeparator = System.getProperty("line.separator")
      System.setProperty("line.separator", "\r\n")
   }

   void cleanupSpec() {

      System.setProperty("line.separator", oldLineSeparator)
   }

   def "exceptionIsOfType(Set<String>) should work"() {

      def exceptionsToIgnore = ["java.lang.NullPointerException", "java.lang.UnsupportedOperationException"] as Set<String>

      expect:
      !ExceptionUtils.exceptionIsOfType(new IllegalStateException(), exceptionsToIgnore)
      !ExceptionUtils.exceptionIsOfType(new IllegalStateException(new RuntimeException()), exceptionsToIgnore)

      ExceptionUtils.exceptionIsOfType(new NullPointerException(), exceptionsToIgnore)
      ExceptionUtils.exceptionIsOfType(new RuntimeException(new NullPointerException()), exceptionsToIgnore)
      ExceptionUtils.exceptionIsOfType(new RuntimeException(new IllegalStateException(new NullPointerException())), exceptionsToIgnore)

      ExceptionUtils.exceptionIsOfType(new UnsupportedOperationException(), exceptionsToIgnore)
      ExceptionUtils.exceptionIsOfType(new RuntimeException(new UnsupportedOperationException()), exceptionsToIgnore)
      ExceptionUtils.exceptionIsOfType(new RuntimeException(new IllegalStateException(new UnsupportedOperationException())), exceptionsToIgnore)
   }

   def "exceptionIsOfType(Class) should work"() {

      expect:
      !ExceptionUtils.exceptionIsOfType(new IllegalStateException(), NullPointerException.class)
      !ExceptionUtils.exceptionIsOfType(new IllegalStateException(new RuntimeException()), NullPointerException.class)

      ExceptionUtils.exceptionIsOfType(new NullPointerException(), NullPointerException.class)
      ExceptionUtils.exceptionIsOfType(new RuntimeException(new NullPointerException()), NullPointerException.class)
      ExceptionUtils.exceptionIsOfType(new RuntimeException(new IllegalStateException(new NullPointerException())), NullPointerException.class)
   }

   void testGetStackTraceWithoutTranslation() {

      def exception = new IllegalArgumentException("This is a problem")

      def stackTrace = ExceptionUtils.getStackTrace(exception)

      expect:
      stackTrace.contains("This is a problem")
      stackTrace.contains("\t")
   }

   void testGetStackTraceWithTranslation() {

      def exception = new IllegalArgumentException("This is a problem")

      def stackTrace = ExceptionUtils.getStackTraceWithTranslation(exception)

      expect:
      stackTrace.contains("This is a problem")
      !stackTrace.contains("\t")
   }

   void testPrivateConstructorToGetCompleteCoverage() {

      expect:
      new ExceptionUtils() != null
   }
}
