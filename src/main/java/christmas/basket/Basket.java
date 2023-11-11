package christmas.basket;

import christmas.exception.ExceptionManager;
import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.util.Converter;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.implementation.ExceptionMethod;

public class Basket {

    private final List<BasketItem> basket;

    public Basket(List<String> inputs) {
        this.basket = inputBasket(inputs);
    }

    private List<BasketItem> inputBasket(List<String> menus) {
        List<BasketItem> baskets = new ArrayList<>();
        for (String menuInfo : menus) {
            String[] split = getSplit(menuInfo);
            String name = split[0];
            int count = Converter.convertToInt(split[1]);
            BasketItem basketItem = new BasketItem(split[0], count);
            checkDuplicate(baskets, name);
            baskets.add(basketItem);
        }
        return baskets;
    }

    private String[] getSplit(String input) {
        if (input.startsWith("-") || input.endsWith("-")) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
        String[] split = input.split("-");
        if (split.length != 2) {
            throw ExceptionManager.ERROR_WRONG_ORDER.get();
        }
        return split;
    }

    private void checkDuplicate(List<BasketItem> basketItems, String name) {
        basketItems.stream()
                .filter(basketItem -> basketItem.isMenuNameEquals(name))
                .findAny()
                .ifPresent(item -> {
                    throw ExceptionManager.ERROR_WRONG_ORDER.get();
                });
    }

    public int getTotalCountByCategory(Category category) {
        return basket.stream()
                .filter(basketItem -> basketItem.isCategoryEquals(category))
                .mapToInt(BasketItem::getCount)
                .sum();
    }
}
