package com.stano.daterange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class MonthlyDateRange extends DefaultDateRange {

   private final int startDayOfMonth;

   public static MonthlyDateRange withEndDateOnFirstOfMonth(LocalDate endDate) {

      return withEndDateAndStartDayOfMonth(endDate, 1);
   }

   public static MonthlyDateRange withEndDateAndStartDayOfMonth(LocalDate endDate, int startDayOfMonth) {

      return new MonthlyDateRange(calculateStartDateFromEndDate(endDate, startDayOfMonth), endDate, startDayOfMonth);
   }

   @Override
   public DateRange getPriorDateRange() {

      if (startDayOfMonth == 1) {
         LocalDate newEndDate = startDate.minusDays(1);

         return new MonthlyDateRange(newEndDate.withDayOfMonth(1),
                                     newEndDate,
                                     startDayOfMonth);
      }

      return new MonthlyDateRange(startDate.minusMonths(1),
                                  startDate.minusDays(1),
                                  startDayOfMonth);
   }

   @Override
   public DateRange getNextDateRange() {

      if (startDayOfMonth == 1) {
         LocalDate newStartDate = endDate.plusDays(1);

         return new MonthlyDateRange(newStartDate,
                                     newStartDate.withDayOfMonth(newStartDate.getMonth().length(newStartDate.isLeapYear())),
                                     startDayOfMonth);
      }

      return new MonthlyDateRange(endDate.plusDays(1),
                                  endDate.plusMonths(1),
                                  startDayOfMonth);
   }

   @Override
   protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {

      verifyParameters(endDate, startDayOfMonth);

      return new MonthlyDateRange(startDate, endDate, startDayOfMonth);
   }

   private MonthlyDateRange(LocalDate startDate, LocalDate endDate, int startDayOfMonth) {

      super(startDate, endDate);

      this.startDayOfMonth = startDayOfMonth;
   }

   private static LocalDate calculateStartDateFromEndDate(LocalDate endDate, int startDayOfMonth) {

      verifyParameters(endDate, startDayOfMonth);

      if (startDayOfMonth == 1) {
         return endDate.withDayOfMonth(1);
      }

      return endDate.plusDays(1).minusMonths(1);
   }

   private static void verifyParameters(LocalDate endDate, int startDayOfMonth) {

      if (endDate.plusDays(1).getDayOfMonth() != startDayOfMonth) {
         throw new IllegalArgumentException(String.format("The endDate (%s) and startDayOfMonth (%d) are not correct",
                                                          endDate.format(DateTimeFormatter.ISO_DATE),
                                                          startDayOfMonth));
      }
   }
}
