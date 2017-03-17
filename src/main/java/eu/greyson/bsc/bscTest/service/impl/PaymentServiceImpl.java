package eu.greyson.bsc.bscTest.service.impl;

import eu.greyson.bsc.bscTest.repository.PaymentRepository;
import eu.greyson.bsc.bscTest.service.PaymentService;
import eu.greyson.bsc.bscTest.service.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/** Payment service implementation. */
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAll(String filename) throws IOException {
        return paymentRepository.getAll(filename).filter(Payment::isPaymentValid).map(Payment::new).collect(Collectors.toList());
    }
}
