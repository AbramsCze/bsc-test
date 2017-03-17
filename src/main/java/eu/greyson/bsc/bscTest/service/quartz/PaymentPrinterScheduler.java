package eu.greyson.bsc.bscTest.service.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/** Quartz payment printer scheduler interface. */
public interface PaymentPrinterScheduler {
    /**
     * Method start scheduler.
     * @return started scheduler.
     */
    Scheduler startScheduler() throws SchedulerException;

    /**
     * Method stop scheduler.
     * @param scheduler to stop.
     */
    void stopScheduler(Scheduler scheduler) throws SchedulerException;
}
