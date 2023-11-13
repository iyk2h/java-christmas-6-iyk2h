package christmas.badge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @ParameterizedTest
    @CsvSource(
            value = {"0-없음", "4999-없음", "5000-별", "9999-별", "10000-트리", "19999-트리", "20000-산타",
                    "30000-산타"}
            , delimiter = '-')
    void normal_test(int price, String name) {
        assertThat(Badge.getBadgeByPrice(price).getName()).isEqualTo(name);
    }
}