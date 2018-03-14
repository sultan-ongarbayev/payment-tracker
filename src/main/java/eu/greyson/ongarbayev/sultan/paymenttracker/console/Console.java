package eu.greyson.ongarbayev.sultan.paymenttracker.console;

import lombok.extern.slf4j.Slf4j;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;

import java.io.IOException;

/**
 * This class represents virtual console, which is exclusively responsible  for
 * managing input and output to the terminal window. At first glance it may seem
 * like a small problem, but there may be situations when user writes something
 * to the terminal and receives message from the console at the same time. In some
 * conditions messages can overlap each other. Class below is able to solve such
 * problems.
 */
@Slf4j
public class Console {

    private String prompt;
    private Terminal terminal;
    private LineReader reader;
    private boolean reading;

    public Console() {
        this.reading = false;
        this.prompt = new AttributedStringBuilder()
                .style(AttributedStyle.BOLD.foreground(AttributedStyle.GREEN))
                .append("Payment > ").toAnsi();

        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .streams(System.in, System.out)
                    .name("Payment Tracker Terminal")
                    .build();
        } catch (IOException e) {
            log.error("Could not create terminal object.", e);
        }
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    /**
     * Writes message to the terminal window.
     * Every message is preceded by new line.
     * @param message message to print
     */
    public synchronized void write(String message) {
        if (reading) {
            reader.callWidget(LineReader.CLEAR);
            reader.getTerminal().writer().println(message);
            reader.callWidget(LineReader.REDRAW_LINE);
            reader.callWidget(LineReader.REDISPLAY);
            reader.getTerminal().writer().flush();
        } else {
            reader.getTerminal().writer().println(message);
            reader.getTerminal().writer().flush();
        }
    }

    /**
     * Reads message from the user input.
     * @return message
     */
    public String readLine() {
        reading = true;
        String line = null;
        try {
            line = reader.readLine(prompt);
        } catch (UserInterruptException e) {
            log.error("User input is interrupted.", e);
        } catch (EndOfFileException e) {
            log.error("User typed Ctrl + D.", e);
        }
        reading = false;
        return line;
    }

    /**
     * This method is responsible for closing all terminal - related resources.
     */
    public synchronized void close() {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        reader = null;
        try {
            terminal.close();
        } catch (IOException e) {
            log.error("Could not close terminal: " + terminal.getName(), e);
        }
    }
}
