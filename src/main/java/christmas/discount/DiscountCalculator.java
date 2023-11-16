package christmas.discount;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculator {

    private final List<DiscountInfo> discountInfos;

    public DiscountCalculator(List<DiscountInfo> discountInfos) {
        this.discountInfos = discountInfos;
    }

    public Map<String, Integer> getDiscountInfo() {
        Map<String, Integer> discountInfo = new LinkedHashMap<>();
        for (DiscountInfo discount : discountInfos) {
            discountInfo.put(discount.getDiscountName(), discount.getDiscountAmount());
        }
        return discountInfo;
    }

    public int getTotalDiscount() {
        return discountInfos.stream().mapToInt(DiscountInfo::getDiscountAmount).sum();
    }

}