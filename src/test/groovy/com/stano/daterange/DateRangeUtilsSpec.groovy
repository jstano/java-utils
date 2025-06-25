package com.stano.daterange

import spock.lang.Specification

import java.time.LocalDate

class DateRangeUtilsSpec extends Specification {

   def "test Overlapping Date ranges validators"() {

      expect:
      hasOverlap == DateRangeUtils.dateRangeListContainsOverlaps(dateRanges)

      where:
      dateRanges   | hasOverlap | scenario
      testDates0() | false      | "Empty List"
      testDates1() | false      | "Single range, no overlap"
      testDates2() | true       | "2 Ranges which Overlap"
      testDates3() | false      | "2 Ranges with no Overlap"
      testDates4() | true       | "5 ranges, the 4th and 5th range overlap"
      testDates5() | false      | "5 ranges, no overlaps"
   }

   def testPrivateConstructorToGetFullCoverage() {

      expect:
      new DateRangeUtils() != null
   }

   def testDates0() {
      new LinkedList<DateRange>()
   }

   def testDates1() {

      new LinkedList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 12, 31))])
   }

   def testDates2() {

      new LinkedList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 12, 31)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 03, 01), LocalDate.of(2012, 03, 31))])
   }

   def testDates3() {

      new LinkedList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 01, 31)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 03, 01), LocalDate.of(2012, 03, 31))])
   }

   def testDates4() {

      new LinkedList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 01, 31)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 03, 01), LocalDate.of(2012, 03, 31)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 04, 01), LocalDate.of(2012, 04, 30)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 05, 01), LocalDate.of(2012, 06, 01)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 06, 01), LocalDate.of(2012, 06, 30))])
   }

   def testDates5() {

      new LinkedList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 01, 31)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 03, 01), LocalDate.of(2012, 03, 31)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 04, 01), LocalDate.of(2012, 04, 30)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 05, 01), LocalDate.of(2012, 05, 31)),
                                 ArbitraryDateRange.of(LocalDate.of(2012, 06, 01), LocalDate.of(2012, 06, 30))])
   }
}
