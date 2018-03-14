package eu.greyson.ongarbayev.sultan.paymenttracker.exception;

/**
 * This class represents runtime exception which can be thrown
 * during payment parsing from {@link String} object.
 */
public class PaymentParserException extends RuntimeException {

    public PaymentParserException() {
    }

    public PaymentParserException(String message) {
        super(message);
    }

    public PaymentParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
