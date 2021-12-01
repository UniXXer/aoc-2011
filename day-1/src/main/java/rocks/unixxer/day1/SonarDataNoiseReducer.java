package rocks.unixxer.day1;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.mutiny.core.eventbus.EventBus;

public class SonarDataNoiseReducer {
    private static final Integer WINDOW_SIZE = 3;

    @Inject
    EventBus bus; 

    private List<Integer> measurementList= new ArrayList<>();

    @ConsumeEvent("sonarData")
    void reduceNoise(Integer depth) {

        measurementList.add(depth);

        if(measurementList.size() >= WINDOW_SIZE) {
            int depthSum = measurementList.stream()
                .mapToInt(Integer::intValue)
                .sum();
                
            bus.publish("sonarDataNoiseReduced", depthSum);

            measurementList.remove(0);
        }
    }
}
