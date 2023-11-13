package christmas.discount;

import christmas.basket.BasketItem;
import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.order.Order;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Discount {

    private final Order order;
    private final Map<String, Integer> discountInfo;
    private final Map<Menu, Integer> giftBasket;

    public Discount(Order order) {
        discountInfo = new LinkedHashMap<>();
        giftBasket = new LinkedHashMap<>();
        this.order = order;
        discount();
    }

    public String getGiftBasket() {
        if (giftBasket.isEmpty()) {
            return "없음";
        }
        StringBuilder sb = new StringBuilder();
        for (Menu menu : giftBasket.keySet()) {
            sb.append(menu.getName()).append(" ").append(giftBasket.get(menu)).append("개");
        }
        return sb.toString();
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }

    public int getAfterDiscount() {
        int totalDiscount = discountInfo.keySet().stream()
                .filter(key -> key.contains("증정 할인"))
                .mapToInt(discountInfo::get)
                .sum();

        return order.getTotalPrice() - totalDiscount;
    }

    public int getTotalBenefitPrice() {
        return discountInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void discount() {
        if (order.getTotalPrice() >= 10000) {
            applyChristmasDDayDiscount();
            applyWeekDiscount();
            applySpecialDayDiscount();
            applyGetGift();
        }
    }

    private void applyChristmasDDayDiscount() {
        if (order.isDayLaterOrEqual(25)) {
            discountInfo.put("크리스마스 디데이 할인", 900 + (100 * order.getDay()));
        }
    }

    private void applyWeekDiscount() {
        List<Integer> weekEnd = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
        if (weekEnd.contains(order.getDay())) {
            discountInfo.put("주말 할인", order.getCountOfCategory(Category.MAIN) * 2023);
            return;
        }
        discountInfo.put("평일 할인", order.getCountOfCategory(Category.DESSERT) * 2023);
    }

    private void applySpecialDayDiscount() {
        List<Integer> specialDay = List.of(3, 10, 17, 14, 15, 31);
        if (specialDay.contains(order.getDay())) {
            discountInfo.put("특별 할인", 1000);
        }
    }

    private void applyGetGift() {
        if (order.getTotalPrice() >= 120_000) {
            Menu champagne = Menu.CHAMPAGNE;
            discountInfo.put("증정 이벤트", champagne.getPrice());
            giftBasket.put(champagne, 1);
        }
    }
}
