package eu.greyson.bsc.bscTest.view;

import eu.greyson.bsc.bscTest.service.PaymentService;
import eu.greyson.bsc.bscTest.service.dto.Payment;
import eu.greyson.bsc.bscTest.view.manager.impl.ConsoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/** Payment tracker implementation. */
@Component
public class PaymentTrackerController implements ApplicationRunner {
    private static final String QUIT_COMMAND = "quit";
    private static final String APPLICATION_ARGUMENT_FILE_KEY = "data";
    private static final String APPLICATION_DEFAULT_FILE = "payments1";

    private final PaymentService paymentService;
    private final ApplicationContext applicationContext;
    private final ConsoleManager consoleManager;

    private Queue<Payment> payments = new ConcurrentLinkedQueue<>();

    @Autowired
    public PaymentTrackerController(PaymentService paymentService, ApplicationContext applicationContext, ConsoleManager consoleManager) throws IOException {
        this.paymentService = paymentService;
        this.applicationContext = applicationContext;
        this.consoleManager = consoleManager;
    }

    @Override
    public void run(final ApplicationArguments applicationArguments) throws Exception {
        List<String> dataFiles;

        if(applicationArguments.containsOption(APPLICATION_ARGUMENT_FILE_KEY)) {
            dataFiles = applicationArguments.getOptionValues(APPLICATION_ARGUMENT_FILE_KEY);
        }
        else {
            dataFiles = Collections.singletonList(APPLICATION_DEFAULT_FILE);
        }

        dataFiles.forEach(data -> {
            try {
                payments.addAll(paymentService.getAllConcurrent(data));
            }
            catch (IOException e) {
                consoleManager.writeError("File: %s not found", data);
            }
        });
        sendToConsole();
        readFromConsole();
    }

    /** Show all payments in console. */
    public void sendToConsole() {
        for(Payment payment : payments) {
            consoleManager.writePayment(payment);
        }
    }

    /** Read text from console until quit command is received. */
    private void readFromConsole() {
        String data = consoleManager.readInput();

        if(!isQuitCommand(data)) {
            if(Payment.isPaymentValid(data)) {
                payments.add(new Payment(data));
            }
            else {
                consoleManager.writeError("Payment: %s is not valid", data);
            }
            readFromConsole();
        }
        else {
            SpringApplication.exit(applicationContext, () -> 0);
        }
    }

    /** @return if command is quit command. */
    private boolean isQuitCommand(String command) {
        return StringUtils.hasLength(command) && QUIT_COMMAND.equalsIgnoreCase(command);
    }
}
