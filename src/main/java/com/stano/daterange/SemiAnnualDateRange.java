package com.stano.daterange;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public final class SemiAnnualDateRange extends DefaultDateRange {

   public static SemiAnnualDateRange withEndDate(LocalDate endDate) {
      return new SemiAnnualDateRange(endDate.minusMonths(6).plusDays(1), endDate);
   }

   public static SemiAnnualDateRange withStartDate(LocalDate startDate) {
      return new SemiAnnualDateRange(startDate, startDate.plusMonths(6).minusDays(1));
   }

   public static SemiAnnualDateRange withEndDate(LocalDate endDate, boolean snapToMonthEdges) {
      if(snapToMonthEdges) {
         return new SemiAnnualDateRange(endDate.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(5), endDate, snapToMonthEdges);
      }
      else {
         return withEndDate(endDate);
      }
   }

   public static SemiAnnualDateRange withStartDate(LocalDate startDate, boolean snapToMonthEdges) {
      if(snapToMonthEdges) {
         return new SemiAnnualDateRange(startDate, startDate.plusMonths(5), snapToMonthEdges);
      }
      else {
         return withStartDate(startDate);
      }
   }

   @Override
   protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {
      return new SemiAnnualDateRange(startDate, endDate);
   }

   private SemiAnnualDateRange(LocalDate startDate, LocalDate endDate) {
      super(startDate, endDate);
   }

   private SemiAnnualDateRange(LocalDate startDate, LocalDate endDate, boolean snapToMonthEdges) {
      super(
              snapToMonthEdges ? startDate.with(TemporalAdjusters.firstDayOfMonth()) : startDate,
              snapToMonthEdges ? endDate.with(TemporalAdjusters.lastDayOfMonth()) : endDate
      );
      this.snapToMonthEdges = snapToMonthEdges;
   }

   public DateRange getPriorDateRange() {
      LocalDate newStartDate = this.startDate.minusMonths(6),
              newEndDate = this.snapToMonthEdges ?
                      this.endDate.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(6).with(TemporalAdjusters.lastDayOfMonth()) :
                      this.endDate.minusMonths(6);
      return new SemiAnnualDateRange(newStartDate, newEndDate, this.snapToMonthEdges);
   }

   public DateRange getNextDateRange() {
      LocalDate newStartDate = this.startDate.plusMonths(6),
              newEndDate = this.snapToMonthEdges ?
                      this.endDate.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(6).with(TemporalAdjusters.lastDayOfMonth()) :
                      this.endDate.plusMonths(6);
      return new SemiAnnualDateRange(newStartDate, newEndDate, this.snapToMonthEdges);
   }

   public boolean isSnapToMonthEdges() {
      return snapToMonthEdges;
   }

   public void setSnapToMonthEdges(boolean snapToMonthEdges) {
      this.snapToMonthEdges = snapToMonthEdges;
   }

   private boolean snapToMonthEdges = false;
}
