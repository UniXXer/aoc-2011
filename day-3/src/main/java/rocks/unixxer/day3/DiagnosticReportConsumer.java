package rocks.unixxer.day3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class DiagnosticReportConsumer {

    private Integer reports = 0;

    private Integer[] signals;

    @ConsumeEvent("diagnosticReports")
    void consume(String command) {
        reports++;

        byte[] cmdInBytes = command.getBytes();

        if(signals == null) {
            signals = new Integer[cmdInBytes.length];
        }
        
        for(int i = 0; i < cmdInBytes.length; i++) {
            if(cmdInBytes[i] == 1) {
                signals[i]++;
            }
        }


    }      

    public Integer getGammaRate() {
        return 0;
    }

    public Integer getEpsilonrate() {
        return 0;
    }
}
