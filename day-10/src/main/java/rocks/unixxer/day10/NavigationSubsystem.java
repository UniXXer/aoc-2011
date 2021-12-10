package rocks.unixxer.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NavigationSubsystem {

    public int syntaxCheck(List<String> lines) {
        List<Chunk> errors = new ArrayList<>();

        for(String line : lines) {
            Deque<Chunk> deque = new ArrayDeque<>();

            for(int i = 0; i < line.length(); i++) {
                String aChar = line.charAt(i) + "";

                if(Chunk.isOpenBracket(aChar)) {
                    deque.addFirst(Chunk.valueOfBracket(aChar));
                } else {
                    Chunk current = deque.removeFirst();

                    if(!current.close().equals(aChar)) {
                        errors.add(Chunk.valueOfBracket(aChar));
                        break;
                    }
                }
            }
        }

        return errors.stream().mapToInt((c) -> c.points()).sum();
    }

    public enum Chunk {
        ROUND("(", ")", 3),
        SQUARE("[", "]", 57),
        CURLY("{", "}", 1197),
        ANGLE("<", ">", 25137);

        static List<String> openBrackets = Arrays.asList(ROUND.open(), SQUARE.open(), CURLY.open(), ANGLE.open());

        private String open;
        private String close;
        private int points;

        Chunk(String open, String close, int points) {
            this.open = open;
            this.close = close;
            this.points = points;
        }

        public static boolean isOpenBracket(String b) {
            return openBrackets.contains(b);
        }

        public String open() {
            return open;
        }

        public String close() {
            return close;
        }

        public int points() {
            return points;
        }

        public static Chunk valueOfBracket(String b) {
            switch(b) {
                case "(" : 
                case ")" : return ROUND;
                case "[" :
                case "]" : return SQUARE;
                case "}" :
                case "{" : return CURLY;
                case ">" :
                case "<" : return ANGLE;
            }

            return null;
        }
    }
}
