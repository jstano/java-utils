package com.stano.exceptions

import spock.lang.Specification

class ReflectionExceptionSpec extends Specification {

   def "should be able to create an exception using the default constructor"() {

      def exception = new ReflectionException()

      expect:
      exception.message == null
      exception.cause == null
   }

   def "should be able to create an exception with a message and get the message out"() {

      def exception = new ReflectionException("MESSAGE")

      expect:
      exception.message == "MESSAGE"
      exception.cause == null
   }

   def "should be able to create an exception with a message and nested exception and get the message and nested exception out"() {

      def nestedException = new NullPointerException()
      def exception = new ReflectionException("MESSAGE", nestedException)

      expect:
      exception.message == "MESSAGE"
      exception.cause == nestedException
   }

   def "should be able to create an exception with a nested exception and get the nested exception out"() {

      def nestedException = new NullPointerException()
      def exception = new ReflectionException(nestedException)

      expect:
      exception.cause == nestedException
      exception.message == "java.lang.NullPointerException"
   }
}
