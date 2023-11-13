package christmas.view;

import christmas.exception.ExceptionManager;
import christmas.util.Converter;

public class InputView {

    private final ConsoleIoManager consoleIoManager;

    public InputView(ConsoleIoManager consoleIoManager) {
        this.consoleIoManager = consoleIoManager;
    }

    public int inputDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Converter.convertToInt(consoleIoManager.read(),
                ExceptionManager.ERROR_WRONG_DAY.get());
    }

}
