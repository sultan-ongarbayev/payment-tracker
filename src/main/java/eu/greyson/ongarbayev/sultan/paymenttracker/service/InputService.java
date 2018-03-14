package eu.greyson.ongarbayev.sultan.paymenttracker.service;

import eu.greyson.ongarbayev.sultan.paymenttracker.console.Console;
import eu.greyson.ongarbayev.sultan.paymenttracker.exception.PaymentParserException;
import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import eu.greyson.ongarbayev.sultan.paymenttracker.repository.impl.PaymentsRepositoryImpl;
import eu.greyson.ongarbayev.sultan.paymenttracker.utils.PaymentParser;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * Service which is responsible for user input handling.
 */
@Slf4j
public class InputService extends BaseService {

    private Console console;
    private PaymentsRepositoryImpl paymentsRepository;

    public InputService(
            Console console,
            PaymentsRepositoryImpl paymentsRepository,
            ExecutorService inputExecutorService,
            ExecutorService outputExecutorService
    ) {
        super(inputExecutorService, outputExecutorService);
        this.console = console;
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public void run() {
        String line;
        while ((line = console.readLine()) != null) {
            if (line.equalsIgnoreCase("quit")) {
                shutdown();
                return;
            }
            try {
                Payment payment = PaymentParser.parse(line);
                paymentsRepository.addPayment(payment);
            } catch (PaymentParserException e) {
                log.error("Could not parse user input.", e);
            }
        }
    }

    private void shutdown() {
        console.close();
        inputExecutorService.shutdown();
        outputExecutorService.shutdown();
    }
}
