package com.stano.javatime;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

public class TestLocalDate {

  public static void main(String[] args) {
    LocalDate date = LocalDate.now();

    LocalDate.now(ZoneId.of("UTC"));
    LocalDate.now(Clock.systemDefaultZone());
    LocalDate.ofEpochDay(875);
    LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate.ofYearDay(2023, 57);

    LocalDate.of(2023, 12, 31).plusMonths(6).minusMonths(6);

    LocalDateTime.now().atOffset(ZoneOffset.UTC);
    LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC);
    LocalDateTime.now(Clock.systemDefaultZone());
    LocalDateTime.of(2023, 10, 6, 8, 30);
    LocalDateTime.of(2023, 10, 6, 8, 30, 45);
    LocalDateTime.of(2023, 10, 6, 8, 30, 45, 77565);
    LocalDateTime.of(LocalDate.of(2023, 10, 6), LocalTime.of(8, 30, 45));
    LocalDateTime.ofEpochSecond(10000, 99999, ZoneOffset.UTC);
    LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());

    LocalTime.of(8, 30);
    LocalTime.of(18, 30, 45);
    LocalTime.of(18, 30, 45, 85067);
    LocalTime.now();
    LocalTime.now(Clock.systemDefaultZone());
    LocalTime.now(ZoneId.of("UTC"));
    LocalTime.ofSecondOfDay(875);
    LocalTime.ofNanoOfDay(875);

    Duration.between(LocalDate.now(), LocalDate.now());
    Duration.between(LocalDateTime.now(), LocalDateTime.now());
    Duration.between(LocalTime.now(), LocalTime.now());
    Duration.between(OffsetDateTime.now(), OffsetDateTime.now());
    Duration.between(ZonedDateTime.now(), ZonedDateTime.now());
    Duration.between(Instant.now(), Instant.now());

    ZonedDateTime.now().toInstant();

    Instant instant = Instant.now();

    Instant.now();
    Instant.now(Clock.systemDefaultZone());
    Instant.ofEpochSecond(123);
    Instant.ofEpochSecond(123, 456);
    Instant.ofEpochMilli(111);
    Instant.parse("");

    Clock.fixed(Instant.now(), ZoneId.systemDefault());
    Clock.systemUTC().instant();
    Clock.systemUTC().withZone(ZoneId.systemDefault());
    Clock.systemUTC().millis();
    Clock.system(ZoneId.systemDefault());

    LocalDateTime dateTime = LocalDateTime.now();
    LocalTime time = LocalTime.now();
    Duration duration = Duration.between(time, time);
    YearMonth yearMonth = YearMonth.now();
    Period period = Period.between(date, date);
    ZoneId zoneId = ZoneId.of("America/Los_Angeles");
    ZoneOffset zoneOffset = zoneId.getRules().getOffset(dateTime);
    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    OffsetDateTime offsetDateTime = OffsetDateTime.now();
    //    Duration.ofDays();
    //    Duration.ofHours();
    //    Duration.ofMinutes();
    //    Duration.ofSeconds();
    //    Duration.ofMillis();
    //    Duration.ofNanos();
    Year year = Year.now();
    Month month = Month.NOVEMBER;
    MonthDay monthDay = MonthDay.now();
    Clock clock = Clock.fixed(instant, zoneId);
    OffsetDateTime.of(dateTime, zoneOffset);
    dateTime.atOffset(zoneOffset);
    LocalDateTime.now()
      .atOffset(ZoneOffset.UTC)
      .withOffsetSameInstant(ZoneOffset.ofHours(-2))
      .getHour();

    date.atTime(0, 0);
    date.atStartOfDay();
    date.with(TemporalAdjusters.firstDayOfMonth());
    date.with(TemporalAdjusters.firstDayOfNextMonth());
    date.with(TemporalAdjusters.firstDayOfNextYear());
    date.with(TemporalAdjusters.firstDayOfYear());
    date.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
    date.with(TemporalAdjusters.lastDayOfMonth());
    date.with(TemporalAdjusters.lastDayOfYear());
    date.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
    date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    date.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
    date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

    dateTime.with(TemporalAdjusters.firstDayOfMonth());
    dateTime.with(TemporalAdjusters.firstDayOfNextMonth());
    dateTime.with(TemporalAdjusters.firstDayOfNextYear());
    dateTime.with(TemporalAdjusters.firstDayOfYear());
    dateTime.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
    dateTime.with(TemporalAdjusters.lastDayOfMonth());
    dateTime.with(TemporalAdjusters.lastDayOfYear());
    dateTime.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
    dateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    dateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    dateTime.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
    dateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

    time.getHour();
    time.getMinute();
    time.getSecond();
    time.getNano();
    time.plusHours(1);
    time.plusMinutes(1);
    time.plusSeconds(1);
    time.plusNanos(1);

    duration.toDays();
    duration.toHours();
    duration.toMinutes();
    duration.toSeconds();
    duration.toMillis();
    duration.toNanos();
    duration.isNegative();
    duration.isZero();
    duration.isPositive();
    duration.minusHours(1);
    duration.minusMinutes(1);

    Clock.fixed(instant, zoneId);
    Clock.system(ZoneId.of("America/Los_Angeles"));
    Clock.systemDefaultZone();
    Clock.systemUTC();
    clock.instant();
    clock.getZone();
    clock.millis();
    clock.withZone(ZoneId.of("America/Los_Angeles"));
  }
}
