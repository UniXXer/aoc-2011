package rocks.unixxer.day3;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.LookAndFeel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class DiagnosticReportConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticReportConsumer.class);

    private Integer reports = 0;

    private int[] signals;

    private List<String> allCommands = new ArrayList<>();

    @ConsumeEvent("diagnosticReports")
    void consume(String command) {
        reports++;

        if(signals == null) {
            signals = new int[command.length()];
        }
        
        for(int i = 0; i < command.length(); i++) {
            if(command.charAt(i) == '1') {
                signals[i]++;
            }
        }

        allCommands.add(command);
    }      
    
    private String mostCommonBits() {
        String result = new String();

        double half = reports / 2;

        for (int i = 0; i < signals.length; i++) {
            if(signals[i] > half) {
                result += "1";
            } else {
                result += "0";
            }
        }

        return result;
    }

    private String leastCommonBits() {
        String result = new String();

        double half = reports / 2;

        for (int i = 0; i < signals.length; i++) {
            if(signals[i] > half) {
                result += "0";
            } else {
                result += "1";
            }
        }

        return result;
    }

    public Integer getGammaRate() {
        return Integer.valueOf(mostCommonBits().toString(), 2);
    }

    public Integer getEpsilonrate() {
        return Integer.valueOf(leastCommonBits().toString(), 2);
    }

    public Integer findOxygenGeneratorRating() {
        return 0;
    }
}
