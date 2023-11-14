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
    private static final int CHRISTMAS_DDAY_DISCOUNT_LAST_DAY = 25;
    private static final int WEEKEND_DISCOUNT_PER_MAIN = 2_023;
    private static final int SPECIAL_DAY_DISCOUNT = 1_000;

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
            applyWeekDiscount();
            applySpecialDayDiscount();
        }
    }

    private void applyChristmasDDayDiscount() {
        if (this.order.isDayLaterOrEqual(CHRISTMAS_DDAY_DISCOUNT_LAST_DAY)) {
            discountInfos.add(
                    new DiscountInfo("크리스마스 디데이 할인",
                            CHRISTMAS_DDAY_DISCOUNT_BASE
                                    + (CHRISTMAS_DDAY_DISCOUNT_PER_DAY * this.order.getDay())));
        }
    }

    private void applyWeekDiscount() {
        List<Integer> weekEnd = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
        if (weekEnd.contains(this.order.getDay())) {
            discountInfos.add(new DiscountInfo("주말 할인",
                    this.order.getCountOfCategory(Category.MAIN) * WEEKEND_DISCOUNT_PER_MAIN));
            return;
        }
        discountInfos.add(new DiscountInfo("평일 할인",
                this.order.getCountOfCategory(Category.DESSERT) * WEEKEND_DISCOUNT_PER_MAIN));
    }

    private void applySpecialDayDiscount() {
        List<Integer> specialDay = List.of(3, 10, 17, 14, 15, 31);
        if (specialDay.contains(this.order.getDay())) {
            discountInfos.add(new DiscountInfo("특별 할인", SPECIAL_DAY_DISCOUNT));
        }
    }

}
