package eu.greyson.bsc.bscTest.service.impl;

import eu.greyson.bsc.bscTest.service.PaymentPrinterService;
import eu.greyson.bsc.bscTest.view.PaymentTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/** Payment printer service implementation. */
@Service
public class PaymentPrinterServiceImpl implements PaymentPrinterService {
    private final PaymentTracker paymentTracker;

    @Autowired
    public PaymentPrinterServiceImpl(PaymentTracker paymentTracker) {
        this.paymentTracker = paymentTracker;
    }

    @Scheduled(fixedRate = 6000)
    @Override
    public void continuousSendToConsole() {
        paymentTracker.sendToConsole();
    }
}
