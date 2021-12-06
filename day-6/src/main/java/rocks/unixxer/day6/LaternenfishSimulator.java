package rocks.unixxer.day6;

import java.util.List;
import java.util.stream.LongStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LaternenfishSimulator {

    public int calculatePopulation(String startState, int days) {
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

    public Long calculatePopulation2(String startState, int days) {
        long[] fishByAge = new long[9];

        String[] split = startState.split(",");
        for(int i = 0; i < split.length; i++) {
            fishByAge[Integer.parseInt(split[i])] ++;
        }

        for(int i = 0; i < days; i++) {
            long[] nextPopulation = new long[9];

            nextPopulation[8] = fishByAge[0];
            nextPopulation[6] = fishByAge[0];
        
            for(int j = 7; j >=0; j--) {
                nextPopulation[j] += fishByAge[j + 1];
            }

            fishByAge = nextPopulation;
        }

        return LongStream.of(fishByAge).sum();
    }
    
}