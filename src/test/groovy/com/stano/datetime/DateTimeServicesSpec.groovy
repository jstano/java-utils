package com.stano.datetime

import spock.lang.Specification

import java.time.LocalDateTime

class DateTimeServicesSpec extends Specification {

   def "methods should return values"() {

      def now = LocalDateTime.of(2015, 8, 21, 8, 21, 17, 45)
      DateTimeProvider.clock = new ConstantClock(now)

      def dateTimeServices = new DateTimeServices()

      expect:
      dateTimeServices.currentDateTime() == now
   }
}
