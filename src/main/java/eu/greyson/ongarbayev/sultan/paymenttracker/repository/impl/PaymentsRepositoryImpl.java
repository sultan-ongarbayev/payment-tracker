package eu.greyson.ongarbayev.sultan.paymenttracker.repository.impl;

import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import eu.greyson.ongarbayev.sultan.paymenttracker.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple in-memory implementation of repository interface.
 */
public class PaymentsRepositoryImpl implements PaymentRepository {

    private List<Payment> payments;

    public PaymentsRepositoryImpl() {
        this.payments = new ArrayList<>();
    }

    public synchronized void addPayment(Payment payment) {
        payments.add(payment);
    }

    public void addPayments(List<Payment> payments) {
        for (Payment payment : payments) {
            addPayment(payment);
        }
    }

    public synchronized List<Payment> getAllPayments() {
        return payments;
    }
}
