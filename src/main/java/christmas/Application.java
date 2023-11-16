package christmas;

import christmas.controller.ChristmasController;
import christmas.view.ConsoleIoManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new ConsoleIoManager());
        OutputView outputView = new OutputView();
        ChristmasController christmasController = new ChristmasController(inputView, outputView);
        christmasController.run();
    }

}
