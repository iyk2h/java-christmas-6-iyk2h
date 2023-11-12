package christmas.order;

import christmas.exception.ExceptionManager;

public class Day {

    private final int day;

    public Day(int day) {
        validate(day);
        this.day = day;
    }

    private void validate(int day) {
        if (day < 1 || day > 31) {
            throw ExceptionManager.ERROR_MSG_PREFIX.getWithMsg("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
