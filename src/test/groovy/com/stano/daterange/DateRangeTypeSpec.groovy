package com.stano.daterange

import spock.lang.Specification

class DateRangeTypeSpec extends Specification {
  void testGetPeriodsPerYear() {
    expect:
    DateRangeType.WEEKLY.periodsPerYear == 52
    DateRangeType.BI_WEEKLY.periodsPerYear == 26
    DateRangeType.SEMI_MONTHLY.periodsPerYear == 24
    DateRangeType.MONTHLY.periodsPerYear == 12
  }

  void testIsWeekBased() {
    expect:
    DateRangeType.WEEKLY.weekBased == true
    DateRangeType.BI_WEEKLY.weekBased == true
    DateRangeType.SEMI_MONTHLY.weekBased == false
    DateRangeType.MONTHLY.weekBased == false
  }
}
