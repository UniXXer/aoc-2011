package rocks.unixxer.day6;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LaternenfishSimulator {

    public Integer calculatePopulation(String startState, int days) {
        List<Laternenfish> population = new ArrayList<>();

        String[] fishes = startState.split(",");
        Arrays.asList(fishes).forEach((fish) -> population.add(new Laternenfish(Integer.parseInt(fish))));

        for(int i = 0; i < days; i++) {
            long newFishes = population.stream()
                .filter((f) -> {
                    f.age();
                    return f.hasBorn();
                })
                .count();

                for(int j = 0; j < newFishes; j++) {
                    population.add(new Laternenfish());
                }
        }

        return population.size();
    }
    
}