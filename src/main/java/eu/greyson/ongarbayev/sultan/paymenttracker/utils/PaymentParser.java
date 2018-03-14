package eu.greyson.ongarbayev.sultan.paymenttracker.utils;

import eu.greyson.ongarbayev.sultan.paymenttracker.exception.PaymentParserException;
import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/**
 * This class is responsible for parsing strings to {@link Payment} objects.
 */
@Slf4j
public class PaymentParser {

    /**
     * Parses {@link String} to {@link Payment} object
     * @param str String which has payment entry
     * @return Payment object
     * @throws PaymentParserException
     */
    public static Payment parse(String str) throws PaymentParserException {
        String[] words = str.split(" ");
        if (words.length != 2) {
            throw new PaymentParserException("Invalid data in string: " + str);
        }
        try {
            Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new PaymentParserException("Invalid data in string: " + str, e);
        }
        return new Payment(words[0], new BigInteger(words[1]));
    }
}
