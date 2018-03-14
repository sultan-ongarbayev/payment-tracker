package eu.greyson.ongarbayev.sultan.paymenttracker.main;


import eu.greyson.ongarbayev.sultan.paymenttracker.console.Console;
import eu.greyson.ongarbayev.sultan.paymenttracker.repository.impl.PaymentsRepositoryImpl;
import eu.greyson.ongarbayev.sultan.paymenttracker.service.InputService;
import eu.greyson.ongarbayev.sultan.paymenttracker.service.OutputService;
import eu.greyson.ongarbayev.sultan.paymenttracker.utils.CommandLineArgumentsParser;
import eu.greyson.ongarbayev.sultan.paymenttracker.utils.FileParser;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main class which contains application logic.
 * It's also responsible for initializing {@link Console} and starting
 * new threads.
 */
public class PaymentTracker {

    private PaymentsRepositoryImpl paymentsRepository;

    public PaymentTracker() {
        paymentsRepository = new PaymentsRepositoryImpl();
    }

    private void start(String[] args) {
        Console console = new Console();

        CommandLineArgumentsParser cmdParser = new CommandLineArgumentsParser(args);
        if (cmdParser.hasFilename()) {
            paymentsRepository.addPayments(FileParser.readPaymentsFromFile(new File(cmdParser.getFilename())));
        }

        ExecutorService inputExecutor = Executors.newSingleThreadExecutor();
        ScheduledExecutorService outputExecutor = Executors.newSingleThreadScheduledExecutor();

        InputService inputService = new InputService(console, paymentsRepository, inputExecutor, outputExecutor);
        OutputService outputService = new OutputService(console, paymentsRepository, inputExecutor, outputExecutor);

        inputExecutor.submit(inputService);
        outputExecutor.scheduleAtFixedRate(
                        outputService,
                        0,
                        1,
                        TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        new PaymentTracker().start(args);
    }
}
