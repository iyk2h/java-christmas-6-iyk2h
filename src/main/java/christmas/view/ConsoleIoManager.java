package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleIoManager implements IoManager{

    @Override
    public String read() {
        return Console.readLine();
    }
}
