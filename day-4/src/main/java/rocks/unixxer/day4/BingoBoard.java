package rocks.unixxer.day4;


public class BingoBoard {

    private BingoNumber[][] board = new BingoNumber[5][5];
    private int lines = 0;

    private int hits = 0;

    private boolean won = false;

    public void addLine(String line) {
        String[] split = line.split(" ");

        int position = 0;

        for(String s : split) {
            if (s != null && !s.isEmpty()) {
                board[lines][position] = new BingoNumber(Integer.parseInt(s), false);
                position++;
            }
        }

        lines++;
    }

    public void mark(int number) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(board[i][j].number() == number) {
                    board[i][j] = new BingoNumber(number, true);
                    hits++;
                }
            }
        }
    }

    public boolean won() {
        if(hits < 5) {
            return false;
        }

        line: for(int line = 0; line < 5; line++) {
            for(int column = 0; column < 5; column++) {
                if(board[line][column].marked() == false) {
                    continue line;
                }
            }
            won = true;
            return true;
        }

        column: for(int column = 0; column < 5; column++) {
            for(int line = 0; line < 5; line++) {
                if(board[line][column].marked() == false) {
                    continue column;
                }
            }
            won = true;
            return true;
        }

        return false;
    }

    public int calculateUnmarked() {
        int result = 0;

        for(int line = 0; line < 5; line++) {
            for(int column = 0; column < 5; column++) {
                if(board[line][column].marked() == false) {
                    result += board[line][column].number();
                }
            }
        }

        return result;
    }

    public boolean isWon() {
        return won;
    }
}
