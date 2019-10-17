package submissions;
import checkers.*;
// Bravo - just count
public class TeamB implements Evaluator {
	@Override
	public String getName() {
		return "Bravo";
	}

	@Override
	public int evaluateBlack(char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.WCHEC) value=value-1;
				if (position[i][j] == CheckersConstants.WKING) value=value-1;
				if (position[i][j] == CheckersConstants.BCHEC) value=value+1;
				if (position[i][j] == CheckersConstants.BKING) value=value+1;
			}
		}
		return value*10+(int)(Math.random()*10);
	}

  	@Override
  	public int evaluateWhite(char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.WCHEC) value=value+1;
				if (position[i][j] == CheckersConstants.WKING) value=value+1;
				if (position[i][j] == CheckersConstants.BCHEC) value=value-1;
				if (position[i][j] == CheckersConstants.BKING) value=value-1;
			}
		}				
		return value*10+(int)(Math.random()*10);
	}
}