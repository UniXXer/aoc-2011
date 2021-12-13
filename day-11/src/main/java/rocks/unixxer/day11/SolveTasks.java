package rocks.unixxer.day11;

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
    OctopusParty octopusParty;

    void onStart(@Observes StartupEvent ev) throws FileNotFoundException {               
        LOGGER.info("The application is starting...");
        readData("/input.txt");

        LOGGER.info("Puzzle 1 Result: {}", solveTask1());

        readData("/input.txt");
        LOGGER.info("Puzzle 2 Result: {}", solveTask2());
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");
    }

    public int solveTask1() {
        return octopusParty.partyFlasher(100, false);
    }

    public long solveTask2() {
        return octopusParty.partyFlasher(1000, true);
    }

    public void readData(String filename) throws FileNotFoundException {
        octopusParty.reset();
        try (Scanner s = new Scanner(new InputStreamReader(SolveTasks.class.getResourceAsStream(filename)))) {
            int lineNumber = 0;
            while (s.hasNext()) {
                octopusParty.addLine(s.nextLine(), lineNumber);
                lineNumber++;
            }
            LOGGER.info("Read {} lines.", lineNumber);
        }
    }
}
