package com.stano.daterange;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * This interface represents a range of dates.
 */
public interface DateRange extends Comparable<DateRange>, Iterable<LocalDate>, Cloneable, Serializable {

   /**
    * Get the starting date in the range.
    *
    * @return The start date of the range.
    */
   LocalDate getStartDate();

   /**
    * Get the ending date in the range.
    *
    * @return The end date of the range.
    */
   LocalDate getEndDate();

   /**
    * Get the dates contained in the range in a list.
    *
    * @return The list of dates contained in the range.
    */
   List<LocalDate> getDates();

   /**
    * Get the LocalDate object at the specified index. If the index is outside of the bounds
    * an ArrayIndexOutOfBounds exception will be thrown.
    *
    * @param index The index.
    * @return The LocalDate object.
    */
   LocalDate getDate(int index);

   /**
    * Get a list of dates from the range that match the specified DayOfWeek
    *
    * @param dayOfWeek The DayOfWeek to match
    * @return The list of dates that matches the specified DayOfWeek
    */
   List<LocalDate> getDatesForDayOfWeek(DayOfWeek dayOfWeek);

   /**
    * Check if a date is contained in the range.
    *
    * @param date The date to check
    * @return true if the date is contained in the range; false otherwise
    */
   boolean containsDate(LocalDate date);

   /**
    * Check if a date range is fully contained in the range.
    *
    * @param dateRange The DateRange to check
    * @return true if the dateRange is fully contained in the range; false otherwise
    */
   boolean containsDateRange(DateRange dateRange);

   /**
    * Check if a date range is partially contained in the range.
    *
    * @param dateRange The DateRange to check
    * @return true if the dateRange is partially contained in the range; false otherwise
    */
   boolean overlaps(DateRange dateRange);

   /**
    * Check if a date range is partially contained in a list of date ranges.
    *
    * @param dateRanges The list of DateRanges to check
    * @return true if the dateRange is partially contained in any of the ranges in the list; false otherwise
    */
   boolean overlapsDateRanges(List<DateRange> dateRanges);

   /**
    * Get the number of days in the range.
    *
    * @return The number of days in the range.
    */
   int getNumberOfDaysInRange();

   /**
    * Get the DateRange that contains the specified date.
    *
    * @param date The date
    * @return The DateRange that contains the specified date.
    */
   DateRange getDateRangeContainingDate(LocalDate date);

   /**
    * Get a DateRange that represents the prior range to this dateRange.
    *
    * @return The prior DateRange
    */
   DateRange getPriorDateRange();

   /**
    * Get a DateRange that represents the next range to this dateRange.
    *
    * @return The next DateRange
    */
   DateRange getNextDateRange();

   /**
    * Get the DateRange that represents a date range that is N ranges prior from
    * the current DateRange.
    *
    * @param numberBack The number of ranges to go back
    * @return The DateRange object
    */
   DateRange getPriorDateRange(int numberBack);

   /**
    * Get the DateRange that represents a date range that is N ranges forward from
    * the current DateRange.
    *
    * @param numberForward The number of ranges to go forward
    * @return The DateRange object
    */
   DateRange getNextDateRange(int numberForward);

   /**
    * Get a list of N DateRanges before this DateRange, not including this DateRange.
    *
    * @param numberBefore The number of date ranges before this dateRange to include in the list.
    * @return The list of DateRange objects.
    */
   List<DateRange> getDateRangesBefore(int numberBefore);

   /**
    * Get a list of N DateRanges before this DateRange, including this DateRange.
    *
    * @param numberBefore The number of date ranges before this dateRange to include in the list.
    * @return The list of DateRange objects.
    */
   List<DateRange> getDateRangesBeforeIncludingSelf(int numberBefore);

   /**
    * Get a list of N DateRanges after this DateRange, not including this DateRange.
    *
    * @param numberAfter The number of date ranges after this dateRange to include in the list.
    * @return The list of DateRange objects.
    */
   List<DateRange> getDateRangesAfter(int numberAfter);

   /**
    * Get a list of N DateRanges after this DateRange, including this DateRange.
    *
    * @param numberAfter The number of date ranges after this dateRange to include in the list.
    * @return The list of DateRange objects.
    */
   List<DateRange> getDateRangesAfterIncludingSelf(int numberAfter);

   /**
    * Get a list of DateRanges that includes the current DateRange, N DateRanges before
    * this DateRange and N DateRanges after this date range.
    *
    * @param numberBefore The number of date ranges before this date range to include in the list
    * @param numberAfter  The number of date ranges after this date range to include in the list
    * @return The list of DateRange objects.
    */
   List<DateRange> getDateRangesBeforeAndAfter(int numberBefore, int numberAfter);

   /**
    * Get a list of DateRanges that contain the specified dates.
    *
    * @param fromDate The earliest date.
    * @param toDate   The latest date.
    * @return The list of DateRange objects.
    */
   List<DateRange> getDateRangesContainingDates(LocalDate fromDate, LocalDate toDate);
}
