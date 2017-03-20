package eu.greyson.bsc.bscTest.service;

import eu.greyson.bsc.bscTest.service.dto.Payment;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Queue;

/** Payment service interface. */
public interface PaymentService {
    /**
     * Method return all payments from file in storage with specified filename.
     * @param filename of file to read.
     * @return all payments from file in thread save queue.
     */
    Queue<Payment> getAllConcurrent(String filename) throws IOException, URISyntaxException;
}
