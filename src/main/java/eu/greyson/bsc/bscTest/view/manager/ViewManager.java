package eu.greyson.bsc.bscTest.view.manager;

import eu.greyson.bsc.bscTest.service.dto.Payment;

/** View manager interface. */
public interface ViewManager {
    /** Write payment to output. */
    void writePayment(Payment payment);

    /** Write info text to output. */
    void writeInfo(String text, Object... data);

    /** Write error text to output. */
    void writeError(String text, Object... data);

    /** Read text from input. */
    String readInput();
}
