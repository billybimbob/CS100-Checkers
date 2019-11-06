package checkers;
import static checkers.CheckersConstants.*;

/**
 * Adds some helper methods for Evaluator;
 * <p> Each method takes in one spot on the board, and returns a 
 * boolean {@code true} based on if the info checking is valid for 
 * that specific tile; </p>
 * <p> Helper methods: </p>
 * <ul>
 * <li> {@code ownChecker(char tile)}: own regular checker pieces
 * <li> {@code ownKing(char tile)}:    own king checker pieces
 * <li> {@code oppChecker(char tile)}: opponent's regular checker pieces
 * <li> {@code oppKing(char tile)}:    opponent's king checker pieces
 * </ul>
 */
public abstract class AbstractEvaluator implements Evaluator {

    /**team name of Evaluator implementation*/
    protected String name;

    /**lab section of student(s) */
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
        if (Game.changeColor()) {
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
        } else {
            System.err.println("cannot change color");
        }
    }

    /*
     * evaluate helper methods
     */

    /**
     * checks if tile has a checker that is the same
     * color as the Evaluator's color
     * @param tile tile being checked
     * @return {@code true} tile has a checker piece and is same color
     */
    protected final boolean ownChecker(char tile) { return ownChec == tile; }

    /**
     * checks if tile has a checker that is the not
     * the same color as the Evaluator's color
     * @param tile tile being checked
     * @return {@code true} tile has a checker piece and is different color
     */
    protected final boolean oppChecker(char tile) { return oppChec == tile; }

    /**
     * checks if tile has a king piece that is the same
     * as the Evaluator's color
     * @param tile tile being checked
     * @return {@code true} tile has a king piece and is the same color
     */
    protected final boolean ownKing(char tile) { return ownKing == tile; }

    /**
     * checks if tile has a king piece that is not the
     * same as the Evaluator's color
     * @param tile tile being checked
     * @return {@code true} tile has a king piece and is different color
     */
    protected final boolean oppKing(char tile) { return oppKing == tile; }


    @Override
    public abstract int evaluateBoard (char[][] board);

}