package christmas.benefit;

import christmas.discount.DiscountCalculator;
import christmas.discount.DiscountFactory;
import christmas.discount.DiscountInfo;
import christmas.gift.GiftCalculator;
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

    public Benefit(Order order) {
        this.order = order;
        discountInfos = new DiscountFactory(this.order).get();
        giftInfos = new GiftFactory(this.order).get();
    }

    public List<GiftInfo> getGiftInfos() {
        return Collections.unmodifiableList(giftInfos);
    }

    public Map<String, Integer> getBenefitInfo() {
        DiscountCalculator discountCalculator = new DiscountCalculator(discountInfos);
        GiftCalculator giftCalculator = new GiftCalculator(giftInfos);

        Map<String, Integer> benefitInfo = new LinkedHashMap<>();
        benefitInfo.putAll(discountCalculator.calculateDiscountInfo());
        benefitInfo.putAll(giftCalculator.calculateGiftInfo());

        return Collections.unmodifiableMap(benefitInfo);
    }

    public int getAfterDiscount() {
        DiscountCalculator discountCalculator = new DiscountCalculator(discountInfos);
        return order.getTotalPrice() - discountCalculator.calculateTotalDiscount();
    }

    public int getTotalBenefitPrice() {
        DiscountCalculator discountCalculator = new DiscountCalculator(discountInfos);
        GiftCalculator giftCalculator = new GiftCalculator(giftInfos);

        return discountCalculator.calculateTotalDiscount()
                + giftCalculator.calculateTotalGiftAmount();
    }

}
