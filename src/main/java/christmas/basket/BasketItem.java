package christmas.basket;

import christmas.exception.ExceptionManager;
import christmas.menu.Category;
import christmas.menu.Menu;

public class BasketItem {

    private final Menu menu;
    private final int count;

    private static final int MIN_COUNT = 1;

    public BasketItem(String name, int count) {
        validate(name, count);
        this.menu = Menu.of(name);
        this.count = count;
    }

    public boolean isMenuNameEquals(String name) {
        return this.menu.getName().equals(name);
    }

    public boolean isCategoryEquals(Category category) {
        return this.menu.isEqualCategory(category);
    }

    public int getCount() {
        return count;
    }

    public int getTotalPrice() {
        return menu.getPrice() * count;
    }

    public String getMenuName() {
        return menu.getName();
    }

    private void validate(String name, int count) {
        countValidate(count);
    }

    private void countValidate(int count) {
        if (count < MIN_COUNT) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
    }

}
