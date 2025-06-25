package com.stano.exceptions

import spock.lang.Specification

class RuntimeMalformedURLExceptionSpec extends Specification {

   def "should be able to create an exception with a nested SQLException and get the nested exception out"() {

      def malformedURLException = new MalformedURLException("URL is bad")
      def exception = new RuntimeMalformedURLException(malformedURLException)

      expect:
      exception.cause == malformedURLException
      exception.message == "java.net.MalformedURLException: URL is bad"
   }
}
