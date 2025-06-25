package com.stano.daterange

import spock.lang.Specification

import java.time.DayOfWeek
import java.time.LocalDate

class BiWeeklyDateRangeSpec extends Specification {

   void basicTests() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = BiWeeklyDateRange.withEndDate(endDate)

      expect:
      dateRange.endDate == endDate
   }

   void testGetPriorDateRange() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = BiWeeklyDateRange.withEndDate(endDate)

      expect:
      dateRange.priorDateRange.endDate == LocalDate.of(2011, 12, 31)
   }

   void testGetNextDateRange() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = BiWeeklyDateRange.withEndDate(endDate)

      expect:
      dateRange.nextDateRange.endDate == LocalDate.of(2012, 1, 28)
   }

   void testEndDayOfWeek() {

      def dateRange = new BiWeeklyDateRange(endDate, endDayOfWeek)

      expect:
      dateRange.getStartDate() == expectedStartDate
      dateRange.getEndDate() == expectedEndDate

      where:
      endDate                    |  endDayOfWeek         |  expectedStartDate          |  expectedEndDate
      LocalDate.of(2014, 12, 18) |  DayOfWeek.FRIDAY     |  LocalDate.of(2014, 12, 6)  |  LocalDate.of(2014, 12, 19)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.THURSDAY   |  LocalDate.of(2014, 12, 5)  |  LocalDate.of(2014, 12, 18)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.WEDNESDAY  |  LocalDate.of(2014, 12, 11) |  LocalDate.of(2014, 12, 24)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.TUESDAY    |  LocalDate.of(2014, 12, 10) |  LocalDate.of(2014, 12, 23)
   }

   void testEndDayOfWeekNextRanges() {

      def dateRange = new BiWeeklyDateRange(endDate, endDayOfWeek)

      expect:
      dateRange.getNextDateRange().getStartDate() == expectedNextStartDate
      dateRange.getNextDateRange(2).getStartDate() == expectedNext2StartDate
      dateRange.getNextDateRange().getEndDate() == expectedNextEndDate
      dateRange.getNextDateRange(2).getEndDate() == expectedNext2EndDate

      where:
      endDate                    |  endDayOfWeek         |  expectedNextStartDate      |  expectedNextEndDate      |  expectedNext2StartDate     |  expectedNext2EndDate
      LocalDate.of(2014, 12, 18) |  DayOfWeek.FRIDAY     |  LocalDate.of(2014, 12, 20) |  LocalDate.of(2015, 1, 2) |  LocalDate.of(2015, 1, 3)   |  LocalDate.of(2015, 1, 16)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.THURSDAY   |  LocalDate.of(2014, 12, 19) |  LocalDate.of(2015, 1, 1) |  LocalDate.of(2015, 1, 2)   |  LocalDate.of(2015, 1, 15)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.WEDNESDAY  |  LocalDate.of(2014, 12, 25) |  LocalDate.of(2015, 1, 7) |  LocalDate.of(2015, 1, 8)   |  LocalDate.of(2015, 1, 21)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.TUESDAY    |  LocalDate.of(2014, 12, 24) |  LocalDate.of(2015, 1, 6) |  LocalDate.of(2015, 1, 7)   |  LocalDate.of(2015, 1, 20)
   }

   void testEndDayOfWeekPriorRanges() {

      def dateRange = new BiWeeklyDateRange(LocalDate.of(2014, 12, 18), endDayOfWeek)

      expect:
      dateRange.getPriorDateRange().getStartDate() == expectedPriorStartDate
      dateRange.getPriorDateRange(2).getStartDate() == expectedPrior2StartDate
      dateRange.getPriorDateRange().getEndDate() == expectedPriorEndDate
      dateRange.getPriorDateRange(2).getEndDate() == expectedPrior2EndDate

      where:
      endDate                    |  endDayOfWeek         |  expectedPriorStartDate     |  expectedPriorEndDate       |  expectedPrior2StartDate    |  expectedPrior2EndDate
      LocalDate.of(2014, 12, 18) |  DayOfWeek.FRIDAY     |  LocalDate.of(2014, 11, 22) |  LocalDate.of(2014, 12, 5)  |  LocalDate.of(2014, 11, 8)  |  LocalDate.of(2014, 11, 21)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.THURSDAY   |  LocalDate.of(2014, 11, 21) |  LocalDate.of(2014, 12, 4)  |  LocalDate.of(2014, 11, 7)  |  LocalDate.of(2014, 11, 20)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.WEDNESDAY  |  LocalDate.of(2014, 11, 27) |  LocalDate.of(2014, 12, 10) |  LocalDate.of(2014, 11, 13) |  LocalDate.of(2014, 11, 26)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.TUESDAY    |  LocalDate.of(2014, 11, 26) |  LocalDate.of(2014, 12, 9)  |  LocalDate.of(2014, 11, 12) |  LocalDate.of(2014, 11, 25)
   }
}
