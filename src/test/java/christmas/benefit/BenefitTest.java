package christmas.benefit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.basket.Basket;
import christmas.order.Day;
import christmas.order.Order;
import java.util.List;
import org.junit.jupiter.api.Test;

class BenefitTest {

    @Test
    void normalTest() {
        Benefit benefit = new Benefit(new Order(new Day(3),
                new Basket(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"))));

        assertAll(
                () -> assertThat(benefit.getBenefitInfo().toString()).isEqualTo(
                        "{크리스마스 디데이 할인=1200, 평일 할인=4046, 특별 할인=1000, 증정 이벤트=25000}"),
                () -> assertThat(benefit.getGiftInfos().get(0).getMenuName()).isEqualTo("샴페인"),
                () -> assertThat(benefit.getAfterDiscount()).isEqualTo(135754),
                () -> assertThat(benefit.getTotalBenefitPrice()).isEqualTo(31246)
        );
    }
}