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

    //evaluate helper methods
    protected final boolean ownChecker(char check) { return ownChec == check; }
    protected final boolean oppChecker(char check) { return oppChec == check; }

    protected final boolean ownKing(char check) { return ownKing == check; }
    protected final boolean oppKing(char check) { return oppKing == check; }

    @Override
    public abstract int evaluateBoard (char[][] board);

}