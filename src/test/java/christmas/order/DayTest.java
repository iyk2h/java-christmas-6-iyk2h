package christmas.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.menu.Menu;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    void normal_test(int input) {
        new Day(input);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void wrong_name_test(int input) {
        assertThatThrownBy(() -> new Day(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}