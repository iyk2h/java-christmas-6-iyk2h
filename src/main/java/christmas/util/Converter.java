package christmas.util;

public class Converter {

    public static int convertToInt(String input, IllegalArgumentException exception) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw exception;
        }
    }

}
