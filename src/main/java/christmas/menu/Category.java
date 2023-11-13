package christmas.menu;

public enum Category {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료"),
    NOTHING("없음");

    private final String name;

    Category(String name) {
        this.name = name;
    }
}

