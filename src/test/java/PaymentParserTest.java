import eu.greyson.ongarbayev.sultan.paymenttracker.exception.PaymentParserException;
import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import eu.greyson.ongarbayev.sultan.paymenttracker.utils.PaymentParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PaymentParserTest {

    private List<Payment> payments;
    private List<String> correctUserInput;

    @Before
    public void setup() {
        payments = new ArrayList<>();
        payments.add(new Payment("USD", new BigInteger("1000")));
        payments.add(new Payment("HKD", new BigInteger("100")));
        payments.add(new Payment("USD", new BigInteger("-100")));
        payments.add(new Payment("RMB", new BigInteger("2000")));
        payments.add(new Payment("HKD", new BigInteger("200")));

        correctUserInput = new ArrayList<>();
        correctUserInput.add("USD 1000");
        correctUserInput.add("HKD 100");
        correctUserInput.add("USD -100");
        correctUserInput.add("RMB 2000");
        correctUserInput.add("HKD 200");
    }

    @Test
    public void testCorrectUserInput() {
        for (int i = 0; i < correctUserInput.size(); i++) {
            Payment payment = PaymentParser.parse(correctUserInput.get(i));
            Assert.assertEquals(payment, payments.get(i));
        }
    }

    @Test(expected = PaymentParserException.class)
    public void testNotEnoughDataForPaymentSubmission() {
        String incorrectUserInput = "USD";
        PaymentParser.parse(incorrectUserInput);
    }

    @Test(expected = PaymentParserException.class)
    public void testInvalidDataFormat() {
        String incorrectUserInput = "USD ten";
        PaymentParser.parse(incorrectUserInput);
    }

    @Test(expected = PaymentParserException.class)
    public void testTooManyArguments() {
        String incorrectUserInput = "USD 100 200";
        PaymentParser.parse(incorrectUserInput);
    }
}
