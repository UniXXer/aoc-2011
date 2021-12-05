package rocks.unixxer.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Line {

    private Point from;
    private Point to;

    private List<Point> line = new ArrayList<>();

    private boolean diagonal = false;

    public Line(Point p1, Point p2) {
        from = p1;
        to = p2;

        if(from.getX() == to.getX()) {
            int y1 = Math.min(from.getY(), to.getY());
            int y2 = Math.max(from.getY(), to.getY());

            line = IntStream.range(y1, y2 + 1)
                .boxed()
                .map((y) -> new Point(from.getX(), y))
                .collect(Collectors.toList());
        } else if(from.getY() == to.getY()) {
            int x1 = Math.min(from.getX(), to.getX());
            int x2 = Math.max(from.getX(), to.getX());

            line = IntStream.range(x1, x2 + 1)
                .boxed()
                .map((x) -> new Point(x, from.getY()))
                .collect(Collectors.toList());
        } else {
            diagonal = true;

            line = new ArrayList<>();
            line.add(from);
            int steps = Math.max(from.getX(), to.getX()) - Math.min(from.getX(), to.getX());
            boolean xAdd = from.getX() < to.getX();
            boolean yAdd = from.getY() < to.getY();

            for(int i = 1; i < steps; i++) {
                line.add(new Point(xAdd ? from.getX() + i : from.getX() - i, yAdd ? from.getY() + i : from.getY() - i));    
            }

            line.add(to);
        }
    }    

    public List<Point> getLine() {
        return line;
    }

    public boolean isDiagonal() {
        return diagonal;
    }
}
