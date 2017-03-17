package eu.greyson.bsc.bscTest.service.quartz;

import eu.greyson.bsc.bscTest.view.PaymentTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ScheduledPaymentPrinterJobDetailFactory extends JobDetailFactoryBean {
    @Autowired
    private PaymentTracker paymentTracker;

//    @Autowired
//    public ScheduledPaymentPrinterJobDetailFactory(PaymentTracker paymentTracker) {
//        this.paymentTracker = paymentTracker;
//    }

    @Override
    public void afterPropertiesSet() {
        setJobClass(PaymentPrinterJob.class);
        Map<String, Object> data = new HashMap<>();
        data.put("paymentTracker", paymentTracker);
        setJobDataAsMap(data);
        setDurability(true);
        super.afterPropertiesSet();
    }
}
