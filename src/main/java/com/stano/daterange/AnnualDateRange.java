package com.stano.daterange;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public final class AnnualDateRange extends DefaultDateRange {

    public static AnnualDateRange withEndDate(LocalDate endDate) {
        return new AnnualDateRange(endDate.minusYears(1).plusDays(1), endDate);
    }

    public static AnnualDateRange withStartDate(LocalDate startDate) {
        return new AnnualDateRange(startDate, startDate.plusYears(1).minusDays(1));
    }

    public static AnnualDateRange withEndDate(LocalDate endDate, boolean snapToMonthEdges) {
        return new AnnualDateRange(snapToMonthEdges ?
                endDate.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(11) : endDate.minusYears(1).plusDays(1),
                endDate,
                snapToMonthEdges);
    }

    public static AnnualDateRange withStartDate(LocalDate startDate, boolean snapToMonthEdges) {
        return new AnnualDateRange(startDate,
                snapToMonthEdges ?
                        startDate.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(11) :
                        startDate.plusYears(1).minusDays(1),
                snapToMonthEdges);
    }

    @Override
    protected DateRange createNewDateRange(LocalDate startDate, LocalDate endDate) {
        return new AnnualDateRange(startDate, endDate);
    }

    private AnnualDateRange(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    private AnnualDateRange(LocalDate startDate, LocalDate endDate, boolean snapToMonthEdges) {
        super(
            snapToMonthEdges ? startDate.with(TemporalAdjusters.firstDayOfMonth()) : startDate,
            snapToMonthEdges ? endDate.with(TemporalAdjusters.lastDayOfMonth()) : endDate
        );
        this.snapToMonthEdges = snapToMonthEdges;
    }

    private AnnualDateRange(int year) {
        super(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 1).with(TemporalAdjusters.lastDayOfMonth()));
        this.snapToMonthEdges = true;
    }

    public DateRange getPriorDateRange() {
        LocalDate newStartDate = this.startDate.minusYears(1),
            newEndDate = this.snapToMonthEdges ?
                this.endDate.with(TemporalAdjusters.firstDayOfMonth()).minusYears(1).with(TemporalAdjusters.lastDayOfMonth()) :
                this.endDate.minusYears(1);
        return new AnnualDateRange(newStartDate, newEndDate, this.snapToMonthEdges);
    }

    public DateRange getNextDateRange() {
        LocalDate newStartDate = this.startDate.plusYears(1),
            newEndDate = this.snapToMonthEdges ?
                this.endDate.with(TemporalAdjusters.firstDayOfMonth()).plusYears(1).with(TemporalAdjusters.lastDayOfMonth()) :
                this.endDate.plusYears(1);
        return new AnnualDateRange(newStartDate, newEndDate, this.snapToMonthEdges);
    }

    public boolean isSnapToMonthEdges() {
        return snapToMonthEdges;
    }

    public void setSnapToMonthEdges(boolean snapToMonthEdges) {
        this.snapToMonthEdges = snapToMonthEdges;
    }

    private boolean snapToMonthEdges = false;
}
