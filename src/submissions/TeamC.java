package submissions;
import checkers.*;
// Charlie - kings more and sides on other side more
public class TeamC implements Evaluator {
	@Override
	public String getName() {
		return "Charlie";
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
		for (i=1;i<=4;i++) {
			if (position[i][1] == CheckersConstants.BCHEC) value=value+1;
			if (position[i][8] == CheckersConstants.BCHEC) value=value+1;
		}
		for (i=5;i<=8;i++) {
			if (position[i][1] == CheckersConstants.WCHEC) value=value-1;
			if (position[i][8] == CheckersConstants.WCHEC) value=value-1;
		}
		for (i=1;i<=8;i++) {
			if (position[i][1] == CheckersConstants.BKING) value=value+1;
			if (position[i][8] == CheckersConstants.BKING) value=value+1;
			if (position[i][1] == CheckersConstants.WKING) value=value-1;
			if (position[i][8] == CheckersConstants.WKING) value=value-1;
		}
		return value*10+(int)(Math.random()*10);
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
		for (i=1;i<=4;i++) {
			if (position[i][1] == CheckersConstants.BCHEC) value=value-1;
			if (position[i][8] == CheckersConstants.BCHEC) value=value-1;
		}
		for (i=5;i<=8;i++) {
			if (position[i][1] == CheckersConstants.WCHEC) value=value+1;
			if (position[i][8] == CheckersConstants.WCHEC) value=value+1;
		}
		for (i=1;i<=8;i++) {
			if (position[i][1] == CheckersConstants.BKING) value=value-1;
			if (position[i][8] == CheckersConstants.BKING) value=value-1;
			if (position[i][1] == CheckersConstants.WKING) value=value+1;
			if (position[i][8] == CheckersConstants.WKING) value=value+1;
		}
		return value*10+(int)(Math.random()*10);
	}
}