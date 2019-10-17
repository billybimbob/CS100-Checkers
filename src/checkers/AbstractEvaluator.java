package checkers;

public abstract class AbstractEvaluator implements Evaluator {

    protected String name;
    protected int section;
    protected char ownChec, oppChec;
    protected char ownKing, oppKing;

    protected AbstractEvaluator() { }

    @Override
    public final String getName() { return name; }

    @Override
    public final int getSection() { return section; }

    @Override
    public final int evaluateBlack (char[][] position) {
        ownChec = CheckersConstants.BCHEC;
        ownKing = CheckersConstants.BKING;
        oppChec = CheckersConstants.WCHEC;
        oppKing = CheckersConstants.WKING;
        return evaluateBoard(position);
    }

    @Override 
    public final int evaluateWhite (char[][] position) {
        ownChec = CheckersConstants.WCHEC;
        ownKing = CheckersConstants.WKING;
        oppChec = CheckersConstants.BCHEC;
        oppKing = CheckersConstants.BKING;
        return evaluateBoard(position);
    }

    protected abstract int evaluateBoard (char[][] board);

}