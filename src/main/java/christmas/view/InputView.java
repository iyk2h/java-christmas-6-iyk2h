package christmas.view;

import christmas.exception.ExceptionManager;
import christmas.util.Converter;
import java.util.List;

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

    public List<String> inputMenuAndCount() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return inputMenuAndCountToList(consoleIoManager.read());
    }

    private List<String> inputMenuAndCountToList(String input) {
        if (input.startsWith(",") || input.endsWith(",")) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
        return List.of(input.split(","));
    }

}
