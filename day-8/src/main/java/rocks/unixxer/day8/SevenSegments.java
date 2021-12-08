package rocks.unixxer.day8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    // Big Thanks to https://www.reddit.com/r/adventofcode/comments/rbj87a/comment/hnr6eil/?utm_source=share&utm_medium=web2x&context=3   

    public long decodeDIgits(List<String> inputs) {
        List<Integer> signatures = Arrays.asList(42, 17, 34, 39, 30, 37, 41, 25, 49, 45);
        long total = 0;
        for (String line : inputs) {
            String[] parts = line.split(" \\| ");
            String output = parts[1];

            for (char c : "abcdefg".toCharArray()) {
                output = output.replace(c + "", String.valueOf(parts[0].chars()
                        .filter(cc -> cc == c).count()));
            }
            
            String number = Arrays.stream(output.split(" "))
                    .map(d -> d.chars().map(Character::getNumericValue)
                            .reduce(0, Integer::sum))
                    .map(signatures::indexOf)
                    .map(String::valueOf)
                    .collect(Collectors.joining());
        
            total += Integer.parseInt(number);
        }

        return total;
    }
}
