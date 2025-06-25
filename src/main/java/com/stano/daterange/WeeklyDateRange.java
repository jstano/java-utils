package com.stano.daterange;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class WeeklyDateRange extends DefaultDateRange {

   public static WeeklyDateRange withEndDate(LocalDate endDate) {

      return new WeeklyDateRange(endDate.minusDays(6), endDate);
   }

   public static WeeklyDateRange withTargetDate(LocalDate targetDate, DayOfWeek endDayOfWeek) {

      return new WeeklyDateRange(targetDate, endDayOfWeek);
   }

   public static WeeklyDateRange withStartDate(LocalDate startDate) {

      return new WeeklyDateRange(startDate, startDate.plusDays(6));
   }

   @Override
   protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {

      return new WeeklyDateRange(startDate, endDate);
   }

   private WeeklyDateRange(LocalDate startDate, LocalDate endDate) {

      super(startDate, endDate);
   }

   private WeeklyDateRange(LocalDate targetDate, DayOfWeek endDayOfWeek) {

      super(
              LocalDate.of(targetDate.getYear(), targetDate.getMonthValue(), targetDate.getDayOfMonth())
                       .plusDays(calculateDayOfWeekOffset(targetDate, endDayOfWeek) - 7 + 1),
              targetDate.plusDays(calculateDayOfWeekOffset(targetDate, endDayOfWeek))
      );
   }

   static private int calculateDayOfWeekOffset(LocalDate endDate, DayOfWeek endDayOfWeek) {

      int dayOfWeekOffset = endDayOfWeek.getValue() - endDate.getDayOfWeek().getValue();
      if (dayOfWeekOffset < 0) {
         dayOfWeekOffset += 7;
      }
      return dayOfWeekOffset;
   }
}
