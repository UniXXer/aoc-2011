package rocks.unixxer.day4;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BingoGame {

    private String randomNumbers;
    private List<BingoBoard> boards = new ArrayList<>();

    public void parseBoard(String line) {
        if(line.isEmpty()) {
            boards.add(new BingoBoard());
        } else {
            BingoBoard currentBoard = boards.get(boards.size() - 1);
            currentBoard.addLine(line);
        }
    }

    public int play() {
        String[] numbers = randomNumbers.split(",");
            
        for(String number : numbers) {
            int i = Integer.parseInt(number);

            for(BingoBoard b : boards) {
                b.mark(i);

                if(b.won()) {
                    return b.calculateUnmarked() * i;
                }
            }
        }

        return -1;
    }
    
    public void setRandomNumbers(String numbers) {
        this.randomNumbers = numbers;
    }
    
    public List<BingoBoard> getBoards() {
        return boards;
    }
}
