package com.stano.daterange;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public final class QuarterlyDateRange extends DefaultDateRange {

    public static QuarterlyDateRange withEndDate(LocalDate endDate) {
        return new QuarterlyDateRange(
            endDate.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(2),
            endDate.with(TemporalAdjusters.firstDayOfMonth())
        );
    }

    public static QuarterlyDateRange withStartDate(LocalDate startDate) {
        return new QuarterlyDateRange(
            startDate.with(TemporalAdjusters.firstDayOfMonth()),
            startDate.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(2)
        );
    }

    public static QuarterlyDateRange withEndDate(LocalDate endDate, Month endMonth) {
        return new QuarterlyDateRange(
                endDate.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(2),
                endDate.with(TemporalAdjusters.firstDayOfMonth()),
                endMonth
        );
    }

    public static QuarterlyDateRange withStartDate(LocalDate startDate, Month endMonth) {
        return new QuarterlyDateRange(
                startDate.with(TemporalAdjusters.firstDayOfMonth()),
                startDate.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(2),
                endMonth
        );
    }

    public DateRange getPriorDateRange() {
        return new QuarterlyDateRange(
                this.startDate.minusMonths(3),
                this.endDate.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(3).with(TemporalAdjusters.lastDayOfMonth())
        );
    }

    public DateRange getNextDateRange() {
        return new QuarterlyDateRange(
                this.startDate.plusMonths(3),
                this.endDate.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(3).with(TemporalAdjusters.lastDayOfMonth())
        );
    }

    @Override
    protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {
        return new QuarterlyDateRange(startDate, endDate);
    }

    private QuarterlyDateRange(LocalDate startDate, LocalDate endDate) {
        super(startDate.with(TemporalAdjusters.firstDayOfMonth()), endDate.with(TemporalAdjusters.lastDayOfMonth()));
    }

    private QuarterlyDateRange(LocalDate startDate, LocalDate endDate, Month endMonth) {
        super(
            startDate.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(calculateMonthOffset(endDate, endMonth)),
            endDate.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(calculateMonthOffset(endDate, endMonth)).with(TemporalAdjusters.lastDayOfMonth())
        );
    }

    private static int calculateMonthOffset(LocalDate endDate, Month endMonth) {
        int offset = endMonth.getValue() - endDate.getMonth().getValue();
        if(offset < 0) {
            offset += Month.values().length;
        }
        return offset % 3;
    }
}
