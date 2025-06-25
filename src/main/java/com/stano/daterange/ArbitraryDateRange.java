package com.stano.daterange;

import java.time.LocalDate;

public class ArbitraryDateRange extends DefaultDateRange {

   public static DateRange of(LocalDate date) {

      return new ArbitraryDateRange(date, date);
   }

   public static DateRange of(LocalDate startDate, LocalDate endDate) {

      return new ArbitraryDateRange(startDate, endDate);
   }

   @Override
   protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {

      return new ArbitraryDateRange(startDate, endDate);
   }

   private ArbitraryDateRange(LocalDate startDate, LocalDate endDate) {

      super(startDate, endDate);
   }
}
