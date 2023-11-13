package christmas.view;

import christmas.badge.Badge;
import christmas.basket.Basket;
import christmas.discount.Discount;
import christmas.order.Order;
import java.text.NumberFormat;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private final NumberFormat numberFormat = NumberFormat.getNumberInstance();

    public OutputView() {
    }

    public void displayStartPlanerMsg() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void displayEventBenefit(int day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void displayOrderMenuAndCount(Order order) {
        System.out.println("<주문 메뉴>");
        System.out.println(order.getOrderMenuAndCount());
    }

    public void displayBeforeDiscount(int price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(numberFormat.format(price) + "원");
        System.out.println();
    }

    public void displayGiftList(Discount discount) {
        System.out.println("<증정 메뉴>");
        System.out.println(discount.getGiftBasket());
        System.out.println();
    }

    public void displayDiscountInfo(Discount discount) {
        System.out.println("<혜택 내역>");
        Map<String, Integer> discountInfo = discount.getDiscountInfo();
        if (discountInfo.isEmpty()) {
            System.out.println("없음\n");
            return;
        }
        String result = discountInfo.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + numberFormat.format(-entry.getValue()) + "원")
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    public void displayTotalBenefit(Discount discount) {
        System.out.println("<총혜택 금액>");
        int totalBenefitPrice = discount.getTotalBenefitPrice() * -1;
        System.out.println(numberFormat.format(totalBenefitPrice) + "원");
        System.out.println();
    }

    public void displayAfterDiscount(Discount discount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(numberFormat.format(discount.getAfterDiscount()) + "원");
        System.out.println();
    }

    public void displayBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getName());
    }
}
