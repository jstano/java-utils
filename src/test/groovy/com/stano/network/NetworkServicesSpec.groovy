package com.stano.network

import spock.lang.Specification

class NetworkServicesSpec extends Specification {
   def networkServices = new NetworkServices()

   def "addresses that should return true"() {
      expect:
      networkServices.isLocalHost('127.0.0.1')
      networkServices.isLocalHost('127.255.255.254')
      networkServices.isLocalHost('::1')
      networkServices.isLocalHost('0:0:0:0:0:0:0:0')
      networkServices.isLocalHost('0:0:0:0:0:0:0:1')
      networkServices.isLocalHost('localhost')
   }

   def "addresses that should return false"() {
      expect:
      !networkServices.isLocalHost('192.168.254.1')
      !networkServices.isLocalHost('10.0.0.1')
      !networkServices.isLocalHost('google.com')
      !networkServices.isLocalHost('2001:db8:85a3:8d3:1319:8a2e:370:7348')
   }
}
