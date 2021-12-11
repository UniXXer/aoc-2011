package rocks.unixxer.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NavigationSubsystem {

    List<Chunk> errors;
    List<Long> incompleteScores;

    public int checkScore() {
        return errors.stream().mapToInt((c) -> c.points()).sum();
    }

    public long autoCompleteScore() {
        List<Long> sortedScores = incompleteScores.stream().sorted().collect(Collectors.toList());
        
        return sortedScores.get(sortedScores.size() / 2);
    }

    public void syntaxCheck(List<String> lines) {
        errors = new ArrayList<>();
        incompleteScores = new ArrayList<>();

        outer: for(String line : lines) {
            Deque<Chunk> deque = new ArrayDeque<>();

            for(int i = 0; i < line.length(); i++) {
                String aChar = line.charAt(i) + "";

                if(Chunk.isOpenBracket(aChar)) {
                    deque.addFirst(Chunk.valueOfBracket(aChar));
                } else {
                    Chunk current = deque.removeFirst();

                    if(!current.close().equals(aChar)) {
                        errors.add(Chunk.valueOfBracket(aChar));
                        continue outer;
                    }
                }
            }

            // Incomplete
            long score = 0;
            while(deque.size() > 0) {            
                Chunk nextChunk = deque.removeFirst();     
                score = score * 5 + nextChunk.acPoints();   
            }
            incompleteScores.add(score);
        }
    }

    public enum Chunk {
        ROUND("(", ")", 3, 1),
        SQUARE("[", "]", 57, 2),
        CURLY("{", "}", 1197, 3),
        ANGLE("<", ">", 25137, 4);

        static List<String> openBrackets = Arrays.asList(ROUND.open(), SQUARE.open(), CURLY.open(), ANGLE.open());

        private String open;
        private String close;
        private int points;
        private int acPoints;

        Chunk(String open, String close, int points, int ac) {
            this.open = open;
            this.close = close;
            this.points = points;
            this.acPoints = ac;
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

        public int acPoints() {
            return acPoints;
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
