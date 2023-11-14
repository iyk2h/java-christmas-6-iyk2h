package christmas.benefit;

import christmas.discount.DiscountFactory;
import christmas.discount.DiscountInfo;
import christmas.gift.GiftFactory;
import christmas.gift.GiftInfo;
import christmas.order.Order;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Benefit {


    private final Order order;
    private final List<DiscountInfo> discountInfos;
    private final List<GiftInfo> giftInfos;
    private final Map<String, Integer> benefitInfo;

    public Benefit(Order order) {
        this.order = order;
        discountInfos = new DiscountFactory(this.order).get();
        giftInfos = new GiftFactory(this.order).get();
        benefitInfo = new LinkedHashMap<>();
    }

    public List<GiftInfo> getGiftBasket() {
        return Collections.unmodifiableList(giftInfos);
    }

    public Map<String, Integer> getBenefitInfo() {
        getDiscountInfo();
        getGiftInfo();

        return Collections.unmodifiableMap(benefitInfo);
    }

    private void getDiscountInfo() {
        for (DiscountInfo discountInfo : discountInfos) {
            benefitInfo.put(discountInfo.getDiscountName(), discountInfo.getDiscountAmount());
        }
    }

    private void getGiftInfo() {
        if (!giftInfos.isEmpty()) {
            benefitInfo.put("증정 이벤트", getTotalOnlyGiftAmount());
        }
    }

    public int getAfterDiscount() {
        return order.getTotalPrice() - getTotalOnlyDiscount();
    }

    public int getTotalBenefitPrice() {
        return getTotalOnlyDiscount() + getTotalOnlyGiftAmount();
    }

    private int getTotalOnlyDiscount() {
        return discountInfos.stream().mapToInt(DiscountInfo::getDiscountAmount).sum();
    }

    private int getTotalOnlyGiftAmount() {
        return giftInfos.stream().mapToInt(GiftInfo::getMenuPrice).sum();
    }

}
