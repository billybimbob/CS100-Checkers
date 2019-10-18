package submissions;
import checkers.AbstractEvaluator;

//Delta - kings more and regular pieces getting closer to kings are worth more
public class TeamD extends AbstractEvaluator {

    
    /**
     * have values
     * ownChecker: own regular checker pieces
     * ownKing:    own king checker pieces
     * oppChecker: opponent's regular checker pieces
     * oppKing:    opponent's king checker pieces
     */

    public TeamD() {
        super.name = "Delta";
        super.section = 7;
    }

	@Override
	public int evaluateBoard (char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				char spot = position[i][j];
				if (super.oppChecker(spot)) value -= 3;
				if (super.oppKing(spot))    value -= 5;
				if (super.ownChecker(spot)) value += 3;
				if (super.ownKing(spot))    value += 5;
			}
		}
		value=value*100;
		for (i=2;i<=5;i++) {
			for (j=1;j<=8;j++) {
				if (super.oppChecker(position[i][j])) value += (8-i);
			}
		}
		for (i=4;i<=7;i++) {
			for (j=1;j<=8;j++) {
				if (super.ownChecker(position[i][j])) value -= i;
			}
		}		
		return value*100+(int)(Math.random()*10);
	}

}