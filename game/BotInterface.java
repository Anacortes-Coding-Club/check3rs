package game;

public interface BotInterface {

    /**
     * Sets the piece color of bot is playing. Method will be run at the start of each game.
     * @param isRed     playing red if true, playing black if false.
     */
    public void setColor(boolean isRed);

    /**
     * 
     * @param gameBoard
     * @return              Return type will most likely change. Start with reading the board in the meantime.
     */
    public Stone[][] move(Stone[][] gameBoard);
}
