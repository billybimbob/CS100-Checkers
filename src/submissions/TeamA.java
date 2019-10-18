package submissions;
import checkers.AbstractEvaluator;

// Alpha - kings more
public class TeamA extends AbstractEvaluator {
	
	/**
     * have values
     * ownChecker: own regular checker pieces
     * ownKing:    own king checker pieces
     * oppChecker: opponent's regular checker pieces
     * oppKing:    opponent's king checker pieces
     */

    public TeamA() {
        super.name = "Alpha";
        super.section = 1;
	}

	@Override
	public int evaluateBoard(char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				char spot = position[i][j];
				if (super.ownChecker(spot)) value=value+3;
				if (super.ownKing(spot))    value=value+5;
				if (super.oppChecker(spot)) value=value-3;
				if (super.oppKing(spot))    value=value-5;
			}
		}
		return value*10+(int)(Math.random()*10);
	}

}