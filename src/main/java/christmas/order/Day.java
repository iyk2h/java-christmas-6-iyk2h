package christmas.order;

import christmas.exception.ExceptionManager;

public class Day {

    private final int day;

    public Day(int day) {
        validate(day);
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public boolean isDayLaterOrEqual(int day) {
        return this.day <= day;
    }

    private void validate(int day) {
        if (day < 1 || day > 31) {
            throw ExceptionManager.ERROR_WRONG_DAY.get();
        }
    }

}
