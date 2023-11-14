package christmas.discount;

public class DiscountInfo {

    private final String discountName;
    private final int discountAmount;

    public DiscountInfo(String discountName, int discountAmount) {
        this.discountName = discountName;
        this.discountAmount = discountAmount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

}
