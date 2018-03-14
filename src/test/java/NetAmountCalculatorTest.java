import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import eu.greyson.ongarbayev.sultan.paymenttracker.utils.NetAmountCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NetAmountCalculatorTest {

    private List<Payment> payments;
    private List<Payment> correctNetAmounts;
    private List<Payment> netAmounts;

    private class PaymentComparator implements Comparator<Payment> {
        @Override
        public int compare(Payment p1, Payment p2) {
            return p1.getCurrency().compareTo(p2.getCurrency());
        }
    }

    @Before
    public void setup() {
        payments = new ArrayList<>();
        payments.add(new Payment("USD", new BigInteger("1000")));
        payments.add(new Payment("HKD", new BigInteger("100")));
        payments.add(new Payment("USD", new BigInteger("-100")));
        payments.add(new Payment("RMB", new BigInteger("2000")));
        payments.add(new Payment("HKD", new BigInteger("200")));

        correctNetAmounts = new ArrayList<>();
        correctNetAmounts.add(new Payment("USD", new BigInteger("900")));
        correctNetAmounts.add(new Payment("RMB", new BigInteger("2000")));
        correctNetAmounts.add(new Payment("HKD", new BigInteger("300")));

        netAmounts = NetAmountCalculator.getNetAmounts(payments);

        correctNetAmounts.sort(new PaymentComparator());
        netAmounts.sort(new PaymentComparator());
    }

    @Test
    public void testNetAmount() {
        if (correctNetAmounts.size() != netAmounts.size()) {
            Assert.fail();
        } else {
            for (int i = 0; i < correctNetAmounts.size(); i ++) {
                Assert.assertEquals(correctNetAmounts.get(i), netAmounts.get(i));
            }
        }
    }

    @Test
    public void testZeroAmount() {
        for (Payment amount : netAmounts) {
            if (amount.getAmount().compareTo(BigInteger.ZERO) == 0) {
                Assert.fail();
            }
        }
    }
}
