package com.stano.exceptions

import spock.lang.Specification

class InternalServerErrorSpec extends Specification {

   def "should be able to create an exception with a message and get the message out"() {

      expect:
      new InternalServerError("MESSAGE").message == "MESSAGE"
   }

   def "should be able to create an exception with a message and nested exception and get the message and nested exception out"() {

      def nestedException = new NullPointerException()
      def exception = new InternalServerError("MESSAGE", nestedException)

      expect:
      exception.message == "MESSAGE"
      exception.cause == nestedException
   }
}
