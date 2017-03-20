package eu.greyson.bsc.bscTest.exception;

/** Payment data file is not found. */
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
        super("Payment data file is not found");
    }
}
