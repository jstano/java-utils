package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class DateRangeFactorySpec extends Specification {

   void testGetInstanceWeekly() {

      def config = new DateRangeConfig(DateRangeType.WEEKLY)
      def endDate = LocalDate.of(2012, 1, 7)
      def result = DateRangeFactory.getInstance(config, endDate)

      expect:
      result instanceof WeeklyDateRange
      result.endDate == endDate
   }

   void testGetInstanceBiWeekly() {

      def config = new DateRangeConfig(DateRangeType.BI_WEEKLY)
      def endDate = LocalDate.of(2012, 1, 14)
      def result = DateRangeFactory.getInstance(config, endDate)

      expect:
      result instanceof BiWeeklyDateRange
      result.endDate == endDate
   }

   void testGetInstanceSemiMonthly() {

      def config = new DateRangeConfig(DateRangeType.SEMI_MONTHLY)
      def endDate = LocalDate.of(2012, 1, 15)
      def result = DateRangeFactory.getInstance(config, endDate)

      expect:
      result instanceof SemiMonthlyDateRange
      result.endDate == endDate
   }

   void testGetInstanceMonthly() {

      def config = new DateRangeConfig(DateRangeType.MONTHLY, 16)
      def endDate = LocalDate.of(2012, 2, 15)
      def result = DateRangeFactory.getInstance(config, endDate)

      expect:
      result instanceof MonthlyDateRange
      result.endDate == endDate
   }

   void testGetInstanceNull() {

      def config = new DateRangeConfig(null)
      def endDate = LocalDate.of(2012, 2, 15)

      when:
      DateRangeFactory.getInstance(config, endDate)

      then:
      thrown IllegalArgumentException
   }

   void testPrivateConstructorToGetFullCoverage() {

      expect:
      new DateRangeFactory() != null
   }
}
