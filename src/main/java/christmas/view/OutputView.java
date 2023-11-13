package christmas.view;

import christmas.basket.Basket;
import christmas.discount.Discount;
import java.text.NumberFormat;

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

    public void displayOrderMenuAndCount(Basket basket) {
        System.out.println("<주문 메뉴>");
        System.out.println(basket.getMenuNameAndCount());
    }

    public void displayBeforeDiscount(int price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(numberFormat.format(price) + "원");
    }

    public void displayGiftList(Discount discount) {
        System.out.println("<증정 메뉴>");
        System.out.println(discount.getGiftBasket());
    }


}
