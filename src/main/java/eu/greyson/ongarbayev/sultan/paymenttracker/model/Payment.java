package eu.greyson.ongarbayev.sultan.paymenttracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * This class represents payment data model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String currency;
    private BigInteger amount;

    @Override
    public String toString() {
        return currency + " " + amount.toString();
    }
}
