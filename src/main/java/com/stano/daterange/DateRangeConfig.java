package com.stano.daterange;

public final class DateRangeConfig {

   private final DateRangeType dateRangeType;
   private final int startDayOfMonth;

   public DateRangeConfig(DateRangeType dateRangeType) {

      this.dateRangeType = dateRangeType;
      this.startDayOfMonth = 1;
   }

   public DateRangeConfig(DateRangeType dateRangeType, int startDayOfMonth) {

      this.dateRangeType = dateRangeType;
      this.startDayOfMonth = startDayOfMonth;
   }

   public DateRangeType getDateRangeType() {

      return dateRangeType;
   }

   public int getStartDayOfMonth() {

      return startDayOfMonth;
   }
}
