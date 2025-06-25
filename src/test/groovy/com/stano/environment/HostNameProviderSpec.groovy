package com.stano.environment

import spock.lang.Specification

class HostNameProviderSpec extends Specification {

   def "getHostName should return the hostname"() {

      expect:
      HostNameProvider.getHostName()
   }

   def "call the private constructor so the code coverage is accurate"() {

      expect:
      new HostNameProvider()
   }
}
