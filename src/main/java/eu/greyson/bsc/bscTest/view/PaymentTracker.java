package eu.greyson.bsc.bscTest.view;

import eu.greyson.bsc.bscTest.service.PaymentService;
import eu.greyson.bsc.bscTest.service.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

/** Payment tracker implementation. */
@Component
public class PaymentTracker implements ApplicationRunner {
    private static final String QUIT_COMMAND = "quit";
    private final PaymentService paymentService;

    private Queue<Payment> payments = new ConcurrentLinkedQueue<>();

    @Autowired
    public PaymentTracker(PaymentService paymentService) throws IOException {
        this.paymentService = paymentService;
    }

    @Override
    public void run(final ApplicationArguments applicationArguments) throws Exception {
        payments = paymentService.getAllConcurrent("payments1");
        sendToConsole();
        readFromConsole();
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
    }

    /** @return if command is quit command. */
    private boolean isQuitCommand(String command) {
        return StringUtils.hasLength(command) && QUIT_COMMAND.equalsIgnoreCase(command);
    }
}
