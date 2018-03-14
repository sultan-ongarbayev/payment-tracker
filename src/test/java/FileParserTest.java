import eu.greyson.ongarbayev.sultan.paymenttracker.exception.PaymentParserException;
import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import eu.greyson.ongarbayev.sultan.paymenttracker.utils.FileParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FileParserTest {

    private File resourcesDirectory = new File("src/test/resources");

    private File correctPaymentsFile = new File(resourcesDirectory, "correct-payments-data.txt");
    private File incorrectPaymentsFile1 = new File(resourcesDirectory, "incorrect-payments-data-1.txt");
    private File incorrectPaymentsFile2 = new File(resourcesDirectory, "incorrect-payments-data-2.txt");
    private File incorrectPaymentsFile3 = new File(resourcesDirectory, "incorrect-payments-data-3.txt");

    private List<Payment> payments;

    @Before
    public void setup() {
        payments = new ArrayList<>();
        payments.add(new Payment("USD", new BigInteger("1000")));
        payments.add(new Payment("HKD", new BigInteger("100")));
        payments.add(new Payment("USD", new BigInteger("-100")));
        payments.add(new Payment("RMB", new BigInteger("2000")));
        payments.add(new Payment("HKD", new BigInteger("200")));
    }

    @Test
    public void testCorrectFileInput() {
        List<Payment> correctPayments = FileParser.readPaymentsFromFile(correctPaymentsFile);
        for (int i = 0; i < correctPayments.size(); i++) {
            Assert.assertEquals(correctPayments.get(i), payments.get(i));
        }
    }

    @Test(expected = PaymentParserException.class)
    public void testNotEnoughDataForPaymentSubmissionInFile() {
        FileParser.readPaymentsFromFile(incorrectPaymentsFile1);
    }

    @Test(expected = PaymentParserException.class)
    public void testInvalidDataFormatInFile() {
        FileParser.readPaymentsFromFile(incorrectPaymentsFile2);
    }

    @Test(expected = PaymentParserException.class)
    public void testTooManyArgumentsInFile() {
        FileParser.readPaymentsFromFile(incorrectPaymentsFile3);
    }
}
