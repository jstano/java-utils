package com.stano.daterange;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class BiWeeklyDateRange extends DefaultDateRange {

   public static BiWeeklyDateRange withEndDate(LocalDate endDate) {

      return new BiWeeklyDateRange(endDate.minusDays(13), endDate);
   }

   public static BiWeeklyDateRange withTargetDate(LocalDate targetDate, DayOfWeek endDayOfWeek) {

      return new BiWeeklyDateRange(targetDate, endDayOfWeek);
   }

   public static BiWeeklyDateRange withStartDate(LocalDate startDate) {

      return new BiWeeklyDateRange(startDate, startDate.plusDays(13));
   }

   @Override
   protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {

      return new BiWeeklyDateRange(startDate, endDate);
   }

   private BiWeeklyDateRange(LocalDate startDate, LocalDate endDate) {

      super(startDate, endDate);
   }

   private BiWeeklyDateRange(LocalDate targetDate, DayOfWeek endDayOfWeek) {

      super(
              LocalDate.of(targetDate.getYear(), targetDate.getMonthValue(), targetDate.getDayOfMonth())
                       .plusDays(calculateDayOfWeekOffset(targetDate, endDayOfWeek) - 14 + 1),
              targetDate.plusDays(calculateDayOfWeekOffset(targetDate, endDayOfWeek))
      );
   }

   static private int calculateDayOfWeekOffset(LocalDate targetDate, DayOfWeek endDayOfWeek) {

      int dayOfWeekOffset = endDayOfWeek.getValue() - targetDate.getDayOfWeek().getValue();
      if (dayOfWeekOffset < 0) {
         dayOfWeekOffset += 7;
      }
      return dayOfWeekOffset;
   }
}
