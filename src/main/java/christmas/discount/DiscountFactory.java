package christmas.discount;

import christmas.menu.Category;
import christmas.order.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscountFactory {

    private static final int DISCOUNT_BASE_MIN_PRICE = 10_000;
    private static final int CHRISTMAS_DDAY_DISCOUNT_BASE = 900;
    private static final int CHRISTMAS_DDAY_DISCOUNT_PER_DAY = 100;
    private static final int WEEKEND_DISCOUNT_PER_MAIN = 2_023;
    private static final int SPECIAL_DAY_DISCOUNT = 1_000;

    private static final String CHRISTMAS_DD_DISCOUNT_NAME = "크리스마스 디데이 할인";
    private static final String WEEKEND_DISCOUNT_NAME = "주말 할인";
    private static final String WEEKDAY_DISCOUNT_NAME = "평일 할인";
    private static final String SPECIAL_DAY_DISCOUNT_NAME = "특별 할인";

    private static final int CHRISTMAS_DDAY_DISCOUNT_LAST_DAY = 25;
    private static final List<Integer> WEEKEND_DISCOUNT_DAYS = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> SPECIAL_DISCOUNT_DAYS = List.of(3, 10, 17, 24, 25, 31);

    private final Order order;
    private final List<DiscountInfo> discountInfos;

    public DiscountFactory(Order order) {
        discountInfos = new ArrayList<>();
        this.order = order;
        applyGetDiscount();
    }

    public List<DiscountInfo> get() {
        return Collections.unmodifiableList(discountInfos);
    }

    private void applyGetDiscount() {
        if (this.order.getTotalPrice() >= DISCOUNT_BASE_MIN_PRICE) {
            applyChristmasDDayDiscount();
            applyWeekEndDiscount();
            applyWeekDaysDiscount();
            applySpecialDayDiscount();
        }
    }

    private void applyChristmasDDayDiscount() {
        if (this.order.isDayLaterOrEqual(CHRISTMAS_DDAY_DISCOUNT_LAST_DAY)) {
            discountInfos.add(
                    new DiscountInfo(CHRISTMAS_DD_DISCOUNT_NAME,
                            CHRISTMAS_DDAY_DISCOUNT_BASE
                                    + (CHRISTMAS_DDAY_DISCOUNT_PER_DAY * this.order.getDay())));
        }
    }

    private void applyWeekEndDiscount() {
        if (WEEKEND_DISCOUNT_DAYS.contains(this.order.getDay())) {
            discountInfos.add(new DiscountInfo(WEEKEND_DISCOUNT_NAME,
                    this.order.getCountOfCategory(Category.MAIN) * WEEKEND_DISCOUNT_PER_MAIN));
        }
    }

    private void applyWeekDaysDiscount() {
        if (!WEEKEND_DISCOUNT_DAYS.contains(this.order.getDay())) {
            discountInfos.add(new DiscountInfo(WEEKDAY_DISCOUNT_NAME,
                    this.order.getCountOfCategory(Category.DESSERT) * WEEKEND_DISCOUNT_PER_MAIN));
        }
    }

    private void applySpecialDayDiscount() {
        if (SPECIAL_DISCOUNT_DAYS.contains(this.order.getDay())) {
            discountInfos.add(new DiscountInfo(SPECIAL_DAY_DISCOUNT_NAME, SPECIAL_DAY_DISCOUNT));
        }
    }

}
