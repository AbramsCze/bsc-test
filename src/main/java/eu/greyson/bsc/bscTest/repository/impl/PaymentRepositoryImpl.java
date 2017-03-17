package eu.greyson.bsc.bscTest.repository.impl;

import eu.greyson.bsc.bscTest.repository.PaymentRepository;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

/** Payments repository implementation. */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private static final String PAYMENTS_STORAGE_DIRECTORY = "/storage";
    private static final String PAYMENTS_FILE_SUFFIX = "data";

    private final File storageDirectory;

    public PaymentRepositoryImpl() throws URISyntaxException {
        storageDirectory = new File(getClass().getResource(PAYMENTS_STORAGE_DIRECTORY).toURI());
    }

    @Override
    public Stream<String> getAll(String filename) throws IOException {
        Stream<String> data = Stream.empty();
        File payments = new File(storageDirectory, String.format("%s.%s", filename, PAYMENTS_FILE_SUFFIX));

        if(storageDirectory.exists()) {
            data = Files.lines(payments.toPath(), StandardCharsets.UTF_8);
        }
        return data;
    }
}
