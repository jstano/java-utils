package com.stano.daterange

import spock.lang.Specification

class DateRangeConfigSpec extends Specification {

   void testConstructorWithSingleParameter() {

      def dateRangeConfig = new DateRangeConfig(DateRangeType.WEEKLY)

      expect:
      dateRangeConfig.dateRangeType == DateRangeType.WEEKLY
      dateRangeConfig.startDayOfMonth == 1
   }

   void testConstructorWithBothParameters() {

      def dateRangeConfig = new DateRangeConfig(DateRangeType.MONTHLY, 17)

      expect:
      dateRangeConfig.dateRangeType == DateRangeType.MONTHLY
      dateRangeConfig.startDayOfMonth == 17
   }
}
