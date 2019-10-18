package submissions;
import checkers.AbstractEvaluator;

// Bravo - just count
public class TeamB extends AbstractEvaluator {
	/**
     * have values
     * ownChecker: own regular checker pieces
     * ownKing:    own king checker pieces
     * oppChecker: opponent's regular checker pieces
     * oppKing:    opponent's king checker pieces
     */

    public TeamB() {
        super.name = "Bravo";
        super.section = 3;
	}

	@Override
	public int evaluateBoard(char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				char spot = position[i][j];
				if (super.oppChecker(spot)) value=value-1;
				if (super.oppKing(spot))    value=value-1;
				if (super.ownChecker(spot)) value=value+1;
				if (super.ownKing(spot))    value=value+1;
			}
		}
		return value*10+(int)(Math.random()*10);
	}

}