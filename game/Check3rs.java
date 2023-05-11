package game;

public class Check3rs {
    Stone[][] gameBoard = new Stone[8][8];
    BotInterface player1, player2;
    int wins; //0 if equal wins, +1 for each player1 win, 

    public Check3rs(BotInterface player1, BotInterface player2) {
        for(int r = 0; r < 8; r ++) {
            for(int c = (r+1) % 2; c < 8; c += 2) {
                if(r < 3) gameBoard[r][c] = new Stone(false);
                if(r >= 5) gameBoard[r][c] = new Stone(true);
            }
        }
        player1.setColor(true);
        player2.setColor(false);
    }

    public void runGames() {
        //will run two games, with each round having a different player start.
    }

    public void runGame() {

    }

    public static void main(String[] args) {
        Check3rs match = new Check3rs(null, null);
    }
}
