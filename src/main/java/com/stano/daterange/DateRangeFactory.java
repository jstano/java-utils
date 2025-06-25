package com.stano.daterange;

import java.time.LocalDate;

public final class DateRangeFactory {

   public static DateRange getInstance(DateRangeConfig dateRangeConfig, LocalDate endDate) {

      DateRangeType dateRangeType = dateRangeConfig.getDateRangeType();
      int startDayOfMonth = dateRangeConfig.getStartDayOfMonth();

      if (dateRangeType != null) {
         switch (dateRangeType) {
            case WEEKLY:
               return WeeklyDateRange.withEndDate(endDate);

            case BI_WEEKLY:
               return BiWeeklyDateRange.withEndDate(endDate);

            case SEMI_MONTHLY:
               return SemiMonthlyDateRange.withEndDate(endDate);

            case MONTHLY:
               return MonthlyDateRange.withEndDateAndStartDayOfMonth(endDate, startDayOfMonth);
         }
      }

      throw new IllegalArgumentException("The dateRangeType must not be null");
   }

   private DateRangeFactory() {

   }
}
