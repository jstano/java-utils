package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class DateRangeIteratorSpec extends Specification {

   void testLooping() {

      def startDate = LocalDate.of(2012, 1, 1);
      def endDate = LocalDate.of(2012, 1, 7);
      def dateRange = ArbitraryDateRange.of(startDate, endDate);

      def expected = [
         LocalDate.of(2012, 1, 1),
         LocalDate.of(2012, 1, 2),
         LocalDate.of(2012, 1, 3),
         LocalDate.of(2012, 1, 4),
         LocalDate.of(2012, 1, 5),
         LocalDate.of(2012, 1, 6),
         LocalDate.of(2012, 1, 7)
      ]

      def results = new ArrayList<LocalDate>();

      for (LocalDate date : dateRange) {
         results.add(date);
      }

      expect:
      expected == results
   }

   void "reverse date range should loop correctly"() {

      def startDate = LocalDate.of(2012, 1, 7);
      def endDate = LocalDate.of(2012, 1, 1);
      def dateRange = ArbitraryDateRange.of(startDate, endDate);

      def expected = [
         LocalDate.of(2012, 1, 7),
         LocalDate.of(2012, 1, 6),
         LocalDate.of(2012, 1, 5),
         LocalDate.of(2012, 1, 4),
         LocalDate.of(2012, 1, 3),
         LocalDate.of(2012, 1, 2),
         LocalDate.of(2012, 1, 1)
      ]

      def results = new ArrayList<LocalDate>();

      for (LocalDate date : dateRange) {
         results.add(date);
      }

      expect:
      expected == results
   }

   void testRemove() {

      def dateRange = ArbitraryDateRange.of(LocalDate.now(), LocalDate.now().plusDays(6));

      when:
      dateRange.iterator().remove();

      then:
      thrown UnsupportedOperationException
   }
}
