package com.stano.network

import spock.lang.Specification

class NetworkUtilsSpec extends Specification {
   def "addresses that should return true"() {
      expect:
      NetworkUtils.isLocalHost('127.0.0.1')
      NetworkUtils.isLocalHost('127.255.255.254')
      NetworkUtils.isLocalHost('::1')
      NetworkUtils.isLocalHost('0:0:0:0:0:0:0:0')
      NetworkUtils.isLocalHost('0:0:0:0:0:0:0:1')
      NetworkUtils.isLocalHost('localhost')
   }

   def "addresses that should return false"() {
      expect:
      !NetworkUtils.isLocalHost('192.168.254.1')
      !NetworkUtils.isLocalHost('10.0.0.1')
      !NetworkUtils.isLocalHost('google.com')
      !NetworkUtils.isLocalHost('2001:db8:85a3:8d3:1319:8a2e:370:7348')
   }
}
