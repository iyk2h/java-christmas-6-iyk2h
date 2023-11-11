package christmas.util;

import christmas.exception.ExceptionManager;

public class Converter {
    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
    }
}
