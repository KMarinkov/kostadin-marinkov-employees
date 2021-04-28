package main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Date {

    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Date(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getDays(Date otherDate) {
        if (this.dateFrom.isAfter(otherDate.dateTo) || otherDate.dateFrom.isAfter(this.dateTo)) {
            return 0L;
        }
        if (this.dateFrom.isEqual(otherDate.dateFrom) && this.dateTo.isEqual(otherDate.dateTo)) {
            return ChronoUnit.DAYS.between(this.dateFrom, this.dateTo);
        }

        else if (this.dateFrom.isAfter(otherDate.dateFrom) && this.dateTo.isAfter(otherDate.dateTo)) {
            return ChronoUnit.DAYS.between(this.dateFrom, otherDate.dateTo);
        }

        else if (!this.dateFrom.isAfter(otherDate.dateFrom) && this.dateTo.isAfter(otherDate.dateTo)) {
            return ChronoUnit.DAYS.between(otherDate.dateFrom, otherDate.dateTo);
        }

        else if (this.dateFrom.isAfter(otherDate.dateFrom) && !this.dateTo.isAfter(otherDate.dateTo)) {
            return ChronoUnit.DAYS.between(this.dateFrom, this.dateTo);
        }

        else if (!this.dateFrom.isAfter(otherDate.dateFrom) && !this.dateTo.isAfter(otherDate.dateTo)) {
            return ChronoUnit.DAYS.between(this.dateFrom, this.dateTo);
        }

        return 0L;
    }

}
