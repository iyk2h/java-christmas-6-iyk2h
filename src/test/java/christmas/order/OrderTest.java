package christmas.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.basket.Basket;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    @Test
    void normal_test() {
        new Order(new Day(1), new Basket(List.of("양송이수프-1", "티본스테이크-1")));
    }

    @ParameterizedTest
    @CsvSource(value = {"1,양송이수프-0", "1,a-1", "0,양송이수프-1"}, delimiter = ',')
    void wrong_name_test(int input, String name) {
        assertThatThrownBy(() -> new Order(new Day(input), new Basket(List.of(name))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getOrderMenuAndCount() {
        Order order = new Order(new Day(1), new Basket(List.of("양송이수프-1", "티본스테이크-1")));
        assertThat(order.getOrderMenuAndCount()).contains("양송이수프 1개\n티본스테이크 1개\n");
    }

    @Test
    void getTotalPrice() {
        Order order = new Order(new Day(1), new Basket(List.of("양송이수프-1", "티본스테이크-1")));
        assertThat(order.getTotalPrice()).isEqualTo(61_000);
    }
}