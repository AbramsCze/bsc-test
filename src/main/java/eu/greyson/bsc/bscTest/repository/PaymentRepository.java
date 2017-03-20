package eu.greyson.bsc.bscTest.repository;

import java.io.IOException;
import java.util.stream.Stream;

/** Payments repository interface. */
public interface PaymentRepository {
    /**
     * Method return all payments from file in storage with specified filename.
     * @param filename of file to read.
     * @return all payments from file.
     */
    Stream<String> getAll(String filename) throws IOException;
}
