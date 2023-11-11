package christmas.menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void normal_test() {
        Menu menu = Menu.of("양송이수프");

        assertAll(
                () -> assertThat(menu.getName()).isEqualTo("양송이수프"),
                () -> assertTrue(menu.isEqualCategory(Category.APPETIZER))
        );

    }

    @Test
    void wrong_name_test() {
        assertThatThrownBy(() -> Menu.of("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}