package christmas.controller;

import christmas.badge.Badge;
import christmas.basket.Basket;
import christmas.benefit.Benefit;
import christmas.order.Day;
import christmas.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.displayStartPlanerMsg();
        Day day = repeat(this::getDay);
        Order order = repeat(() -> this.inputOrder(day));
        outputOrderInfo(order);
        outputDiscountInfo(new Benefit(order));
    }

    private Day getDay() {
        return new Day(inputView.inputDay());
    }

    private Order inputOrder(Day day) {
        return new Order(day, getBasket());
    }

    private Basket getBasket() {
        return new Basket(inputView.inputMenuAndCount());
    }

    private void outputOrderInfo(Order order) {
        outputView.displayEventBenefit(order.getDay());
        outputView.displayOrderMenuAndCount(order);
        outputView.displayBeforeDiscount(order.getTotalPrice());
    }

    private void outputDiscountInfo(Benefit benefit) {
        outputView.displayGiftList(benefit);
        outputView.displayDiscountInfo(benefit);
        outputView.displayTotalBenefit(benefit);
        outputView.displayAfterDiscount(benefit);
        outputView.displayBadge(Badge.getBadgeByPrice(benefit.getTotalBenefitPrice()));
    }

    private <T> T repeat(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(input);
        }
    }

}
