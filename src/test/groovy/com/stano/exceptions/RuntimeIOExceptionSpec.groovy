package com.stano.exceptions

import spock.lang.Specification

class RuntimeIOExceptionSpec extends Specification {

   def "should be able to create an exception with a nested IOException and get the nested exception out"() {

      def rootCause = new NullPointerException()
      def ioException = new IOException("Something bad happened", rootCause)
      def exception = new RuntimeIOException(ioException)

      expect:
      exception.cause == ioException
      exception.message == "java.io.IOException: Something bad happened"
   }

   def "should be able to create an exception with a nested OutOfMemoryError and get the nested exception out"() {

      def rootCause = new OutOfMemoryError('Out of memory')
      def exception = new RuntimeIOException(rootCause)

      expect:
      exception.cause == rootCause
      exception.message == "java.lang.OutOfMemoryError: Out of memory"
   }
}
