package eu.greyson.bsc.bscTest.service.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Quartz payment printer scheduler implementation. */
@Component
public class PaymentPrinterSchedulerImpl implements PaymentPrinterScheduler {
    @Autowired
    private PaymentsPrinterSchedulerFactoryBean paymentsPrinterSchedulerFactoryBean;

//    @Autowired
//    public PaymentPrinterSchedulerImpl(PaymentsPrinterSchedulerFactoryBean paymentsPrinterSchedulerFactoryBean) {
//        this.paymentsPrinterSchedulerFactoryBean = paymentsPrinterSchedulerFactoryBean;
//    }

    @Override
    public Scheduler startScheduler() throws SchedulerException {
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = paymentsPrinterSchedulerFactoryBean.getScheduler();

        // and start it off
        scheduler.start();

        return scheduler;
    }

    @Override
    public void stopScheduler(final Scheduler scheduler) throws SchedulerException {
        scheduler.shutdown();
    }
}
