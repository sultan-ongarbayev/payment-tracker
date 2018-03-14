package eu.greyson.ongarbayev.sultan.paymenttracker.repository;

import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;

import java.util.List;

/**
 * Interface for all possible repositories.
 * Declares methods which should have every particular repository implementation.
 */
public interface PaymentRepository {

    /**
     * Adds new payment to the repository.
     * @param payment payment to save
     */
    void addPayment(Payment payment);

    /**
     * Adds list of payments.
     * @param payments list of payments to save
     */
    void addPayments(List<Payment> payments);

    /**
     * Returns all saved payments in repository.
     * @return all payments
     */
    List<Payment> getAllPayments();
}
