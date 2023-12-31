package christmas.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.basket.Basket;
import christmas.menu.Category;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertThat(order.getOrderMenuAndCount().size()).isEqualTo(2);
    }

    @Test
    void orderOf20Menu() {
        Order order = new Order(new Day(1), new Basket(List.of("바비큐립-1", "티본스테이크-1", "해산물파스타-1", "크리스마스파스타-17")));
        assertThat(order.getCountOfCategory(Category.MAIN)).isEqualTo(20);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 21})
    void overRangeOderMenu(int input) {
        assertThatThrownBy(() -> new Order(new Day(1), new Basket(List.of("양송이수프-"+input))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getTotalPrice() {
        Order order = new Order(new Day(1), new Basket(List.of("양송이수프-1", "티본스테이크-1")));
        assertThat(order.getTotalPrice()).isEqualTo(61_000);
    }

    @Test
    void orderOnlyBeverage() {
        assertThatThrownBy(() -> new Order(new Day(1), new Basket(List.of("제로콜라-1", "레드와인-1", "샴페인-1"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

}