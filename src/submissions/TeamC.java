package submissions;
import checkers.AbstractEvaluator;

// Charlie - kings more and sides on other side more
public class TeamC extends AbstractEvaluator {

    /**
     * have values
     * ownChecker: own regular checker pieces
     * ownKing:    own king checker pieces
     * oppChecker: opponent's regular checker pieces
     * oppKing:    opponent's king checker pieces
     */

    public TeamC() {
        super.name = "Charlie";
        super.section = 3;
	}
	
	@Override
	public int evaluateBoard(char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				char spot = position[i][j];
				if (super.oppChecker(spot)) value=value-3;
				if (super.oppKing(spot))    value=value-5;
				if (super.ownChecker(spot)) value=value+3;
				if (super.ownKing(spot))    value=value+5;
			}
		}
		value=value*100;
		for (i=1;i<=4;i++) {
			if (super.ownChecker(position[i][1])) value=value+1;
			if (super.ownChecker(position[i][8])) value=value+1;
		}
		for (i=5;i<=8;i++) {
			if (super.oppChecker(position[i][1])) value=value-1;
			if (super.oppChecker(position[i][8])) value=value-1;
		}
		for (i=1;i<=8;i++) {
			if (super.ownKing(position[i][1])) value=value+1;
			if (super.ownKing(position[i][8])) value=value+1;
			if (super.oppKing(position[i][1])) value=value-1;
			if (super.oppKing(position[i][8])) value=value-1;
		}
		return value*10+(int)(Math.random()*10);
	}

}