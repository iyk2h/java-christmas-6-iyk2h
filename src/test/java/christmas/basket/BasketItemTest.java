package christmas.basket;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BasketItemTest {

    @Test
    void normal_test() {
        BasketItem basketItem = new BasketItem("양송이수프", 1);
        assertTrue(basketItem.isMenuNameEquals("양송이수프"));
    }

    @ParameterizedTest
    @CsvSource(value = {"a-1", "양송이수프-0"}, delimiter = '-')
    void wrong_test(String name, int count) {
        assertThatThrownBy(() -> new BasketItem(name, count))
                .isInstanceOf(IllegalArgumentException.class);
    }

}