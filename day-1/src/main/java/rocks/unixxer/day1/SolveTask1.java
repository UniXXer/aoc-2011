package rocks.unixxer.day1;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class SolveTask1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolveTask1.class);

    void onStart(@Observes StartupEvent ev) throws FileNotFoundException {               
        LOGGER.info("The application is starting...");

        LOGGER.info("Found {} increases in the input data!", solve("/input.txt"));
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");
    }

    public Integer solve(String filename) throws FileNotFoundException {
        List<Integer> readData = readData(filename);
        return sonarSweepCountIncreases(readData);
    }

    public Integer sonarSweepCountIncreases(List<Integer> sonarData) {
        Integer result = 0;

        for(int i = 1; i < sonarData.size(); i++) { 
            if(sonarData.get(i) > sonarData.get(i-1)) {
                result++;
            }
        }
        
        return result;
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
