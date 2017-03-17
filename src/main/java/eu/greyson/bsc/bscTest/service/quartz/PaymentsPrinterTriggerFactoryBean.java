package eu.greyson.bsc.bscTest.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class PaymentsPrinterTriggerFactoryBean extends SimpleTriggerFactoryBean {
    @Autowired
    private ScheduledPaymentPrinterJobDetailFactory jobDetailFactory;

//    @Autowired
//    public PaymentsPrinterTriggerFactoryBean(ScheduledPaymentPrinterJobDetailFactory jobDetailFactory) {
//        this.jobDetailFactory = jobDetailFactory;
//    }

    @Override
    public void afterPropertiesSet() {
        setRepeatInterval(6000);
        setJobDetail(jobDetailFactory.getObject());
        super.afterPropertiesSet();
    }
}
