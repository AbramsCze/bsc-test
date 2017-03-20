package eu.greyson.bsc.bscTest.repository.impl;

import eu.greyson.bsc.bscTest.exception.FileNotFoundException;
import eu.greyson.bsc.bscTest.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Stream;

/** Payments repository implementation. */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Value("${payment.file.suffix}") private String fileSuffix;
    @Value("${payment.storage.directory}") private String storageDirectory;

    private Optional<File> storage = Optional.empty();

    @Override
    public Stream<String> getAll(String filename) throws URISyntaxException, IOException {
        Stream<String> data = Stream.empty();

        if(getStorage().isPresent()) {
            File payments = new File(getStorage().get(), String.format("%s.%s", filename, fileSuffix));

            if (payments.exists()) {
                data = Files.lines(payments.toPath(), StandardCharsets.UTF_8);
            }
            else {
                throw new FileNotFoundException();
            }
        }
        return data;
    }

    /** @return payment storage directory or empty, if storage is not valid. */
    private Optional<File> getStorage() throws URISyntaxException {
        if(!storage.isPresent()) {
            if(!StringUtils.isEmpty(storageDirectory)) {
                storage = Optional.of(new File(getClass().getResource(storageDirectory).toURI()));
            }
        }
        return storage;
    }
}
