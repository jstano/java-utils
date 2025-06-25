package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

class QuarterlyDateRangeSpec extends Specification {

   void basicTests() {

      def endDate = LocalDate.of(2012, 1, 7)

      when:
      def dateRange = QuarterlyDateRange.withEndDate(endDate)

      then:
      dateRange.startDate == LocalDate.of(2011, 11, 1)
      dateRange.endDate == LocalDate.of(2012, 1, 31)
   }

   void withStartDateTest() {

      def startDate = LocalDate.of(2012, 1, 1)

      when:
      def dateRange = QuarterlyDateRange.withStartDate(startDate)

      then:
      dateRange.startDate == LocalDate.of(2012, 1, 1)
      dateRange.endDate == LocalDate.of(2012, 3, 31)
   }

   void testGetPriorDateRange() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = QuarterlyDateRange.withEndDate(endDate)

      expect:
      dateRange.priorDateRange.startDate == LocalDate.of(2011, 8, 1)
      dateRange.priorDateRange.endDate == LocalDate.of(2011, 10, 31)
   }

   void testGetNextDateRange() {

      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = QuarterlyDateRange.withEndDate(endDate)

      expect:
      dateRange.nextDateRange.startDate == LocalDate.of(2012, 2, 1)
      dateRange.nextDateRange.endDate == LocalDate.of(2012, 4, 30)
   }

   void testCalculateOffset() {

      def offset = QuarterlyDateRange.calculateMonthOffset(date, endMonth)

      expect:
      offset == expectedOffset

      where:
      date                       |  endMonth       |  expectedOffset
      LocalDate.of(2014, 2, 15)  |  Month.APRIL    |  2
      LocalDate.of(2014, 2, 28)  |  Month.NOVEMBER |  0
      LocalDate.of(2014, 1, 31)  |  Month.APRIL    |  0
      LocalDate.of(2014, 11, 30) |  Month.APRIL    |  2
      LocalDate.of(2014, 11, 30) |  Month.DECEMBER |  1
      LocalDate.of(2014, 1, 30)  |  Month.DECEMBER |  2
   }

   void testWithEndMonth() {
      def dateRange = QuarterlyDateRange.withEndDate(endDate, endMonth)

      expect:
      dateRange.getStartDate() == expectedStartDate
      dateRange.getEndDate() == expectedEndDate

      where:
      endDate                   |  endMonth        |  expectedStartDate          |  expectedEndDate
      LocalDate.of(2014, 2, 15) |  Month.APRIL     |  LocalDate.of(2014, 2, 1)   |  LocalDate.of(2014, 4, 30)
      LocalDate.of(2014, 2, 28) |  Month.DECEMBER  |  LocalDate.of(2014, 1, 1)   |  LocalDate.of(2014, 3, 31)
      LocalDate.of(2014, 1, 31) |  Month.APRIL     |  LocalDate.of(2013, 11, 1)  |  LocalDate.of(2014, 1, 31)
   }

   void testNextRangeWithEndMonth() {
      def dateRange = QuarterlyDateRange.withEndDate(endDate, endMonth)

      expect:
      dateRange.getNextDateRange().getStartDate() == expectedNextStartDate
      dateRange.getNextDateRange(2).getStartDate() == expectedNext2StartDate
      dateRange.getNextDateRange().getEndDate() == expectedNextEndDate
      dateRange.getNextDateRange(2).getEndDate() == expectedNext2EndDate

      where:
      endDate                   |  endMonth        |  expectedNextStartDate      |  expectedNextEndDate        |  expectedNext2StartDate     |  expectedNext2EndDate
      LocalDate.of(2014, 2, 15) |  Month.APRIL     |  LocalDate.of(2014, 5, 1)   |  LocalDate.of(2014, 7, 31)  |  LocalDate.of(2014, 8, 1)   |  LocalDate.of(2014, 10, 31)
      LocalDate.of(2014, 2, 28) |  Month.DECEMBER  |  LocalDate.of(2014, 4, 1)   |  LocalDate.of(2014, 6, 30)  |  LocalDate.of(2014, 7, 1)   |  LocalDate.of(2014, 9, 30)
      LocalDate.of(2014, 1, 31) |  Month.APRIL     |  LocalDate.of(2014, 2, 1)   |  LocalDate.of(2014, 4, 30)  |  LocalDate.of(2014, 5, 1)   |  LocalDate.of(2014, 7, 31)
   }

   void testPriorRangeWithEndMonth() {
      def dateRange = QuarterlyDateRange.withEndDate(endDate, endMonth)

      expect:
      dateRange.getPriorDateRange().getStartDate() == expectedPriorStartDate
      dateRange.getPriorDateRange(2).getStartDate() == expectedPrior2StartDate
      dateRange.getPriorDateRange().getEndDate() == expectedPriorEndDate
      dateRange.getPriorDateRange(2).getEndDate() == expectedPrior2EndDate

      where:
      endDate                   |  endMonth        |  expectedPriorStartDate      |  expectedPriorEndDate        |  expectedPrior2StartDate     |  expectedPrior2EndDate
      LocalDate.of(2014, 2, 15) |  Month.APRIL     |  LocalDate.of(2013, 11, 1)   |  LocalDate.of(2014, 1, 31)   |  LocalDate.of(2013, 8, 1)    |  LocalDate.of(2013, 10, 31)
      LocalDate.of(2014, 2, 28) |  Month.DECEMBER  |  LocalDate.of(2013, 10, 1)   |  LocalDate.of(2013, 12, 31)  |  LocalDate.of(2013, 7, 1)    |  LocalDate.of(2013, 9, 30)
      LocalDate.of(2014, 1, 31) |  Month.APRIL     |  LocalDate.of(2013, 8, 1)    |  LocalDate.of(2013, 10, 31)  |  LocalDate.of(2013, 5, 1)    |  LocalDate.of(2013, 7, 31)
   }
}
