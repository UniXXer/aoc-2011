package rocks.unixxer.day8;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SevenSegments {
    
    public long findEasyDigits(List<String> inputs) {

        long count = inputs.stream()
            .map((s) -> s.split("\\|")[1])
            .map((s) -> Arrays.asList(s.split(" ")))
            .flatMap((l) -> l.stream())
            .filter((s) -> (s.length() == 2 ||  s.length() == 3 || s.length() == 4 || s.length() == 7))
            .count();              
    
        return count;
    }

}
