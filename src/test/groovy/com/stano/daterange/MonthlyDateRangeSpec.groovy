package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class MonthlyDateRangeSpec extends Specification {

   void basicTests() {

      def endDate = LocalDate.of(2012, 1, 31)
      def dateRange = MonthlyDateRange.withEndDateOnFirstOfMonth(endDate)

      expect:
      dateRange.endDate == endDate
   }

   void testCreationOffsetStartOfMonth() {

      def endDate = LocalDate.of(2012, 1, 15)
      def dateRange = MonthlyDateRange.withEndDateAndStartDayOfMonth(endDate, 16)

      expect:
      dateRange.endDate == endDate
   }

   void testFailingConfig() {

      when:
      MonthlyDateRange.withEndDateAndStartDayOfMonth(LocalDate.of(2012, 1, 31), 5)

      then:
      thrown IllegalArgumentException
   }

   void testGetPriorDateRange() {

      def dateRange = MonthlyDateRange.withEndDateAndStartDayOfMonth(endDate, startDayOfMonth)

      expect:
      dateRange.priorDateRange.endDate == expectedEndDate

      where:
      endDate                   | startDayOfMonth | expectedEndDate
      LocalDate.of(2012, 1, 31) | 1               | LocalDate.of(2011, 12, 31)
      LocalDate.of(2012, 1, 15) | 16              | LocalDate.of(2011, 12, 15)
   }

   void testGetNextDateRange() {

      def dateRange = MonthlyDateRange.withEndDateAndStartDayOfMonth(endDate, startDayOfMonth)

      expect:
      dateRange.nextDateRange.endDate == expectedEndDate

      where:
      endDate                   | startDayOfMonth | expectedEndDate
      LocalDate.of(2012, 1, 31) | 1               | LocalDate.of(2012, 2, 29)
      LocalDate.of(2012, 1, 15) | 16              | LocalDate.of(2012, 2, 15)
   }

   void testCreateNewDateRange() {

      def dateRange = MonthlyDateRange.withEndDateAndStartDayOfMonth(LocalDate.of(2012, 2, 15), 16)
      def newDateRange = dateRange.createNewDateRange(LocalDate.of(2012, 1, 16), LocalDate.of(2012, 2, 15))

      expect:
      newDateRange.startDate == LocalDate.of(2012, 1, 16)
      newDateRange.endDate == LocalDate.of(2012, 2, 15)
      newDateRange.startDayOfMonth == 16
   }
}
