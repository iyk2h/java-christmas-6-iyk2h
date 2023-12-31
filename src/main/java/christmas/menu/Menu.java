package christmas.menu;

import christmas.exception.ExceptionManager;
import java.util.Arrays;

public enum Menu {
    // 애피타이저
    YANGSONGISOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    CAESARSALAD("시저샐러드", 8_000, Category.APPETIZER),

    // 메인
    TBONESTEAK("티본스테이크", 55_000, Category.MAIN),
    BARBECUERIB("바비큐립", 54_000, Category.MAIN),
    SEAFOODPASTA("해산물파스타", 35_000, Category.MAIN),
    CHRISTMASPASTA("크리스마스파스타", 25_000, Category.MAIN),

    // 디저트
    CHOCOCAKE("초코케이크", 15_000, Category.DESSERT),
    ICECREAM("아이스크림", 5_000, Category.DESSERT),

    // 음료
    ZEROCOLA("제로콜라", 3_000, Category.BEVERAGE),
    REDWINE("레드와인", 6_0000, Category.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Category.BEVERAGE),

    // 낫딩
    NOTHING("없.음.", 0, Category.NOTHING);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu of(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.isEqualsName(name))
                .findAny()
                .orElseThrow(ExceptionManager.ERROR_WRONG_ORDER::get);
    }

    private boolean isEqualsName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isEqualCategory(Category category) {
        return this.category.equals(category);
    }

}