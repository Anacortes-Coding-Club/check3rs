package game;

public class Stone {
    private boolean isKing;
    private final boolean isRed;

    /**
     * Creates a pawn as black or red, not as a king.
     * @param isRed     sets color as red if true, black if false;
     */
    public Stone(boolean isRed) {
        this.isKing = false;
        this.isRed = isRed;
    }

    /**
     * Creates a pawn or king as black or red.
     * @param isRed     sets color as red if true, black if false;
     * @param isKing    sets type to king.
     */
    public Stone(boolean isRed, boolean isKing) {
        this.isKing = isKing;
        this.isRed = isRed;
    }

    public boolean isKing() {
        return isKing;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setKing() {
        isKing = true;
    }

    public String toString() {
        String out = "(";
        if(isRed) {
            out += "red";
        } else {
            out += "black";
        }

        if(isKing) {
            out += " king";
        } else {
            out += " pawn";
        }
        return out;
    }
}
