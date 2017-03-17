package eu.greyson.bsc.bscTest.exception;

/** Payment cannot be parsed - data are invalid. */
public class PaymentParseException extends RuntimeException {
    public PaymentParseException(String message) {
        super("Payment cannot be parsed!");
    }
}
