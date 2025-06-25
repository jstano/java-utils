package com.stano.daterange;

import java.util.LinkedList;
import java.util.List;

public final class DateRangeUtils {

   /**
    * Check if any date range in the list is partially contained in another date range in the same list.
    *
    * @param dateRanges The list of DateRanges to check
    * @return true if the dateRange is partially contained in any of the ranges in the list; false otherwise
    */
   public static boolean dateRangeListContainsOverlaps(List<DateRange> dateRanges) {

      LinkedList<DateRange> dateRangeList = new LinkedList<DateRange>(dateRanges);

      if (dateRangeList.size() > 1) {

         DateRange firstRange = dateRangeList.removeFirst();

         if (firstRange.overlapsDateRanges(dateRangeList)) {

            return true;
         }
         else {

            if (dateRangeListContainsOverlaps(dateRangeList)) {
               return true;
            }
         }
      }

      return false;
   }

   private DateRangeUtils() {

   }
}
