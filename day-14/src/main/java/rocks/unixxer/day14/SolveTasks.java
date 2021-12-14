package rocks.unixxer.day14;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
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
    PolymerTemplate polymerTemplate;

    void onStart(@Observes StartupEvent ev) throws FileNotFoundException {               
        LOGGER.info("The application is starting...");
        
        polymerTemplate.reset();
        readData("/input.txt");
        polymerTemplate.growPolymer(10);

        LOGGER.info("Puzzle 1 Result: {}", solveTask1());

        //polymerTemplate.reset();
        //readData("/input.txt");
        //polymerTemplate.growPolymer(40);

        LOGGER.info("Puzzle 2 Result: {}", solveTask2());
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");
    }

    public long solveTask1() {        
        return polymerTemplate.analysePolymer();
    }

    public long solveTask2() {
        return polymerTemplate.analysePolymer();
    }

    public void readData(String filename) throws FileNotFoundException {
        try (Scanner s = new Scanner(new InputStreamReader(SolveTasks.class.getResourceAsStream(filename)))) {

            int lineNumber = 0;
           
            while (s.hasNext()) {
                polymerTemplate.parseInput(s.nextLine(), lineNumber);                
                lineNumber++;
            }
        }
    }
}
