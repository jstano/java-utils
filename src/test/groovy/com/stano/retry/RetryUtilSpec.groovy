package com.stano.retry

import spock.lang.Specification

import java.util.function.Supplier

class RetryUtilSpec extends Specification {

   def "should succeed using a Supplier if it suceeds on the second try"() {

      def loopCount = 0

      def result = RetryUtil.retry(2, 10, {
         if (loopCount++ == 1) {
            throw new IllegalStateException("FAIL")
         }

         return 2
      } as Supplier)

      expect:
      result == 2
   }

   def "should succeed using a Runnable if it suceeds on the second try"() {

      def loopCount = 0

      RetryUtil.retry(2, 10, {
         if (loopCount++ == 0) {
            throw new IllegalStateException("FAIL")
         }
      } as Runnable)

      expect:
      loopCount == 2
   }

   def "retry using a Supplier should fail with an RetriesExceededException if we exceed the retry limit"() {

      when:
      RetryUtil.retry(2, 10, {
         throw new IllegalStateException("FAIL")
      } as Supplier)

      then:
      thrown RetriesExceededException
   }

   def "retry using a Runnable should fail with an RetriesExceededException if we exceed the retry limit"() {

      when:
      RetryUtil.retry(2, 10, {
         throw new IllegalStateException("FAIL")
      } as Runnable)

      then:
      thrown RetriesExceededException
   }
}
