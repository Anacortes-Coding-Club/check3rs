package game;

import java.awt.Color;

import game.VisualHelper.*;

public class Check3rs {
    Stone[][] gameBoard = new Stone[8][8];
    BotInterface player1, player2;
    int wins; //0 if equal wins, +1 for each player1 win, 
    VisualInterface rend = new Renderer();

    public Check3rs(BotInterface player1, BotInterface player2) {
        for(int r = 0; r < 8; r ++) {
            for(int c = (r+1) % 2; c < 8; c += 2) {
                if(r < 3) gameBoard[r][c] = new Stone(false);
                if(r >= 5) gameBoard[r][c] = new Stone(true);
            }
        }

        // player1.setColor(true);
        // player2.setColor(false);
        updateVisuals();
    }

    public void runGames() {
        //will run two games, with each round having a different player start.
    }

    public void runGame() {
        rend.updateVisuals(gameBoard);
    }

    public void updateVisuals() {
        rend.updateVisuals(gameBoard);
    }

    public void printBoard() {
        System.out.print("[");
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print(gameBoard[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("]");
    }

    public static void main(String[] args)
    {
        Check3rs match = new Check3rs(null, null);
        match.updateVisuals();
        match.printBoard();
    }
}
