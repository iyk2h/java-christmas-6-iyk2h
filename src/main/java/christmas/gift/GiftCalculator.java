package christmas.gift;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GiftCalculator {

    private static final String GIFT_EVENT_NAME = "증정 이벤트";

    private final List<GiftInfo> giftInfos;

    public GiftCalculator(List<GiftInfo> giftInfos) {
        this.giftInfos = giftInfos;
    }

    public Map<String, Integer> getGiftInfo() {
        Map<String, Integer> giftInfo = new LinkedHashMap<>();
        if (!giftInfos.isEmpty()) {
            giftInfo.put(GIFT_EVENT_NAME, getTotalGiftAmount());
        }
        return giftInfo;
    }

    public int getTotalGiftAmount() {
        return giftInfos.stream().mapToInt(GiftInfo::getMenuPrice).sum();
    }

}
