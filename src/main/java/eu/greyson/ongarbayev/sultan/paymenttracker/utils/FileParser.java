package eu.greyson.ongarbayev.sultan.paymenttracker.utils;

import eu.greyson.ongarbayev.sultan.paymenttracker.exception.PaymentParserException;
import eu.greyson.ongarbayev.sultan.paymenttracker.model.Payment;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for reading payments records from file.
 * Makes use of {@link PaymentParser} class to parse individual lines of file.
 */
@Slf4j
public class FileParser {

    /**
     * Reads payments from file
     * @param file file to read payments from
     * @return {@link List} of payments
     */
    public static List<Payment> readPaymentsFromFile(File file) {
        List<Payment> payments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Payment payment = PaymentParser.parse(line);
                payments.add(payment);
            }
        } catch (FileNotFoundException e) {
            log.error("Could not find file: " + file.getPath(), e);
        } catch (IOException e) {
            log.error("Could not read file: " + file.getPath(), e);
        }

        return payments;
    }
}
