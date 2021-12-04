package rocks.unixxer.day4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Day4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day4.class);

    public static void main(String... args) {
        Quarkus.run(Start.class, args);
    }
    
    public static class Start implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            Quarkus.waitForExit();
            return 0;
        }
    }   
}