package rocks.unixxer.day9;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TheVulcano {

    private int[][] heightmap;

    public void parseLinesToHeightMap(List<String> lines) {
        int dimensionI = lines.size();
        int dimensionJ = lines.get(0).length();
        heightmap = new int[dimensionI][dimensionJ];

        for(int i = 0; i < dimensionI; i++) {
            String line = lines.get(i);

            for(int j = 0; j < dimensionJ; j++) {
                int theInt = Integer.parseInt(line.charAt(j) + "");
                heightmap[i][j] = theInt;
            }
        }
    }

    public int findLowPoints() {
        
        return IntStream.range(0, heightmap.length).boxed().map((i) -> {
            return IntStream.range(0, heightmap[0].length).map((j) -> {
                int myHeight = heightmap[i][j];
                List<Point> adjs = Arrays.asList(new Point(i - 1, j), new Point(i + 1, j), new Point(i, j - 1), new Point(i, j + 1));

                return isLowPoint(myHeight, adjs) ? myHeight + 1 : 0;
            }).sum();
        }).collect(Collectors.summingInt(Integer::valueOf));
    }
    
    private boolean isLowPoint(int height, List<Point> adjs) {
        int dimX = heightmap.length;
        int dimY = heightmap[0].length;
        return !adjs.stream()
            .filter((p) -> p.x > -1 && p.y > -1 && p.x < dimX && p.y < dimY)
            .filter((p) -> height >= heightmap[p.x][p.y])
            .findAny().isPresent();

    }

    record Point(int x, int y) {};
}
