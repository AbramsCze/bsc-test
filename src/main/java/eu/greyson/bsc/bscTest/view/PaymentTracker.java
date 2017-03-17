package eu.greyson.bsc.bscTest.view;

import eu.greyson.bsc.bscTest.service.PaymentService;
import eu.greyson.bsc.bscTest.service.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;

/** Payment tracker implementation. */
@Component
public class PaymentTracker implements ApplicationRunner {
    private final PaymentService paymentService;

    @Autowired
    public PaymentTracker(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void run(final ApplicationArguments applicationArguments) throws Exception {
        List<Payment> payments = paymentService.getAll("payments1");

        for(Payment payment : payments) {
            System.out.println(payment);
        }
    }
}
