package eu.greyson.ongarbayev.sultan.paymenttracker;

import lombok.extern.slf4j.Slf4j;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

@Slf4j
public class Console {

    private String prompt;
    private Terminal terminal;
    private LineReader reader;

    public Console(String prompt) {
        this.prompt = prompt;

        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .streams(System.in, System.out)
                    .build();
        } catch (IOException e) {
            log.error("Could not create terminal object.", e);
        }
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public synchronized void write(String message) {
        reader.callWidget(LineReader.CLEAR);
        reader.getTerminal().writer().println(message);
        reader.callWidget(LineReader.REDRAW_LINE);
        reader.callWidget(LineReader.REDISPLAY);
        reader.getTerminal().writer().flush();
    }

    public String readLine() {
        return reader.readLine(prompt);
    }
}
