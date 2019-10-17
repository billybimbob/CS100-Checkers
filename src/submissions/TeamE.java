package submissions;
import checkers.CheckersConstants;
import checkers.AbstractEvaluator;

//Epsilon - kings more and regular pieces getting closer to kings are worth more
public class TeamE extends AbstractEvaluator {

    /**
     * have values
     * ownChec: own regular checker pieces
     * ownKing: own king checker pieces
     * oppChec: opponent's regular checker pieces
     * oppKing: opponent's king checker pieces
     */

    public TeamE() {
        super.name = "Epsilon";
        super.section = 8;
    }

	@Override
	public int evaluateBoard (char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == super.oppChec) value -= 3;
				if (position[i][j] == super.oppKing) value -= 5;
				if (position[i][j] == super.ownChec) value += 3;
				if (position[i][j] == super.ownKing) value += 5;
			}
		}
		value=value*100;
		for (i=2;i<=5;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == super.oppChec) value += (8-i);
			}
		}
		for (i=4;i<=7;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == super.ownChec) value -= i;
			}
		}		
		return value*100+(int)(Math.random()*10);
	}

}