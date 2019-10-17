package submissions;
import checkers.CheckersConstants;
import checkers.Evaluator;

//Delta - kings more and regular pieces getting closer to kings are worth more
public class TeamD implements Evaluator {

	@Override
	public String getName() {
		return "Delta";
	}
	@Override
	public int getSection() {
		return 7;
	}

	@Override
	public int evaluateBlack(char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.WCHEC) value=value-3;
				if (position[i][j] == CheckersConstants.WKING) value=value-5;
				if (position[i][j] == CheckersConstants.BCHEC) value=value+3;
				if (position[i][j] == CheckersConstants.BKING) value=value+5;
			}
		}
		value=value*100;
		for (i=2;i<=5;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.BCHEC) value=value+(8-i);
			}
		}
		for (i=4;i<=7;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.WCHEC) value=value-i;
			}
		}		
		return value*100+(int)(Math.random()*10);
	}

  	@Override
  	public int evaluateWhite(char [][] position) { 
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.WCHEC) value=value+3;
				if (position[i][j] == CheckersConstants.WKING) value=value+5;
				if (position[i][j] == CheckersConstants.BCHEC) value=value-3;
				if (position[i][j] == CheckersConstants.BKING) value=value-5;
			}
		}
		value=value*100;
		for (i=2;i<=5;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.BCHEC) value=value-(8-i);
			}
		}
		for (i=4;i<=7;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.WCHEC) value=value+i;
			}
		}		
		return value*100+(int)(Math.random()*10);
	}
}