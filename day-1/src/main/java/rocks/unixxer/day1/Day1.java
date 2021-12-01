package rocks.unixxer.day1;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Day1 {

    public static void main(String... args) {
        Quarkus.run(Start.class, args);
        
    }
    
    public static class Start implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
    
}
