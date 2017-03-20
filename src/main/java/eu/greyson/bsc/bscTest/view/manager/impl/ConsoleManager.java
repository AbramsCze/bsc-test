package eu.greyson.bsc.bscTest.view.manager.impl;

import eu.greyson.bsc.bscTest.service.dto.Payment;
import eu.greyson.bsc.bscTest.view.manager.ViewManager;
import org.springframework.stereotype.Component;
import java.util.Scanner;

/** Console input / output manager. */
@Component
public class ConsoleManager implements ViewManager {
    @Override
    public void writePayment(final Payment payment) {
        System.out.println(payment);
    }

    @Override
    public void writeInfo(String text, Object... data) {
        System.out.println(String.format(text, data));
    }

    @Override
    public void writeError(String text, Object... data) {
        System.err.println(String.format(text, data));
    }

    @Override
    public String readInput() {
        Scanner scanInput = new Scanner(System.in);
        return scanInput.nextLine();
    }
}
