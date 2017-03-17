package eu.greyson.bsc.bscTest.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class PaymentsPrinterSchedulerFactoryBean extends SchedulerFactoryBean {
    @Autowired
    private ScheduledPaymentPrinterJobDetailFactory jobDetailFactory;
    @Autowired
    private PaymentsPrinterTriggerFactoryBean triggerFactory;

//    @Autowired
//    public PaymentsPrinterSchedulerFactoryBean(ScheduledPaymentPrinterJobDetailFactory jobDetailFactory, PaymentsPrinterTriggerFactoryBean triggerFactory) {
//        this.jobDetailFactory = jobDetailFactory;
//        this.triggerFactory = triggerFactory;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setJobDetails(jobDetailFactory.getObject());
        setTriggers(triggerFactory.getObject());
        super.afterPropertiesSet();
    }

}
