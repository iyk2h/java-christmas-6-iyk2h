package christmas.gift;

import christmas.menu.Menu;
import christmas.order.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GiftFactory {
    private static final int GIFT_EVENT_THRESHOLD = 120_000;

    private final Order order;
    private final List<GiftInfo> giftInfos;

    public GiftFactory(Order order) {
        giftInfos = new ArrayList<>();
        this.order = order;
        applyGetGift();
    }

    public List<GiftInfo> get() {
        return Collections.unmodifiableList(giftInfos);
    }

    private void applyGetGift() {
        if (order.getTotalPrice() >= GIFT_EVENT_THRESHOLD) {
            Menu champagne = Menu.CHAMPAGNE;
            giftInfos.add(new GiftInfo(champagne, 1));
        }
    }

}
