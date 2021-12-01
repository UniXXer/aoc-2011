package rocks.unixxer.day1;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class SolveTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolveTasks.class);

    @Inject
    SonarDataEmitter sonarDataEmitter;

    @Inject
    SonarDataConsumer sonarDataConsumer;

    void onStart(@Observes StartupEvent ev) throws FileNotFoundException {               
        LOGGER.info("The application is starting...");

        sonarDataEmitter.emitData();

        LOGGER.info("#Puzzle1 - Found {} increases in the input data!", solveTask1("/input.txt"));
        LOGGER.info("#Puzzle2 - Found {} increases in the input data!", solveTask2("/input.txt"));
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");

        LOGGER.info("Puzzle 1 Result: {}", sonarDataConsumer.getDepthIncreasedCount());
        LOGGER.info("Puzzle 2 Result: {}", sonarDataConsumer.getNRDepthIncreasedCount());

    }

    public Integer solveTask1(String filename) throws FileNotFoundException {
        List<Integer> readData = readData(filename);
        return sonarSweepCountIncreases(readData);
    }

    public Integer solveTask2(String filename) throws FileNotFoundException {
        List<Integer> readData = readData(filename);
        return sonarSweepCountIncreases(reduceNoise(readData, 3));
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

    public List<Integer> reduceNoise(List<Integer> data, Integer windowSize) {
        List<Integer> noiseReduced = new ArrayList<>();

        for(int i = windowSize - 1; i < data.size(); i++) { 
            Integer noiseReduction = data.get(i) + data.get(i - 1) + data.get(i - 2);
            noiseReduced.add(noiseReduction);
        }

        return noiseReduced;
    }

    public List<Integer> readData(String filename) throws FileNotFoundException {
        ArrayList<Integer> result = new ArrayList<>();

        try (Scanner s = new Scanner(new InputStreamReader(SolveTasks.class.getResourceAsStream(filename)))) {
            while (s.hasNext()) {
                result.add(s.nextInt());
            }
            return result;
        }
    }
}
