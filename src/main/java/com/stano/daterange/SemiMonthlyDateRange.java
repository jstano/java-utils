package com.stano.daterange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class SemiMonthlyDateRange extends DefaultDateRange {

   private static final int FIFTEENTH_OF_MONTH = 15;
   private static final int SIXTEENTH_OF_MONTH = 16;

   public static SemiMonthlyDateRange withEndDate(LocalDate endDate) {

      return new SemiMonthlyDateRange(calculateStartDateFromEndDate(endDate), endDate);
   }

   @Override
   public DateRange getPriorDateRange() {

      // period days are either 1 to 15 or 16 to last day of month
      if (endDate.equals(atLastDayOfMonth(endDate))) {
         return new SemiMonthlyDateRange(endDate.withDayOfMonth(1), endDate.withDayOfMonth(FIFTEENTH_OF_MONTH));
      }

      LocalDate newEndDate = getStartDate().minusDays(1);

      return new SemiMonthlyDateRange(newEndDate.withDayOfMonth(SIXTEENTH_OF_MONTH), newEndDate);
   }

   @Override
   public DateRange getNextDateRange() {

      // period days are either 1 to 15 or 16 to last day of month
      if (endDate.equals(atLastDayOfMonth(endDate))) {
         LocalDate newStartDate = getEndDate().plusDays(1);

         return new SemiMonthlyDateRange(newStartDate, newStartDate.withDayOfMonth(FIFTEENTH_OF_MONTH));
      }

      return new SemiMonthlyDateRange(endDate.plusDays(1),
                                      atLastDayOfMonth(endDate));
   }

   @Override
   protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {

      return new SemiMonthlyDateRange(startDate, endDate);
   }

   private SemiMonthlyDateRange(LocalDate startDate, LocalDate endDate) {

      super(startDate, endDate);
   }

   private static LocalDate atLastDayOfMonth(LocalDate localDate) {

      return localDate.withDayOfMonth(localDate.lengthOfMonth());
   }

   private static LocalDate calculateStartDateFromEndDate(LocalDate endDate) {

      LocalDate lastDayOfMonth = atLastDayOfMonth(endDate);

      // period days are either 1 to 15 or 16 to last day of month
      if (endDate.equals(lastDayOfMonth)) {
         return endDate.withDayOfMonth(SIXTEENTH_OF_MONTH);
      }

      if (endDate.getDayOfMonth() == FIFTEENTH_OF_MONTH) {
         return endDate.withDayOfMonth(1);
      }

      throw new IllegalArgumentException(String.format("The date (%s) must end on either the 15th of the month or the last day of the month (%d)",
                                                       endDate.format(DateTimeFormatter.ISO_DATE),
                                                       lastDayOfMonth.getDayOfMonth()));
   }
}
