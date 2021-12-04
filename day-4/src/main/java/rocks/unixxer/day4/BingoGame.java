package rocks.unixxer.day4;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BingoGame {

    private String randomNumbers;
    private List<BingoBoard> boards = new ArrayList<>();
    private int result_puzzle1 = -1;
    private int result_puzzle2 = -1;
    private int boardsWon = 0;

    public void parseBoard(String line) {
        if(line.isEmpty()) {
            boards.add(new BingoBoard());
        } else {
            BingoBoard currentBoard = boards.get(boards.size() - 1);
            currentBoard.addLine(line);
        }
    }

    public void play() {
        String[] numbers = randomNumbers.split(",");
            
        for(String number : numbers) {
            int i = Integer.parseInt(number);

            for(BingoBoard b : boards) {
                if(!b.isWon()) {
                    b.mark(i);

                    if(b.won()) {
                        if(result_puzzle1 == -1) {
                            result_puzzle1 = b.calculateUnmarked() * i;
                        }
                            boardsWon++;

                        if(boardsWon == boards.size()) {
                            result_puzzle2  = b.calculateUnmarked() * i;
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public void setRandomNumbers(String numbers) {
        this.randomNumbers = numbers;
    }
    
    public List<BingoBoard> getBoards() {
        return boards;
    }

    public int getResultPuzzle1() {
        return result_puzzle1;
    }

    public int getResultPuzzle2() {
        return result_puzzle2;
    }
}
