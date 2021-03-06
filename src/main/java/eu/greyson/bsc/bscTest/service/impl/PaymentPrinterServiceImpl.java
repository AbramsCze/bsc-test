package eu.greyson.bsc.bscTest.service.impl;

import eu.greyson.bsc.bscTest.service.PaymentPrinterService;
import eu.greyson.bsc.bscTest.view.PaymentTrackerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/** Payment printer service implementation. */
@Service
public class PaymentPrinterServiceImpl implements PaymentPrinterService {
    private final PaymentTrackerController paymentTracker;

    @Autowired
    public PaymentPrinterServiceImpl(PaymentTrackerController paymentTracker) {
        this.paymentTracker = paymentTracker;
    }

    @Scheduled(fixedRateString = "${payment.scheduled.fixedRate}")
    @Override
    public void continuousSendToOutput() {
        paymentTracker.sendToConsole();
    }
}
