package rocks.unixxer.day2;

import javax.inject.Inject;

import io.quarkus.vertx.ConsumeEvent;

public class SubmarineCommandConsumer {

    @Inject
    SubmarineController submarineController;

    @ConsumeEvent("submarineCommand")
    void consume(String command) {
        submarineController.command(command);
    }   
}
