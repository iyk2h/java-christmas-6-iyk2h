package christmas.exception;

public enum ExceptionManager {
    ERROR_MSG_PREFIX(""),
    ERROR_WRONG_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    static private final String BEGIN_MSG = "[ERROR] ";
    private final String msg;

    ExceptionManager(String msg) {
        this.msg = msg;
    }

    public IllegalArgumentException get() {
        return new IllegalArgumentException(BEGIN_MSG + msg);
    }

    public IllegalArgumentException getWithMsg(String detailMsg) {
        return new IllegalArgumentException(BEGIN_MSG + msg + detailMsg);
    }
}
