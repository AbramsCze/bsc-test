package eu.greyson.bsc.bscTest.repository.impl;

import eu.greyson.bsc.bscTest.exception.FileNotFoundException;
import eu.greyson.bsc.bscTest.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/** Payments repository implementation. */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Value("${payment.file.suffix}") private String fileSuffix;
    @Value("${payment.storage.directory}") private String storageDirectory;

    @Override
    public Stream<String> getAll(String filename) throws IOException {
        List<String> fileContent = new LinkedList<>();
        InputStream is = getClass().getResourceAsStream(String.format("%s/%s.%s", storageDirectory, filename, fileSuffix));

        if(is != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            return fileContent.stream();
        }
        else {
            throw new FileNotFoundException();
        }
    }
}
