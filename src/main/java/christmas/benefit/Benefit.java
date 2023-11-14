package christmas.benefit;

import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.order.Order;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Benefit {

    private static final int DISCOUNT_BASE_MIN_PRICE = 10_000;
    private static final int CHRISTMAS_DDAY_DISCOUNT_BASE = 900;
    private static final int CHRISTMAS_DDAY_DISCOUNT_PER_DAY = 100;
    private static final int CHRISTMAS_DDAY_DISCOUNT_LAST_DAY = 25;
    private static final int WEEKEND_DISCOUNT_PER_MAIN = 2_023;
    private static final int SPECIAL_DAY_DISCOUNT = 1_000;
    private static final int GIFT_EVENT_THRESHOLD = 120_000;

    private final Order order;
    private final Map<String, Integer> discountInfo;
    private final EnumMap<Menu, Integer> giftBasket;

    public Benefit(Order order) {
        discountInfo = new LinkedHashMap<>();
        giftBasket = new EnumMap<>(Menu.class);
        this.order = order;
        discount();
    }

    public Map<Menu, Integer> getGiftBasket() {
        return Collections.unmodifiableMap(giftBasket);
    }

    public Map<String, Integer> getDiscountInfo() {
        return Collections.unmodifiableMap(discountInfo);
    }

    public int getAfterDiscount() {
        return order.getTotalPrice() - getTotalOnlyDiscount();
    }

    public int getTotalBenefitPrice() {
        return discountInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int getTotalOnlyDiscount() {
        return discountInfo.keySet().stream()
                .filter(key -> key.contains("할인"))
                .mapToInt(discountInfo::get)
                .sum();
    }

    private void discount() {
        if (order.getTotalPrice() >= DISCOUNT_BASE_MIN_PRICE) {
            applyChristmasDDayDiscount();
            applyWeekDiscount();
            applySpecialDayDiscount();
            applyGetGift();
        }
    }

    private void applyChristmasDDayDiscount() {
        if (order.isDayLaterOrEqual(CHRISTMAS_DDAY_DISCOUNT_LAST_DAY)) {
            discountInfo.put("크리스마스 디데이 할인",
                    CHRISTMAS_DDAY_DISCOUNT_BASE +
                            (CHRISTMAS_DDAY_DISCOUNT_PER_DAY * order.getDay()));
        }
    }

    private void applyWeekDiscount() {
        List<Integer> weekEnd = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
        if (weekEnd.contains(order.getDay())) {
            discountInfo.put("주말 할인",
                    order.getCountOfCategory(Category.MAIN) * WEEKEND_DISCOUNT_PER_MAIN);
            return;
        }
        discountInfo.put("평일 할인",
                order.getCountOfCategory(Category.DESSERT) * WEEKEND_DISCOUNT_PER_MAIN);
    }

    private void applySpecialDayDiscount() {
        List<Integer> specialDay = List.of(3, 10, 17, 14, 15, 31);
        if (specialDay.contains(order.getDay())) {
            discountInfo.put("특별 할인", SPECIAL_DAY_DISCOUNT);
        }
    }

    private void applyGetGift() {
        if (order.getTotalPrice() >= GIFT_EVENT_THRESHOLD) {
            Menu champagne = Menu.CHAMPAGNE;
            discountInfo.put("증정 이벤트", champagne.getPrice());
            giftBasket.put(champagne, 1);
        }
    }

}
