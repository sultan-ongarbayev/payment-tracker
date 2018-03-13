package eu.greyson.ongarbayev.sultan.paymenttracker;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PaymentTracker {

    private Console console;

    public static void main(String[] args) {
        new PaymentTracker().start();
    }

    private void start() {
        console = new Console("Prompt>");

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            public void run() {
                console.write("Message from the server.");
            }
        }, 0, 5, TimeUnit.SECONDS);

        String line = null;
        while((line = console.readLine()) != null) {
            console.write(line);
        }
    }
}
