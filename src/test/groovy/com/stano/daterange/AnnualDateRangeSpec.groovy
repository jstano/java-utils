package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class AnnualDateRangeSpec extends Specification {

   void basicTests() {

      def endDate = LocalDate.of(2012, 1, 7)

      when:
      def dateRange = AnnualDateRange.withEndDate(endDate)

      then:
      dateRange.startDate == LocalDate.of(2011, 1, 8)
      dateRange.endDate == endDate

      when:
      dateRange = new AnnualDateRange(2014)

      then:
      dateRange.startDate == LocalDate.of(2014, 1, 1)
      dateRange.endDate == LocalDate.of(2014, 12, 31)
   }

   void withStartDateTest() {

      def startDate = LocalDate.of(2012, 1, 1)

      when:
      def dateRange = AnnualDateRange.withStartDate(startDate)

      then:
      dateRange.startDate == startDate
      dateRange.endDate == LocalDate.of(2012, 12, 31)
   }

   void testLocalDateAdd1YearDuringLeapYear() {

      def date = LocalDate.of(2011, 1, 14);
      def offDate = LocalDate.of(2012, 2, 29);

      expect:
      date.plusYears(1) == LocalDate.of(2012, 1, 14);
      date.plusYears(2) == LocalDate.of(2013, 1, 14);

      offDate.plusYears(1) == LocalDate.of(2013, 2, 28);
   }

   void testGetPriorDateRange() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = AnnualDateRange.withEndDate(endDate)

      expect:
      dateRange.priorDateRange.startDate == LocalDate.of(2010, 1, 15)
      dateRange.priorDateRange.endDate == LocalDate.of(2011, 1, 14)
   }

   void testGetNextDateRange() {

      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = AnnualDateRange.withEndDate(endDate)

      expect:
      dateRange.nextDateRange.startDate == LocalDate.of(2012, 1, 8)
      dateRange.nextDateRange.endDate == LocalDate.of(2013, 1, 7)
   }

   void testWithSnapToMonthEdges() {

      def endDate = LocalDate.of(2012, 1, 7)

      when:
      def dateRange = AnnualDateRange.withEndDate(endDate, true)

      then:
      dateRange.startDate == LocalDate.of(2011, 2, 1)
      dateRange.endDate == LocalDate.of(2012, 1, 31)
   }

   void testNextDateWithSnapToMonthEdges() {

      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = AnnualDateRange.withEndDate(endDate, true)

      expect:
      dateRange.nextDateRange.startDate == LocalDate.of(2012, 2, 1)
      dateRange.nextDateRange.endDate == LocalDate.of(2013, 1, 31)
   }

   void testPriorDateWithSnapToMonthEdges() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = AnnualDateRange.withEndDate(endDate, true)

      expect:
      dateRange.priorDateRange.startDate == LocalDate.of(2010, 2, 1)
      dateRange.priorDateRange.endDate == LocalDate.of(2011, 1, 31)
   }
}
