package com.stano.daterange;

import java.util.Comparator;

public class DateRangeStartComparator implements Comparator<DateRange> {

   public int compare(DateRange dateRange1, DateRange dateRange2) {

      return dateRange1.getStartDate().compareTo(dateRange2.getStartDate());
   }
}
