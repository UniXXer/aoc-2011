package rocks.unixxer.day13;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Origami {

    List<Point> points = new ArrayList<>();
    
    public void parseLine(String s) {
        if(s.startsWith("fold along ")) {

        } 
    }

    public record Point(int x, int y) {

    }
}
