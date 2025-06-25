package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class DateRangeStartComparatorSpec extends Specification {

   void testCompare() {

      def dateRange1 = WeeklyDateRange.withEndDate(LocalDate.of(2012, 1, 14))
      def dateRange2 = WeeklyDateRange.withEndDate(LocalDate.of(2012, 1, 14))
      def dateRange3 = WeeklyDateRange.withEndDate(LocalDate.of(2012, 1, 7))
      def dateRange4 = WeeklyDateRange.withEndDate(LocalDate.of(2012, 1, 21))

      def dateRangeStartComparator = new DateRangeStartComparator()

      expect:
      dateRangeStartComparator.compare(dateRange1, dateRange1) == 0
      dateRangeStartComparator.compare(dateRange1, dateRange2) == 0

      dateRangeStartComparator.compare(dateRange1, dateRange4) < 0

      dateRangeStartComparator.compare(dateRange1, dateRange3) > 0
   }
}
