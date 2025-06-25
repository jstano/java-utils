package com.stano.daterange

import com.stano.testhelper.PerformClone
import spock.lang.Ignore
import spock.lang.Specification

import java.time.DayOfWeek
import java.time.LocalDate

class ArbitraryDateRangeSpec extends Specification {

   void testWithSingleDate() {

      def date = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(date)

      expect:
      dateRange.startDate == date
      dateRange.endDate == date
   }

   void testWithSingleDateNull() {

      when:
      ArbitraryDateRange.of(null)

      then:
      thrown IllegalArgumentException
   }

   void testWithDatesFromSeparateDateObjects() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      expect:
      dateRange.startDate == startDate
      dateRange.endDate == endDate
   }

   void testWithDatesFromSeparateDateObjectsNullStartDate() {

      when:
      ArbitraryDateRange.of(null, LocalDate.of(2012, 1, 7))

      then:
      thrown IllegalArgumentException
   }

   void testWithDatesFromSeparateDateObjectsNullEndDate() {

      when:
      ArbitraryDateRange.of(LocalDate.of(2012, 1, 7), null)

      then:
      thrown IllegalArgumentException
   }

   void testGetDates() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)
      def datesList = dateRange.dates

      expect:
      datesList.size() == 7
      datesList.get(0) == LocalDate.of(2012, 1, 1)
      datesList.get(1) == LocalDate.of(2012, 1, 2)
      datesList.get(2) == LocalDate.of(2012, 1, 3)
      datesList.get(3) == LocalDate.of(2012, 1, 4)
      datesList.get(4) == LocalDate.of(2012, 1, 5)
      datesList.get(5) == LocalDate.of(2012, 1, 6)
      datesList.get(6) == LocalDate.of(2012, 1, 7)
   }

   void testGetAtIndex() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      expect:
      dateRange.getDate(0) == LocalDate.of(2012, 1, 1)
      dateRange.getDate(1) == LocalDate.of(2012, 1, 2)
      dateRange.getDate(2) == LocalDate.of(2012, 1, 3)
      dateRange.getDate(3) == LocalDate.of(2012, 1, 4)
      dateRange.getDate(4) == LocalDate.of(2012, 1, 5)
      dateRange.getDate(5) == LocalDate.of(2012, 1, 6)
      dateRange.getDate(6) == LocalDate.of(2012, 1, 7)
   }

   void testGetAtIndexOutOfBounds() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDate(12)

      then:
      thrown IndexOutOfBoundsException
   }

   void testGetDatesForDayOfWeek() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      def listOfDates = dateRange.getDatesForDayOfWeek(DayOfWeek.THURSDAY)

      expect:
      listOfDates.size() == 4
      listOfDates.get(0) == LocalDate.of(2012, 1, 5)
      listOfDates.get(1) == LocalDate.of(2012, 1, 12)
      listOfDates.get(2) == LocalDate.of(2012, 1, 19)
      listOfDates.get(3) == LocalDate.of(2012, 1, 26)
   }

   void testContainsDate() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      expect:
      dateRange.containsDate(startDate) == true
      dateRange.containsDate(endDate) == true
      dateRange.containsDate(LocalDate.of(2010, 1, 1)) == false
      dateRange.containsDate(null) == false
   }

   void testContainsPeriod() {

      def startDate = LocalDate.of(2012, 1, 8)
      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)
      def dateRangeBefore = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 7))
      def dateRangeAfter = ArbitraryDateRange.of(LocalDate.of(2012, 1, 15), LocalDate.of(2012, 1, 21))
      def dateRangeOverlapStart = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 8))
      def dateRangeOverlapEnd = ArbitraryDateRange.of(LocalDate.of(2012, 1, 14), LocalDate.of(2012, 1, 21))

      expect:
      dateRange.containsDateRange(dateRange) == true
      dateRange.containsDateRange(dateRangeBefore) == false
      dateRange.containsDateRange(dateRangeAfter) == false
      dateRange.containsDateRange(dateRangeOverlapStart) == false
      dateRange.containsDateRange(dateRangeOverlapEnd) == false
      dateRange.containsDateRange(null) == false
   }

   void testOverlaps() {

      def startDate = LocalDate.of(2012, 1, 8)
      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)
      def dateRangeBefore = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 7))
      def dateRangeAfter = ArbitraryDateRange.of(LocalDate.of(2012, 1, 15), LocalDate.of(2012, 1, 21))
      def dateRangeOverlapStart = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 8))
      def dateRangeOverlapEnd = ArbitraryDateRange.of(LocalDate.of(2012, 1, 14), LocalDate.of(2012, 1, 21))

      expect:
      dateRange.overlaps(dateRange) == true
      dateRange.overlaps(dateRangeBefore) == false
      dateRange.overlaps(dateRangeAfter) == false
      dateRange.overlaps(dateRangeOverlapStart) == true
      dateRange.overlaps(dateRangeOverlapEnd) == true
      dateRange.overlaps((DateRange)null) == false
   }

   void testOverlapsDateRanges() {

      def dateRange = ArbitraryDateRange.of(LocalDate.of(2013, 5, 1), LocalDate.of(2013, 5, 31))

      when:
      def result = dateRange.overlapsDateRanges(dateRanges)

      then:
      result == HasOverlap


      where:
      dateRanges   | HasOverlap | scenario
      testDates0() | false      | "Empty List"
      testDates1() | false      | "Single range, no overlap"
      testDates2() | true       | "Single range, with overlap"
      testDates3() | true       | "5 ranges, overlaps on  both the 4th and 5th range"
      testDates4() | false      | "5 ranges, no overlaps"

   }

   void testGetNumberOfDaysInRange() {

      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      expect:
      dateRange.numberOfDaysInRange == expectedResult

      where:
      startDate                 | endDate                     | expectedResult
      LocalDate.of(2012, 1, 1) | LocalDate.of(2012, 1, 7)   | 7
      LocalDate.of(2012, 1, 1) | LocalDate.of(2012, 12, 31) | 366
      LocalDate.of(2013, 1, 1) | LocalDate.of(2013, 12, 31) | 365
   }

   void testGetDateRangeContainingDateBefore() {

      def startDate = LocalDate.of(2012, 1, 15)
      def endDate = LocalDate.of(2012, 1, 21)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def result = dateRange.getDateRangeContainingDate(LocalDate.of(2012, 1, 4))

      then:
      result.startDate == LocalDate.of(2012, 1, 1)
      result.endDate == LocalDate.of(2012, 1, 7)
   }

   void testGetDateRangeContainingDateAfter() {

      def startDate = LocalDate.of(2012, 1, 15)
      def endDate = LocalDate.of(2012, 1, 21)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def result = dateRange.getDateRangeContainingDate(LocalDate.of(2012, 1, 24))

      then:
      result.startDate == LocalDate.of(2012, 1, 22)
      result.endDate == LocalDate.of(2012, 1, 28)
   }

   void testGetPriorDateRange() {

      def startDate = LocalDate.of(2012, 1, 8)
      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def priorDateRange = dateRange.priorDateRange

      then:
      priorDateRange.startDate == LocalDate.of(2012, 1, 1)
      priorDateRange.endDate == LocalDate.of(2012, 1, 7)
   }

   void testGetNextDateRange() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def nextDateRange = dateRange.nextDateRange

      then:
      nextDateRange.startDate == LocalDate.of(2012, 1, 8)
      nextDateRange.endDate == LocalDate.of(2012, 1, 14)
   }

   void testGetPriorDateRangeWithNumberOfPeriodsBack() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def priorDateRange = dateRange.getPriorDateRange(numberBack)

      then:
      priorDateRange.startDate == expectedStartDate
      priorDateRange.endDate == expectedEndDate

      where:
      numberBack | expectedStartDate          | expectedEndDate
      0          | LocalDate.of(2012, 1, 22) | LocalDate.of(2012, 1, 28)
      1          | LocalDate.of(2012, 1, 15) | LocalDate.of(2012, 1, 21)
      2          | LocalDate.of(2012, 1, 8)  | LocalDate.of(2012, 1, 14)
      3          | LocalDate.of(2012, 1, 1)  | LocalDate.of(2012, 1, 7)
   }

   void testGetNextDateRangeWithNumberOfPeriodsForward() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def nextDateRange = dateRange.getNextDateRange(numberForward)

      then:
      nextDateRange.startDate == expectedStartDate
      nextDateRange.endDate == expectedEndDate

      where:
      numberForward | expectedStartDate          | expectedEndDate
      0             | LocalDate.of(2012, 1, 1)  | LocalDate.of(2012, 1, 7)
      1             | LocalDate.of(2012, 1, 8)  | LocalDate.of(2012, 1, 14)
      2             | LocalDate.of(2012, 1, 15) | LocalDate.of(2012, 1, 21)
      3             | LocalDate.of(2012, 1, 22) | LocalDate.of(2012, 1, 28)
   }

   void testGetPriorDateRangeWithNegativeNumberOfPeriodsBack() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getPriorDateRange(-1)

      then:
      thrown IllegalArgumentException
   }

   void testGetNextDateRangeWithNegativeNumberOfPeriodsForward() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getNextDateRange(-1)

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesBefore() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesBefore(3)

      then:
      dateRangesBefore.size() == 3
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 1)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 7)
      dateRangesBefore.get(1).startDate == LocalDate.of(2012, 1, 8)
      dateRangesBefore.get(1).endDate == LocalDate.of(2012, 1, 14)
      dateRangesBefore.get(2).startDate == LocalDate.of(2012, 1, 15)
      dateRangesBefore.get(2).endDate == LocalDate.of(2012, 1, 21)
   }

   void testGetDateRangesBeforeWithZeroBefore() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesBefore(0)

      then:
      dateRangesBefore.size() == 0
   }

   void testGetDateRangesBeforeWithNegativeNumber() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesBefore(-1)

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesBeforeIncludingSelf() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesBeforeIncludingSelf(3)

      then:
      dateRangesBefore.size() == 4
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 1)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 7)
      dateRangesBefore.get(1).startDate == LocalDate.of(2012, 1, 8)
      dateRangesBefore.get(1).endDate == LocalDate.of(2012, 1, 14)
      dateRangesBefore.get(2).startDate == LocalDate.of(2012, 1, 15)
      dateRangesBefore.get(2).endDate == LocalDate.of(2012, 1, 21)
      dateRangesBefore.get(3).startDate == LocalDate.of(2012, 1, 22)
      dateRangesBefore.get(3).endDate == LocalDate.of(2012, 1, 28)
   }

   void testGetDateRangesBeforeIncludingSelfWithZeroBefore() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesBeforeIncludingSelf(0)

      then:
      dateRangesBefore.size() == 1
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 22)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 28)
   }

   void testGetDateRangesBeforeIncludingSelfWithNegativeNumber() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesBeforeIncludingSelf(-1)

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesAfter() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesAfter(3)

      then:
      dateRangesBefore.size() == 3
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 8)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 14)
      dateRangesBefore.get(1).startDate == LocalDate.of(2012, 1, 15)
      dateRangesBefore.get(1).endDate == LocalDate.of(2012, 1, 21)
      dateRangesBefore.get(2).startDate == LocalDate.of(2012, 1, 22)
      dateRangesBefore.get(2).endDate == LocalDate.of(2012, 1, 28)
   }

   void testGetDateRangesAfterWithZeroAfter() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesAfter(0)

      then:
      dateRangesBefore.size() == 0
   }

   void testGetDateRangesAfterWithNegativeNumber() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesAfter(-1)

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesAfterIncludingSelf() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesAfterIncludingSelf(3)

      then:
      dateRangesBefore.size() == 4
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 1)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 7)
      dateRangesBefore.get(1).startDate == LocalDate.of(2012, 1, 8)
      dateRangesBefore.get(1).endDate == LocalDate.of(2012, 1, 14)
      dateRangesBefore.get(2).startDate == LocalDate.of(2012, 1, 15)
      dateRangesBefore.get(2).endDate == LocalDate.of(2012, 1, 21)
      dateRangesBefore.get(3).startDate == LocalDate.of(2012, 1, 22)
      dateRangesBefore.get(3).endDate == LocalDate.of(2012, 1, 28)
   }

   void testGetDateRangesAfterIncludingSelfWithZeroAfter() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesAfterIncludingSelf(0)

      then:
      dateRangesBefore.size() == 1
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 1)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 7)
   }

   void testGetDateRangesAfterIncludingSelfWithNegativeAfter() {

      def startDate = LocalDate.of(2012, 1, 1)
      def endDate = LocalDate.of(2012, 1, 7)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesAfterIncludingSelf(-1)

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesBeforeAndAfter() {

      def startDate = LocalDate.of(2012, 1, 15)
      def endDate = LocalDate.of(2012, 1, 21)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesBeforeAndAfter(2, 3)

      then:
      dateRangesBefore.size() == 6
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 1)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 7)
      dateRangesBefore.get(1).startDate == LocalDate.of(2012, 1, 8)
      dateRangesBefore.get(1).endDate == LocalDate.of(2012, 1, 14)
      dateRangesBefore.get(2).startDate == LocalDate.of(2012, 1, 15)
      dateRangesBefore.get(2).endDate == LocalDate.of(2012, 1, 21)
      dateRangesBefore.get(3).startDate == LocalDate.of(2012, 1, 22)
      dateRangesBefore.get(3).endDate == LocalDate.of(2012, 1, 28)
      dateRangesBefore.get(4).startDate == LocalDate.of(2012, 1, 29)
      dateRangesBefore.get(4).endDate == LocalDate.of(2012, 2, 4)
      dateRangesBefore.get(5).startDate == LocalDate.of(2012, 2, 5)
      dateRangesBefore.get(5).endDate == LocalDate.of(2012, 2, 11)
   }

   void testGetDateRangesBeforeAndAfterWithNegativeBefore() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesBeforeAndAfter(-1, 3)

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesBeforeAndAfterWithNegativeAfter() {

      def startDate = LocalDate.of(2012, 1, 22)
      def endDate = LocalDate.of(2012, 1, 28)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesBeforeAndAfter(3, -1)

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesContainingDates() {

      def startDate = LocalDate.of(2012, 1, 15)
      def endDate = LocalDate.of(2012, 1, 21)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      def dateRangesBefore = dateRange.getDateRangesContainingDates(LocalDate.of(2012, 1, 5), LocalDate.of(2012, 1, 24))

      then:
      dateRangesBefore.size() == 4
      dateRangesBefore.get(0).startDate == LocalDate.of(2012, 1, 1)
      dateRangesBefore.get(0).endDate == LocalDate.of(2012, 1, 7)
      dateRangesBefore.get(1).startDate == LocalDate.of(2012, 1, 8)
      dateRangesBefore.get(1).endDate == LocalDate.of(2012, 1, 14)
      dateRangesBefore.get(2).startDate == LocalDate.of(2012, 1, 15)
      dateRangesBefore.get(2).endDate == LocalDate.of(2012, 1, 21)
      dateRangesBefore.get(3).startDate == LocalDate.of(2012, 1, 22)
      dateRangesBefore.get(3).endDate == LocalDate.of(2012, 1, 28)
   }

   void testGetDateRangesContainingDatesFromDateNull() {

      def startDate = LocalDate.of(2012, 1, 15)
      def endDate = LocalDate.of(2012, 1, 21)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesContainingDates(null, LocalDate.of(2012, 1, 24))

      then:
      thrown IllegalArgumentException
   }

   void testGetDateRangesContainingDatesToDateNull() {

      def startDate = LocalDate.of(2012, 1, 15)
      def endDate = LocalDate.of(2012, 1, 21)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      when:
      dateRange.getDateRangesContainingDates(LocalDate.of(2012, 1, 5), null)

      then:
      thrown IllegalArgumentException
   }

   void testEquals() {

      def dateRange1 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 7))
      def dateRange2 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 7))
      def dateRange3 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 8))
      def dateRange4 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 2), LocalDate.of(2012, 1, 7))
      def dateRange5 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 4), LocalDate.of(2012, 1, 6))
      def dateRange6 = WeeklyDateRange.withEndDate(LocalDate.of(2012, 1, 7))

      expect:
      dateRange1.equals(null) == false
      dateRange1.equals("ABC") == false
      dateRange1.equals(dateRange1) == true
      dateRange1.equals(dateRange2) == true
      dateRange1.equals(dateRange3) == false
      dateRange1.equals(dateRange4) == false
      dateRange1.equals(dateRange5) == false
      dateRange1.equals(dateRange6) == true
   }

   void testHashCode() {

      def startDate = LocalDate.of(2012, 1, 8)
      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      int expectedHashCode = 31 * startDate.hashCode() + endDate.hashCode()

      expect:
      dateRange.hashCode() == expectedHashCode
   }

   void testCompareTo() {

      def dateRange1 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 8), LocalDate.of(2012, 1, 14))
      def dateRange2 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 8), LocalDate.of(2012, 1, 14))
      def dateRange3 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 1, 7))
      def dateRange4 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 15), LocalDate.of(2012, 1, 21))
      def dateRange5 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 8), LocalDate.of(2012, 1, 13))
      def dateRange6 = ArbitraryDateRange.of(LocalDate.of(2012, 1, 8), LocalDate.of(2012, 1, 15))

      expect:
      dateRange1.compareTo(dateRange1) == 0
      dateRange1.compareTo(dateRange2) == 0
      dateRange1.compareTo(null) == -1
      dateRange1.compareTo(dateRange3) > 0
      dateRange1.compareTo(dateRange4) < 0
      dateRange1.compareTo(dateRange5) > 0
      dateRange1.compareTo(dateRange6) < 0
   }

   void testIterator() {

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

   void testClone() {

      def startDate = LocalDate.of(2012, 1, 8)
      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      expect:
      PerformClone.performClone(dateRange) == dateRange
   }

   @Ignore
   void testToString() {

      def startDate = LocalDate.of(2012, 1, 8)
      def endDate = LocalDate.of(2012, 1, 14)
      def dateRange = ArbitraryDateRange.of(startDate, endDate)

      expect:
      dateRange.toString() == "1/8/12 - 1/14/12"
   }

   def testDates0() {
      new ArrayList<DateRange>()
   }

   def testDates1() {

      new ArrayList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 12, 31))])
   }

   def testDates2() {

      new ArrayList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2013, 01, 01), LocalDate.of(2013, 5, 16))])
   }

   def testDates3() {

      new ArrayList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 01, 31)),
                                ArbitraryDateRange.of(LocalDate.of(2012, 03, 01), LocalDate.of(2012, 03, 31)),
                                ArbitraryDateRange.of(LocalDate.of(2012, 04, 01), LocalDate.of(2012, 04, 30)),
                                ArbitraryDateRange.of(LocalDate.of(2013, 04, 01), LocalDate.of(2013, 05, 01)),
                                ArbitraryDateRange.of(LocalDate.of(2013, 05, 31), LocalDate.of(2013, 06, 30))])
   }

   def testDates4() {

      new ArrayList<DateRange>([ArbitraryDateRange.of(LocalDate.of(2012, 01, 01), LocalDate.of(2012, 01, 31)),
                                ArbitraryDateRange.of(LocalDate.of(2012, 03, 01), LocalDate.of(2012, 03, 31)),
                                ArbitraryDateRange.of(LocalDate.of(2012, 04, 01), LocalDate.of(2012, 04, 30)),
                                ArbitraryDateRange.of(LocalDate.of(2012, 04, 01), LocalDate.of(2012, 05, 01)),
                                ArbitraryDateRange.of(LocalDate.of(2012, 05, 31), LocalDate.of(2012, 06, 30))])
   }
}
