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

    public int getDay() {
        return day.getDay();
    }

    private void checkBasketSize(Basket basket) {
        int totalCount = basket.getTotalCount();
        if (totalCount >= 20 || totalCount <= 0) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
    }

    private void checkAllItemsOfCategory(Basket basket, Category category) {
        if (basket.isAllItemsOfCategory(category)) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
    }

    public int getCountOfCategory(Category category) {
        return basket.getTotalCountByCategory(category);
    }

    public String getOrderMenuAndCount() {
        return basket.getMenuNameAndCount();
    }

    public int getTotalPrice() {
        return basket.getTotalPrice();
    }

    public boolean isDayLaterOrEqual(int day) {
        return this.day.isDayLaterOrEqual(day);
    }

}
