package rocks.unixxer.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

@ApplicationScoped
public class Origami {

    @Inject
    Logger log;

    private Set<Point> points = new HashSet<>();
    private List<FoldInstruction> instructions = new ArrayList<>();

    public void reset() {
        points = new HashSet<>();
        instructions = new ArrayList<>();
    }

    public void fold(int steps) {
        if(steps == -1) {
            steps = instructions.size();
        }

        for(int i = 0; i < steps; i++) {
            Set<Point> newPoints = new HashSet<>();
            FoldInstruction foldTo = instructions.get(i);

            for(Point p : points) {
                int newX = -1;
                int newY = -1;

                switch(foldTo.axile) {
                    case "x": {
                        newY = p.y;

                        if(p.x > foldTo.amount) {
                            newX = p.x - ((p.x - foldTo.amount) * 2);
                        } else {
                            newX = p.x;
                        }
                    }; break;
                    case "y": {
                        newX = p.x;
                        
                        if(p.y > foldTo.amount) {
                            newY = p.y - ((p.y - foldTo.amount) * 2);
                        } else {
                            newY = p.y;
                        }
                    };
                }

                newPoints.add(new Point(newX, newY));
            }

            this.points = newPoints;
        }        
    }

    public void draw() {
        int maxX = points.stream().mapToInt((p) -> p.x).max().getAsInt() + 1;
        int maxY = points.stream().mapToInt((p) -> p.y).max().getAsInt() + 1;

        String[][] drawing = new String[maxX][maxY];

        points.forEach((p) -> drawing[p.x][p.y] = "#");

        for(int y = 0; y < maxY; y++) {
            String output = "";
            for(int x = 0; x < maxX; x++) {
                String s = drawing[x][y];
                if(s != null) {
                    output += s;
                } else {
                    output += " ";
                }
            }
            log.info(output);
        }
    }

    public void parseLine(String s) {
        if(s == null || s.isEmpty()) {
            return;
        }

        if(s.startsWith("fold along ")) {
            String[] split = s.substring(11).split("=");
            instructions.add(new FoldInstruction(split[0], Integer.parseInt(split[1])));
        } else {
            String[] split = s.split(",");
            points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
    }

    public record Point(int x, int y) {
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
    }

    public record FoldInstruction(String axile, int amount) {

    }

    public Set<Point> getPoints() {
        return this.points;
    }

}
