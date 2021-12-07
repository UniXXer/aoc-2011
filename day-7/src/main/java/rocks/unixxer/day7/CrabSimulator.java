package rocks.unixxer.day7;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CrabSimulator {


    public Integer cheapestHorizontalMatch(String inputPositions, boolean expensiveMode) {
        final List<String> listOfStrings = Arrays.asList(inputPositions.split(","));

        Supplier<IntStream> streamSupplier = () -> listOfStrings.stream().mapToInt((s) -> Integer.parseInt(s));

        int maxPosition = streamSupplier.get().max().getAsInt();
        int minPosition = streamSupplier.get().min().getAsInt();

        Optional<PosiitonCost> cheapestMatch = IntStream.range(minPosition, maxPosition + 1)
            .mapToObj((targetPosition) -> {
                int targetPositionCosts = streamSupplier.get().map((position) -> {
                        var width = Math.abs(targetPosition - position);
                        if(!expensiveMode) {
                            return width;
                        }

                        return (width * (width+1)) / 2;
                    })
                    .sum();

                return new PosiitonCost(targetPosition, targetPositionCosts);
            }).sorted((pc1, pc2) -> pc1.costs.compareTo(pc2.costs))
            .findFirst();

        return cheapestMatch.get().costs;
    }


    public record PosiitonCost(Integer position, Integer costs) {
    }
    
}
