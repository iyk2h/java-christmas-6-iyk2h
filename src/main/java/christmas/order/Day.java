package christmas.order;

import christmas.exception.ExceptionManager;

public class Day {

    private final int day;

    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;

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
        if (day < MIN_DAY || day > MAX_DAY) {
            throw ExceptionManager.ERROR_WRONG_DAY.get();
        }
    }

}
