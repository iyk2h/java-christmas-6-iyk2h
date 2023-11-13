package christmas.badge;

public enum Badge {

    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOPE("없음", 0);

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public static Badge getBadgeByPrice(int price) {
        for (Badge badge : values()) {
            if (price >= badge.price) {
                return badge;
            }
        }
        return NOPE;
    }

}