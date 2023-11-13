package christmas;

import christmas.controller.Controller;
import christmas.view.ConsoleIoManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new ConsoleIoManager());
        OutputView outputView = new OutputView();
        Controller controller = new Controller(inputView, outputView);
        controller.run();
    }
}
