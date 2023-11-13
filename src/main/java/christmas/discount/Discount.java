package christmas.discount;

import christmas.menu.Menu;
import christmas.order.Order;
import java.util.LinkedHashMap;
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

    public Map<Menu, Integer> getGiftBasket() {
        return giftBasket;
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }

    private void discount() {
        if (order.getTotalPrice() >= 10000) {
            applyChristmasDDayDiscount();
        }
    }

    private void applyChristmasDDayDiscount() {
        if (order.isDayLaterOrEqual(25)) {
            discountInfo.put("크리스마스 디데이 할인", 900 + (100 * order.getDay()));
        }
    }

}
