package com.stano.check

import org.slf4j.LoggerFactory
import spock.lang.Specification

class CheckFailureHandlerSpec extends Specification {

   def "logAndThrow should work"() {

      def logger = LoggerFactory.getLogger(CheckFailureHandlerSpec.class)
      def failureHandler = CheckFailureHandler.logAndThrow(logger, BadStuffHappenedException.class, "Bad Stuff %s", "Happened")

      when:
      failureHandler.run()

      then:
      def x = thrown(BadStuffHappenedException)
      x.message == "Bad Stuff Happened"
   }
}
