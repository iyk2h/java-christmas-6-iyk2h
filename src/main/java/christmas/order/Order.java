package christmas.order;

import christmas.basket.Basket;
import christmas.exception.ExceptionManager;
import christmas.menu.Category;

public class Order {

    private final Day day;
    private final Basket basket;

    public Order(Day day, Basket basket) {
        checkBasketSize(basket);
        checkAllItemsOfCategory(basket, Category.BEVERAGE);

        this.day = day;
        this.basket = basket;
    }

    private void checkBasketSize(Basket basket) {
        if (basket.getTotalCount() >= 20) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
    }

    private void checkAllItemsOfCategory(Basket basket, Category category) {
        if (basket.isAllItemsOfCategory(category)) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
    }


}
