package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class SemiMonthlyDateRangeSpec extends Specification {

   void testWithEndDateFirstHalfOfMonth() {

      def endDate = LocalDate.of(2012, 1, 15)
      def dateRange = SemiMonthlyDateRange.withEndDate(endDate)

      expect:
      dateRange.endDate == endDate
   }

   void testWithEndDateSecondHalfOfMonth() {

      def endDate = LocalDate.of(2012, 1, 31)
      def dateRange = SemiMonthlyDateRange.withEndDate(endDate)

      expect:
      dateRange.endDate == endDate
   }

   void testWithEndDateNotCorrect() {

      def endDate = LocalDate.of(2012, 1, 22)

      when:
      SemiMonthlyDateRange.withEndDate(endDate)

      then:
      thrown IllegalArgumentException
   }

   void testGetPriorDateRange() {

      def endDate = LocalDate.of(2012, 1, 15)
      def dateRange = SemiMonthlyDateRange.withEndDate(endDate)

      expect:
      dateRange.priorDateRange.endDate == LocalDate.of(2011, 12, 31)
      dateRange.priorDateRange.priorDateRange.endDate == LocalDate.of(2011, 12, 15)
   }

   void testGetNextDateRange() {

      def endDate = LocalDate.of(2012, 1, 15)
      def dateRange = SemiMonthlyDateRange.withEndDate(endDate)

      expect:
      dateRange.nextDateRange.endDate == LocalDate.of(2012, 1, 31)
      dateRange.nextDateRange.nextDateRange.endDate == LocalDate.of(2012, 2, 15)
   }

   void testCreateNewDateRange() {

      def endDate = LocalDate.of(2012, 1, 15)
      def dateRange = SemiMonthlyDateRange.withEndDate(endDate)
      def newDateRange = dateRange.createNewDateRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 15))

      expect:
      newDateRange.startDate == LocalDate.of(2012, 1, 1)
      newDateRange.endDate == LocalDate.of(2012, 1, 15)
   }
}
