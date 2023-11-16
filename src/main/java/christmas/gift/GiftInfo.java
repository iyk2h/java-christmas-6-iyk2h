package christmas.gift;

import christmas.menu.Menu;

public class GiftInfo {

    private final Menu menu;
    private final int count;

    public GiftInfo(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getMenuPrice() {
        return menu.getPrice();
    }

    public int getCount() {
        return count;
    }

}
