package eu.greyson.bsc.bscTest.view;

import eu.greyson.bsc.bscTest.service.PaymentService;
import eu.greyson.bsc.bscTest.service.dto.Payment;
import eu.greyson.bsc.bscTest.service.quartz.PaymentPrinterScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Payment tracker implementation. */
@Component
//@Scope("prototype")
public class PaymentTracker implements ApplicationRunner {
    private static final String QUIT_COMMAND = "quit";
    private final PaymentService paymentService;
    private final PaymentPrinterScheduler paymentPrinterScheduler;

    private List<Payment> payments = new ArrayList<>();
    private Scheduler scheduler;

    @Autowired
    public PaymentTracker(PaymentService paymentService, PaymentPrinterScheduler paymentPrinterScheduler) throws IOException {
        this.paymentService = paymentService;
        this.paymentPrinterScheduler = paymentPrinterScheduler;
    }

    @Override
    public void run(final ApplicationArguments applicationArguments) throws Exception {
        payments = paymentService.getAll("payments1");

        try {
            scheduler = paymentPrinterScheduler.startScheduler();
            sendToConsole();
            readFromConsole();
        }
        catch (SchedulerException e) {
            System.err.printf("Scheduler can't start%n");
        }
    }

    /** Show all payments in console. */
    public void sendToConsole() {
        for(Payment payment : payments) {
            System.out.println(payment);
        }
    }

    /** Read text from console until quit command is received. */
    private void readFromConsole() {
        Scanner scanInput = new Scanner(System.in);
        String data = scanInput.nextLine();

        if(!isQuitCommand(data)) {
            if(Payment.isPaymentValid(data)) {
                payments.add(new Payment(data));
            }
            else {
                System.err.printf("Payment: %s is not valid%n", data);
            }
            readFromConsole();
        }
        else {
            try {
                paymentPrinterScheduler.stopScheduler(scheduler);
            }
            catch (SchedulerException e) {
                System.err.printf("Scheduler can't stop%n");
            }
        }
    }

    /** @return if command is quit command. */
    private boolean isQuitCommand(String command) {
        return StringUtils.hasLength(command) && QUIT_COMMAND.equalsIgnoreCase(command);
    }
}
