package eu.greyson.bsc.bscTest.view;

import eu.greyson.bsc.bscTest.exception.FileNotFoundException;
import eu.greyson.bsc.bscTest.service.PaymentService;
import eu.greyson.bsc.bscTest.service.dto.Payment;
import eu.greyson.bsc.bscTest.view.manager.impl.ConsoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/** Payment tracker implementation. */
@Component
public class PaymentTrackerController implements ApplicationRunner {
    @Value("${payment.argument.file.key}") private String argumentFileKey;
    @Value("${payment.quit.command}") private String quitCommand;

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

        if(applicationArguments.containsOption(argumentFileKey)) {
            dataFiles = applicationArguments.getOptionValues(argumentFileKey);

            dataFiles.forEach(data -> {
                try {
                    payments.addAll(paymentService.getAllConcurrent(data));
                }
                catch (IOException | URISyntaxException | FileNotFoundException e) {
                    consoleManager.writeError("File: %s not found", data);
                }
            });
            sendToConsole();
            readFromConsole();
        }
        else {
            consoleManager.writeError("No file to read");
        }
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
        return StringUtils.hasLength(command) && quitCommand.equalsIgnoreCase(command);
    }
}
