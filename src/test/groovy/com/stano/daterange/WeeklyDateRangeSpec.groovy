package com.stano.daterange

import spock.lang.Specification

import java.time.DayOfWeek
import java.time.LocalDate

class WeeklyDateRangeSpec extends Specification {

   void basicTests() {

      def endDate = LocalDate.of(2012, 1, 7)

      when:
      def dateRange = WeeklyDateRange.withEndDate(endDate)

      then:
      dateRange.endDate == endDate
      dateRange.startDate == LocalDate.of(2012, 1, 1)
   }

   void withStartDateTest() {

      def startDate = LocalDate.of(2012, 1, 1)

      when:
      def dateRange = WeeklyDateRange.withStartDate(startDate)

      then:
      dateRange.startDate == startDate
      dateRange.endDate == LocalDate.of(2012, 1, 7)
   }

   void testGetPriorDateRange() {

      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = WeeklyDateRange.withEndDate(endDate)

      expect:
      dateRange.priorDateRange.endDate == LocalDate.of(2012, 1, 7)
   }

   void testGetNextDateRange() {

      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = WeeklyDateRange.withEndDate(endDate)

      expect:
      dateRange.nextDateRange.endDate == LocalDate.of(2012, 1, 14)
   }

   void testEndDayOfWeek() {

      def dateRange = new WeeklyDateRange(endDate, endDayOfWeek)

      expect:
      dateRange.getStartDate() == expectedStartDate
      dateRange.getEndDate() == expectedEndDate

      where:
      endDate                    |  endDayOfWeek         |  expectedStartDate          |  expectedEndDate
      LocalDate.of(2014, 12, 18) |  DayOfWeek.FRIDAY     |  LocalDate.of(2014, 12, 13) |  LocalDate.of(2014, 12, 19)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.THURSDAY   |  LocalDate.of(2014, 12, 12) |  LocalDate.of(2014, 12, 18)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.WEDNESDAY  |  LocalDate.of(2014, 12, 18) |  LocalDate.of(2014, 12, 24)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.TUESDAY    |  LocalDate.of(2014, 12, 17) |  LocalDate.of(2014, 12, 23)
   }

   void testEndDayOfWeekNextRanges() {

      def dateRange = new WeeklyDateRange(endDate, endDayOfWeek)

      expect:
      dateRange.getNextDateRange().getStartDate() == expectedNextStartDate
      dateRange.getNextDateRange(2).getStartDate() == expectedNext2StartDate
      dateRange.getNextDateRange().getEndDate() == expectedNextEndDate
      dateRange.getNextDateRange(2).getEndDate() == expectedNext2EndDate

      where:
      endDate                    |  endDayOfWeek         |  expectedNextStartDate      |  expectedNextEndDate        |  expectedNext2StartDate     |  expectedNext2EndDate
      LocalDate.of(2014, 12, 18) |  DayOfWeek.FRIDAY     |  LocalDate.of(2014, 12, 20) |  LocalDate.of(2014, 12, 26) |  LocalDate.of(2014, 12, 27) |  LocalDate.of(2015, 1, 2)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.THURSDAY   |  LocalDate.of(2014, 12, 19) |  LocalDate.of(2014, 12, 25) |  LocalDate.of(2014, 12, 26) |  LocalDate.of(2015, 1, 1)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.WEDNESDAY  |  LocalDate.of(2014, 12, 25) |  LocalDate.of(2014, 12, 31) |  LocalDate.of(2015, 1, 1)   |  LocalDate.of(2015, 1, 7)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.TUESDAY    |  LocalDate.of(2014, 12, 24) |  LocalDate.of(2014, 12, 30) |  LocalDate.of(2014, 12, 31) |  LocalDate.of(2015, 1, 6)
   }

   void testEndDayOfWeekPriorRanges() {

      def dateRange = new WeeklyDateRange(LocalDate.of(2014, 12, 18), endDayOfWeek)

      expect:
      dateRange.getPriorDateRange().getStartDate() == expectedPriorStartDate
      dateRange.getPriorDateRange(2).getStartDate() == expectedPrior2StartDate
      dateRange.getPriorDateRange().getEndDate() == expectedPriorEndDate
      dateRange.getPriorDateRange(2).getEndDate() == expectedPrior2EndDate

      where:
      endDate                    |  endDayOfWeek         |  expectedPriorStartDate     |  expectedPriorEndDate       |  expectedPrior2StartDate    |  expectedPrior2EndDate
      LocalDate.of(2014, 12, 18) |  DayOfWeek.FRIDAY     |  LocalDate.of(2014, 12, 6)  |  LocalDate.of(2014, 12, 12) |  LocalDate.of(2014, 11, 29) |  LocalDate.of(2014, 12, 5)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.THURSDAY   |  LocalDate.of(2014, 12, 5)  |  LocalDate.of(2014, 12, 11) |  LocalDate.of(2014, 11, 28) |  LocalDate.of(2014, 12, 4)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.WEDNESDAY  |  LocalDate.of(2014, 12, 11) |  LocalDate.of(2014, 12, 17) |  LocalDate.of(2014, 12, 4)  |  LocalDate.of(2014, 12, 10)
      LocalDate.of(2014, 12, 18) |  DayOfWeek.TUESDAY    |  LocalDate.of(2014, 12, 10) |  LocalDate.of(2014, 12, 16) |  LocalDate.of(2014, 12, 3)  |  LocalDate.of(2014, 12, 9)
   }

   def "toString should work"() {

      def dateRange = WeeklyDateRange.withEndDate(LocalDate.of(2019, 1, 7))

      expect:
      dateRange.toString() == '2019-01-01 - 2019-01-07'
   }
}
