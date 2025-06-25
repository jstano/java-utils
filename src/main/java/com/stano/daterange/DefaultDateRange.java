package com.stano.daterange;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class DefaultDateRange implements DateRange {

   protected final LocalDate startDate;
   protected final LocalDate endDate;
   protected final int numberOfDaysInRange;

   private transient List<LocalDate> dates;

   @Override
   public LocalDate getStartDate() {

      return startDate;
   }

   @Override
   public LocalDate getEndDate() {

      return endDate;
   }

   @Override
   public List<LocalDate> getDates() {

      if (dates == null) {
         dates = new ArrayList<>(getNumberOfDaysInRange());

         for (LocalDate date : this) {
            dates.add(date);
         }
      }

      return dates;
   }

   @Override
   public LocalDate getDate(int index) {

      return getDates().get(index);
   }

   @Override
   public List<LocalDate> getDatesForDayOfWeek(DayOfWeek dayOfWeek) {

      List<LocalDate> dates = new ArrayList<>(getNumberOfDaysInRange());

      for (LocalDate date : this) {
         if (date.getDayOfWeek() == dayOfWeek) {
            dates.add(date);
         }
      }

      return dates;
   }

   @Override
   public boolean containsDate(LocalDate date) {

      if (date == null) {
         return false;
      }

      return startDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0;
   }

   @Override
   public boolean containsDateRange(DateRange dateRange) {

      if (dateRange == null) {
         return false;
      }

      return dateRange.getStartDate().compareTo(startDate) >= 0 && dateRange.getEndDate().compareTo(endDate) <= 0;
   }

   @Override
   public boolean overlaps(DateRange dateRange) {

      if (dateRange == null) {
         return false;
      }

      return startDate.compareTo(dateRange.getEndDate()) <= 0 && endDate.compareTo(dateRange.getStartDate()) >= 0;
   }

   @Override
   public boolean overlapsDateRanges(List<DateRange> dateRanges) {

      for (DateRange listRange : dateRanges) {
         if (overlaps(listRange)) {

            return true;
         }
      }
      return false;
   }

   @Override
   public int getNumberOfDaysInRange() {

      return numberOfDaysInRange;
   }

   @Override
   public DateRange getDateRangeContainingDate(LocalDate date) {

      DateRange dateRange = this;

      while (!dateRange.containsDate(date)) {
         if (date.isAfter(dateRange.getEndDate())) {
            dateRange = dateRange.getNextDateRange();
         }
         else {
            dateRange = dateRange.getPriorDateRange();
         }
      }

      return dateRange;
   }

   @Override
   public DateRange getPriorDateRange() {

      return createNewDateRange(startDate.minusDays(numberOfDaysInRange),
                                endDate.minusDays(numberOfDaysInRange));
   }

   @Override
   public DateRange getNextDateRange() {

      return createNewDateRange(startDate.plusDays(numberOfDaysInRange),
                                endDate.plusDays(numberOfDaysInRange));
   }

   @Override
   public DateRange getPriorDateRange(int numberBack) {

      if (numberBack < 0) {
         throw new IllegalArgumentException("The numberBack cannot be less then 0");
      }

      DateRange dateRange = this;

      for (int i = 0; i < numberBack; i++) {
         dateRange = dateRange.getPriorDateRange();
      }

      return dateRange;
   }

   @Override
   public DateRange getNextDateRange(int numberForward) {

      if (numberForward < 0) {
         throw new IllegalArgumentException("The numberForward cannot be less then 0");
      }

      DateRange dateRange = this;

      for (int i = 0; i < numberForward; i++) {
         dateRange = dateRange.getNextDateRange();
      }

      return dateRange;
   }

   @Override
   public List<DateRange> getDateRangesBefore(int numberBefore) {

      return getDateRangesBeforeImpl(numberBefore, false);
   }

   @Override
   public List<DateRange> getDateRangesBeforeIncludingSelf(int numberBefore) {

      return getDateRangesBeforeImpl(numberBefore, true);
   }

   @Override
   public List<DateRange> getDateRangesAfter(int numberAfter) {

      return getDateRangesAfterImpl(numberAfter, false);
   }

   @Override
   public List<DateRange> getDateRangesAfterIncludingSelf(int numberAfter) {

      return getDateRangesAfterImpl(numberAfter, true);
   }

   @Override
   public List<DateRange> getDateRangesBeforeAndAfter(int numberBefore, int numberAfter) {

      if (numberBefore < 0) {
         throw new IllegalArgumentException("The numberBefore cannot be less then 0");
      }

      if (numberAfter < 0) {
         throw new IllegalArgumentException("The numberAfter cannot be less then 0");
      }

      List<DateRange> dateRanges = new ArrayList<>();

      dateRanges.add(this);

      DateRange dateRange = this;

      for (int i = 0; i < numberBefore; i++) {
         dateRange = dateRange.getPriorDateRange();

         dateRanges.add(dateRange);
      }

      dateRange = this;

      for (int i = 0; i < numberAfter; i++) {
         dateRange = dateRange.getNextDateRange();

         dateRanges.add(dateRange);
      }

      Collections.sort(dateRanges, new DateRangeStartComparator());

      return dateRanges;
   }

   @Override
   public List<DateRange> getDateRangesContainingDates(LocalDate fromDate, LocalDate toDate) {

      if (fromDate == null) {
         throw new IllegalArgumentException("The fromDate parameter cannot be null");
      }

      if (toDate == null) {
         throw new IllegalArgumentException("The toDate parameter cannot be null");
      }

      List<DateRange> dateRanges = new ArrayList<>();

      DateRange dateRange = getDateRangeContainingDate(fromDate);
      dateRanges.add(dateRange);

      while (dateRange.getEndDate().isBefore(toDate)) {
         dateRange = dateRange.getNextDateRange();

         dateRanges.add(dateRange);
      }

      Collections.sort(dateRanges, new DateRangeStartComparator());

      return dateRanges;
   }

   @Override
   public boolean equals(Object o) {

      if (this == o) {
         return true;
      }

      if (o == null || !DateRange.class.isAssignableFrom(o.getClass())) {
         return false;
      }

      DateRange that = (DateRange)o;

      if (!endDate.equals(that.getEndDate())) {
         return false;
      }

      return startDate.equals(that.getStartDate());
   }

   @Override
   public int hashCode() {

      int result = startDate.hashCode();
      result = 31 * result + endDate.hashCode();
      return result;
   }

   @Override
   public int compareTo(DateRange dateRange) {

      if (this == dateRange) {
         return 0;
      }

      if (dateRange == null) {
         return -1;
      }

      int result = startDate.compareTo(dateRange.getStartDate());

      if (result == 0) {
         result = endDate.compareTo(dateRange.getEndDate());
      }

      return result;
   }

   @Override
   public Iterator<LocalDate> iterator() {

      return new DateRangeIterator(this);
   }

   @Override
   protected Object clone() throws CloneNotSupportedException {

      return super.clone();
   }

   @Override
   public String toString() {

      return String.format("%s - %s", startDate.toString(), endDate.toString());
   }

   protected abstract DateRange createNewDateRange(LocalDate startDate, LocalDate endDate);

   protected DefaultDateRange(LocalDate startDate, LocalDate endDate) {

      if (startDate == null) {
         throw new IllegalArgumentException("The startDate parameter must not be null");
      }

      if (endDate == null) {
         throw new IllegalArgumentException("The endDate parameter must not be null");
      }

      this.startDate = startDate;
      this.endDate = endDate;
      this.numberOfDaysInRange = (int)Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays() + 1;
   }

   private List<DateRange> getDateRangesBeforeImpl(int numberBefore, boolean includeSelf) {

      if (numberBefore < 0) {
         throw new IllegalArgumentException("The numberBefore cannot be less then 0");
      }

      List<DateRange> dateRanges = new ArrayList<DateRange>();

      if (includeSelf) {
         dateRanges.add(this);
      }

      DateRange dateRange = this;

      for (int i = 0; i < numberBefore; i++) {
         dateRange = dateRange.getPriorDateRange();

         dateRanges.add(dateRange);
      }

      Collections.sort(dateRanges, new DateRangeStartComparator());

      return dateRanges;
   }

   private List<DateRange> getDateRangesAfterImpl(int numberAfter, boolean includeSelf) {

      if (numberAfter < 0) {
         throw new IllegalArgumentException("The numberAfter cannot be less then 0");
      }

      List<DateRange> dateRanges = new ArrayList<DateRange>();

      if (includeSelf) {
         dateRanges.add(this);
      }

      DateRange daterange = this;

      for (int i = 0; i < numberAfter; i++) {
         daterange = daterange.getNextDateRange();

         dateRanges.add(daterange);
      }

      Collections.sort(dateRanges, new DateRangeStartComparator());

      return dateRanges;
   }
}
