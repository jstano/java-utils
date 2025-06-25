package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class SemiAnnualDateRangeSpec extends Specification {

   void basicTests() {

      def endDate = LocalDate.of(2012, 1, 7)

      when:
      def dateRange = SemiAnnualDateRange.withEndDate(endDate)

      then:
      dateRange.endDate == endDate
      dateRange.startDate == LocalDate.of(2011, 7, 8)
   }

   void withStartDateTest() {

      def startDate = LocalDate.of(2012, 1, 1)

      when:
      def dateRange = SemiAnnualDateRange.withStartDate(startDate)

      then:
      dateRange.startDate == startDate
      dateRange.endDate == LocalDate.of(2012, 6, 30)
   }

   void testGetPriorDateRange() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = SemiAnnualDateRange.withEndDate(endDate)

      expect:
      dateRange.priorDateRange.startDate == LocalDate.of(2011, 1, 15)
      dateRange.priorDateRange.endDate == LocalDate.of(2011, 7, 14)
   }

   void testGetNextDateRange() {

      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = SemiAnnualDateRange.withEndDate(endDate)

      expect:
      dateRange.nextDateRange.startDate == LocalDate.of(2012, 1, 8)
      dateRange.nextDateRange.endDate == LocalDate.of(2012, 7, 7)
   }

   void testWithSnapToMonthEdges() {

      def endDate = LocalDate.of(2012, 1, 7)

      when:
      def dateRange = SemiAnnualDateRange.withEndDate(endDate, true)

      then:
      dateRange.startDate == LocalDate.of(2011, 8, 1)
      dateRange.endDate == LocalDate.of(2012, 1, 31)
   }

   void testNextDateWithSnapToMonthEdges() {

      def dateRange = SemiAnnualDateRange.withEndDate(endDate, true)

      expect:
      dateRange.getNextDateRange().getStartDate() == expectedNextStartDate
      dateRange.getNextDateRange(2).getStartDate() == expectedNext2StartDate
      dateRange.getNextDateRange().getEndDate() == expectedNextEndDate
      dateRange.getNextDateRange(2).getEndDate() == expectedNext2EndDate

      where:
      endDate                   |  expectedNextStartDate      |  expectedNextEndDate        |  expectedNext2StartDate     |  expectedNext2EndDate
      LocalDate.of(2014, 2, 15) |  LocalDate.of(2014, 3, 1)   |  LocalDate.of(2014, 8, 31)  |  LocalDate.of(2014, 9, 1)   |  LocalDate.of(2015, 2, 28)
      LocalDate.of(2014, 2, 28) |  LocalDate.of(2014, 3, 1)   |  LocalDate.of(2014, 8, 31)  |  LocalDate.of(2014, 9, 1)   |  LocalDate.of(2015, 2, 28)
      LocalDate.of(2014, 1, 31) |  LocalDate.of(2014, 2, 1)   |  LocalDate.of(2014, 7, 31)  |  LocalDate.of(2014, 8, 1)   |  LocalDate.of(2015, 1, 31)
   }

   void testPriorDateWithSnapToMonthEdges() {
      def dateRange = SemiAnnualDateRange.withEndDate(endDate, true)

      expect:
      dateRange.getPriorDateRange().getStartDate() == expectedPriorStartDate
      dateRange.getPriorDateRange(2).getStartDate() == expectedPrior2StartDate
      dateRange.getPriorDateRange().getEndDate() == expectedPriorEndDate
      dateRange.getPriorDateRange(2).getEndDate() == expectedPrior2EndDate

      where:
      endDate                   |  expectedPriorStartDate     |  expectedPriorEndDate       |  expectedPrior2StartDate    |  expectedPrior2EndDate
      LocalDate.of(2014, 2, 15) |  LocalDate.of(2013, 3, 1)   |  LocalDate.of(2013, 8, 31)  |  LocalDate.of(2012, 9, 1)   |  LocalDate.of(2013, 2, 28)
      LocalDate.of(2014, 2, 28) |  LocalDate.of(2013, 3, 1)   |  LocalDate.of(2013, 8, 31)  |  LocalDate.of(2012, 9, 1)   |  LocalDate.of(2013, 2, 28)
      LocalDate.of(2014, 1, 31) |  LocalDate.of(2013, 2, 1)   |  LocalDate.of(2013, 7, 31)  |  LocalDate.of(2012, 8, 1)   |  LocalDate.of(2013, 1, 31)
   }
}
