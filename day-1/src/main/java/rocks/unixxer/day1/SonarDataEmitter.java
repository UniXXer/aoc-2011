package rocks.unixxer.day1;

import java.io.InputStreamReader;
import java.util.Scanner;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.mutiny.core.eventbus.EventBus;

@ApplicationScoped
public class SonarDataEmitter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SonarDataEmitter.class);


    @Inject
    EventBus bus; 

    public void emitData() {
        try (Scanner s = new Scanner(new InputStreamReader(SonarDataEmitter.class.getResourceAsStream("/input.txt")))) {
            while (s.hasNext()) {
                bus.publish("sonarData", s.nextInt());
            }
        }

        LOGGER.info("SonarDataEmitter finished...");
    }
}
