package eu.greyson.ongarbayev.sultan.paymenttracker.utils;

import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for calculating net amounts of
 * money of each particular currency.
 */
public class NetAmountCalculator {

    /**
     * Returns net amounts for each currency.
     * @param allPayments list of all payments
     * @return list of net amounts
     */
    public static List<Payment> getNetAmounts(List<Payment> allPayments) {
        Map<String, BigInteger> amountsMap = new HashMap<>();
        for (Payment payment : allPayments) {
            String currency = payment.getCurrency();
            BigInteger value = amountsMap.containsKey(currency)
                    ? amountsMap.get(currency)
                    : new BigInteger("0");
            BigInteger newValue = value.add(payment.getAmount());
            amountsMap.put(currency, newValue);
        }
        List<Payment> netAmounts = new ArrayList<>();
        for (Map.Entry<String, BigInteger> entry : amountsMap.entrySet()) {
            // Do not print net amounts with value of zero
            if (entry.getValue().compareTo(BigInteger.ZERO) != 0) {
                netAmounts.add(new Payment(entry.getKey(), entry.getValue()));
            }
        }
        return netAmounts;
    }
}
