package rocks.unixxer.day1;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SonarDataConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SonarDataConsumer.class);

    private Integer lastMeasurement = null;
    private Integer depthIncreasedCount = 0;

    private Integer lastMeasurementNR = null;
    private Integer depthIncreasedCountNR = 0;

    @ConsumeEvent("sonarData")
    void consume(Integer depth) {
        if(lastMeasurement != null) {
            if(isDepthIncreases(depth, lastMeasurement)) {
                depthIncreasedCount++;
            }
        }
        
        lastMeasurement = depth;
    }

    @ConsumeEvent("sonarDataNoiseReduced")
    void consumeNoiseReduced(Integer depth) {
        if(lastMeasurementNR != null) {
            if(isDepthIncreases(depth, lastMeasurementNR)) {
                depthIncreasedCountNR++;
            }
        }
        
        lastMeasurementNR = depth;
    }
    
    private boolean isDepthIncreases(Integer current, Integer last) {
        return current > last;
    } 

    public Integer getDepthIncreasedCount() {
        return depthIncreasedCount;
    }

    public Integer getNRDepthIncreasedCount() {
        return depthIncreasedCountNR;
    }
}
