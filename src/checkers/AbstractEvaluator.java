package checkers;
import static checkers.CheckersConstants.*;

public abstract class AbstractEvaluator implements Evaluator {

    protected String name;
    protected int section;
    private char ownChec, oppChec;
    private char ownKing, oppKing;

    protected AbstractEvaluator() { //defaults to black
        this.ownChec = BCHEC;
        this.ownKing = BKING;
        this.oppChec = WCHEC;
        this.oppKing = WKING;
    }

    @Override
    public final String getName() { return name; }

    @Override
    public final int getSection() { return section; }

    @Override
    public final void setColor(int color) { //must be called before game starts
        if (color == BLACK) {
            this.ownChec = BCHEC;
            this.ownKing = BKING;
            this.oppChec = WCHEC;
            this.oppKing = WKING;
        } else {
            this.ownChec = WCHEC;
            this.ownKing = WKING;
            this.oppChec = BCHEC;
            this.oppKing = BKING;
        }
    }

    /*
     * evaluate helper methods
     */

    /**
     * checks if tile has a checker that is the same
     * color as the Evaluator's color
     * @param tile
     * @return {@code true} tile has a checker piece and is same color
     */
    protected final boolean ownChecker(char tile) { return ownChec == tile; }

    /**
     * checks if tile has a checker that is the not
     * the same color as the Evaluator's color
     * @param tile
     * @return {@code true} tile has a checker piece and is different color
     */
    protected final boolean oppChecker(char tile) { return oppChec == tile; }

    /**
     * checks if tile has a king piece that is the same
     * as the Evaluator's color
     * @param tile
     * @return {@code true} tile has a king piece and is the same color
     */
    protected final boolean ownKing(char tile) { return ownKing == tile; }

    /**
     * checks if tile has a king piece that is not the 
     * same as the Evaluator's color
     * @param tile
     * @return {@code true} tile has a king piece and is different color
     */
    protected final boolean oppKing(char tile) { return oppKing == tile; }


    @Override
    public abstract int evaluateBoard (char[][] board);

}