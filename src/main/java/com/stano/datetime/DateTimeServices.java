package com.stano.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Deprecated
/**
 * @deprecated Use the DateTimeProvider class instead.
 */
public class DateTimeServices {

   public LocalDate currentDate() {

      return DateTimeProvider.currentDate();
   }

   public LocalDateTime currentDateTime() {

      return DateTimeProvider.currentDateTime();
   }

   public LocalTime currentTime() {

      return DateTimeProvider.currentTime();
   }
}
