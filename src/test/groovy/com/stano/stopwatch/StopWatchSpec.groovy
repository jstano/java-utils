package com.stano.stopwatch

import spock.lang.Specification

class StopWatchSpec extends Specification {

   def "StopWatch.timed should work"() {

      def result = StopWatch.timed({Thread.sleep(1000)})

      expect:
      result >= 1000
   }
}
