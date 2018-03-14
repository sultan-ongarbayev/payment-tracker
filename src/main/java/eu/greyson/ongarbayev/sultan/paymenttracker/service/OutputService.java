package eu.greyson.ongarbayev.sultan.paymenttracker.service;

import eu.greyson.ongarbayev.sultan.paymenttracker.console.Console;
import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import eu.greyson.ongarbayev.sultan.paymenttracker.repository.impl.PaymentsRepositoryImpl;
import eu.greyson.ongarbayev.sultan.paymenttracker.utils.NetAmountCalculator;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Service which is responsible for handling terminal output.
 */
public class OutputService extends BaseService {

    private Console console;
    private PaymentsRepositoryImpl paymentsRepository;

    public OutputService(
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
        List<Payment> payments = paymentsRepository.getAllPayments();
        List<Payment> netAmounts = NetAmountCalculator.getNetAmounts(payments);
        for (Payment amount : netAmounts) {
            console.write(amount.toString());
        }
    }
}
