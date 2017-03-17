package eu.greyson.bsc.bscTest.service.quartz;

import eu.greyson.bsc.bscTest.view.PaymentTracker;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Quartz payment printer job - continuous print payments. */
public class PaymentPrinterJob implements Job {
    @Override
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        PaymentTracker paymentTracker = (PaymentTracker) jobExecutionContext.getJobDetail().getJobDataMap().get("paymentTracker");
        paymentTracker.sendToConsole();
    }
}
