package rocks.unixxer.day1;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class SolveTask1 {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {               
        LOGGER.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");
    }

    public Integer solve(String filename) throws FileNotFoundException {
        List<Integer> readData = readData(filename);
        return sonarSweepCountIncreases(readData);
    }

    public Integer sonarSweepCountIncreases(List<Integer> sonarData) {

        
        return 0;
    }

    public List<Integer> readData(String filename) throws FileNotFoundException {
        ArrayList<Integer> result = new ArrayList<>();

        try (Scanner s = new Scanner(new InputStreamReader(SolveTask1.class.getResourceAsStream(filename)))) {
            while (s.hasNext()) {
                result.add(s.nextInt());
            }
            return result;
        }
    }
}
