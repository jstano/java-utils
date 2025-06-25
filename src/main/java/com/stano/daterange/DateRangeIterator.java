package com.stano.daterange;

import java.time.LocalDate;
import java.util.Iterator;

public class DateRangeIterator implements Iterator<LocalDate> {

   public DateRangeIterator(DateRange dateRange) {

      this.dateRange = dateRange;
      this.nextDate = dateRange.getStartDate();
   }

   @Override
   public boolean hasNext() {

      return nextDate != null;
   }

   @Override
   public LocalDate next() {

      LocalDate result = nextDate;

      if (shouldTraverseForward()) {
         traverseForward();
      }
      else {
         traverseBackward();
      }

      return result;
   }

   private void traverseBackward() {

      nextDate = nextDate.minusDays(1);
      if (nextDate.isBefore(dateRange.getEndDate())) {
         nextDate = null;
      }
   }

   private void traverseForward() {

      nextDate = nextDate.plusDays(1);
      if (nextDate.isAfter(dateRange.getEndDate())) {
         nextDate = null;
      }
   }

   private boolean shouldTraverseForward() {

      return dateRange.getStartDate().compareTo(dateRange.getEndDate()) <= 0;
   }

   @Override
   public void remove() {

      throw new UnsupportedOperationException("The remove method is not supported by DateRangeIterator.");
   }

   private final DateRange dateRange;

   private LocalDate nextDate;
}
