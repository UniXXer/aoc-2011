package rocks.unixxer.day5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MapOfVents {

    private List<Line> lines = new ArrayList<>();

    private Map<Point, Integer> field = new HashMap<>();

    public void parseLine(String line) {
        String[] pair = line.split("->");
        
        Point p1 = Point.of(pair[0]);
        Point p2 = Point.of(pair[1]);

        lines.add(new Line(p1, p2));
    }

    public void markLines() {
        field = new HashMap<>();
        for(Line line : lines) {
            if(!line.isDiagonal()) {
                line.getLine().forEach((p) -> {
                        Integer hits = field.get(p);
                        if(hits == null) {
                            field.put(p, 1);
                        } else {
                            field.put(p, hits+1);
                        }
                    });
            }
        }
    }

    public void markLinesWithDiagonal() {
        field = new HashMap<>();
        for(Line line : lines) {
            line.getLine().forEach((p) -> {
                    Integer hits = field.get(p);
                    if(hits == null) {
                        field.put(p, 1);
                    } else {
                        field.put(p, hits+1);
                    }
                });
        }
    }
    
    public long getMostDangerousAreasCount() {

        long count = field.entrySet()
            .stream()
            .filter((entry) -> entry.getValue() > 1)
            .count();

        return count;
    }
}
