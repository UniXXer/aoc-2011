package rocks.unixxer.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TheVulcano {

    private int[][] heightmap;

    private List<Point> pointsInBasin = new ArrayList<>();

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

    public int calculateRiskPoints() {
        return findLowPoints()
                .mapToInt((p) -> p.height + 1)
                .sum();
    }

    public int basinSizes() {
        pointsInBasin = new ArrayList<>();

        return findLowPoints()
            .map(this::calculateBasinSize)
            .sorted((o1, o2) -> o2.compareTo(o1))
            .limit(3)
            .mapToInt(Integer::valueOf)
            .reduce(1, (a, b) -> a * b);
    }

    private Stream<Point> findLowPoints() {
        return IntStream.range(0, heightmap.length).boxed().flatMap((i) -> {
            return IntStream.range(0, heightmap[0].length).mapToObj((j) -> {
                int myHeight = heightmap[i][j];
                List<Point> adjs = Arrays.asList(new Point(i - 1, j, 0), new Point(i + 1, j, 0), new Point(i, j - 1, 0), new Point(i, j + 1, 0));

                return isLowPoint(myHeight, adjs) ? new Point(i, j, myHeight) : null;
            }).filter((p) -> p != null);
        });
    }
    
    private boolean isLowPoint(int height, List<Point> adjs) {
        return !adjs.stream()
            .filter(this::validPoint)
            .filter((p) -> height >= heightmap[p.x][p.y])
            .findAny().isPresent();

    }

    private int calculateBasinSize(final Point lowPoint) {
        pointsInBasin.add(lowPoint);

        List<Point> adjs = Arrays.asList(
            new Point(lowPoint.x - 1, lowPoint.y, 0), 
            new Point(lowPoint.x + 1, lowPoint.y, 0), 
            new Point(lowPoint.x, lowPoint.y - 1, 0), 
            new Point(lowPoint.x, lowPoint.y + 1, 0));

        return adjs.stream()
            .filter(this::validPoint)
            .filter((p) -> !pointsInBasin.contains(p))
            .filter((p) -> heightmap[p.x][p.y] < 9 && heightmap[p.x][p.y] > lowPoint.height)
            .map((p) -> calculateBasinSize(new Point(p.x, p.y, heightmap[p.x][p.y])))
            .mapToInt(Integer::valueOf)
            .sum() + 1;
    }

    private boolean validPoint(Point p) {
        int dimX = heightmap.length;
        int dimY = heightmap[0].length;
        return p.x > -1 && p.y > -1 && p.x < dimX && p.y < dimY;
    }

    record Point(int x, int y, int height) {
        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Point)) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    };
}
