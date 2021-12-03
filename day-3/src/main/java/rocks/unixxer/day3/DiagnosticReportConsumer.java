package rocks.unixxer.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class DiagnosticReportConsumer {
    @Inject
    Logger LOGGER;

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
    
    private String mostCommonBits(int[] signals, Integer reports) {
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

    private String leastCommonBits(int[] signals, Integer reports) {
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
        return Integer.valueOf(mostCommonBits(signals, reports).toString(), 2);
    }

    public Integer getEpsilonrate() {
        return Integer.valueOf(leastCommonBits(signals, reports).toString(), 2);
    }

    public Integer findOxygenGeneratorRating() {
        List<String> commads = new ArrayList<>(allCommands);

        String oxygenRatingString = reduceOxygenGeneratorRating(commads, 0);
        
        LOGGER.info(oxygenRatingString);
        return Integer.valueOf(oxygenRatingString, 2);
    }

    private String reduceOxygenGeneratorRating(final List<String> commads, final int round) {
        int sumOfOne = commads.stream()
            .map((cmd) -> (cmd.charAt(round) == '1' ? 1 : 0))
            .mapToInt(Integer::intValue)
            .sum();
        
        boolean keepOne = (double) sumOfOne >= ( ((double)commads.size())/2.0);

        List<String> subCommands = commads.stream().filter((cmd) -> {
            if(keepOne) {
                return cmd.charAt(round) == '1';
            } else {
                return cmd.charAt(round) == '0';
            }
        }).collect(Collectors.toList());

        if(subCommands.size() > 1) {
            return reduceOxygenGeneratorRating(subCommands, (round+1));
        } 

        return subCommands.get(0);
    }
}
