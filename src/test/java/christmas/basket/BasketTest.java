package christmas.basket;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.menu.Category;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BasketTest {

    @Test
    void normal_test() {
        Basket basket = new Basket(List.of("양송이수프-1", "티본스테이크-1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a-1", "양송이수프-a", "양송이수프-0", "양송이수프-0-", "양송이수프,1", "양송이수프,1",
            "양송이수프 1"})
    void wrong_test(String input) {
        assertThatThrownBy(() -> new Basket(List.of(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void duplicate_test() {
        assertThatThrownBy(() -> new Basket(List.of("양송이수프-1", "양송이수프-2")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getTotalCountByCategory() {
        Basket basket = new Basket(List.of("양송이수프-1", "티본스테이크-1", "바비큐립-2"));
        assertThat(basket.getTotalCountByCategory(Category.MAIN)).isEqualTo(3);
    }
}