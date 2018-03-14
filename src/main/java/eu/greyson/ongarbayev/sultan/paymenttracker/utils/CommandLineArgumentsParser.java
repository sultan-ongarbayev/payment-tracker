package eu.greyson.ongarbayev.sultan.paymenttracker.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.Arrays;

/**
 * This class is responsible for command line arguments parsing.
 */
@Slf4j
public class CommandLineArgumentsParser {

    private static final String FILE_OPTION = "file";
    private static final String FILE_OPTION_SHORT = "f";

    private CommandLine commandLine;

    public CommandLineArgumentsParser(String[] arguments) {
        this.commandLine = generateCommandLine(generateOptions(), arguments);
    }

    public boolean hasFilename() {
        return commandLine.hasOption(FILE_OPTION);
    }

    public String getFilename() {
        if (hasFilename()) {
            return commandLine.getOptionValue(FILE_OPTION);
        } else {
            return null;
        }
    }

    private Options generateOptions() {
        Option fileOption = Option.builder(FILE_OPTION_SHORT)
                .required(false)
                .hasArg(true)
                .longOpt(FILE_OPTION)
                .desc("File with payments data.")
                .build();
        return new Options()
                .addOption(fileOption);
    }

    private CommandLine generateCommandLine(Options options, String[] arguments) {
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, arguments);
        } catch (ParseException e) {
            log.error("Could not parse command line arguments: " + Arrays.toString(arguments), e);
        }
        return commandLine;
    }
}
