package submissions;
import checkers.CheckersConstants;
import checkers.Evaluator;
// Alpha - kings more
public class TeamA implements Evaluator {
	
	@Override
	public String getName() {
		return "Alpha";
	}
	@Override
	public int getSection() {
		return 3;
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
		return value*10+(int)(Math.random()*10);
	}

	@Override
	public int evaluateBlack(char [][] position){
		int value=0, i, j;
		for (i=1;i<=8;i++) {
			for (j=1;j<=8;j++) {
				if (position[i][j] == CheckersConstants.WCHEC) value=value-3;
				if (position[i][j] == CheckersConstants.WKING) value=value-5;
				if (position[i][j] == CheckersConstants.BCHEC) value=value+3;
				if (position[i][j] == CheckersConstants.BKING) value=value+5;
			}
		}
		return value*10+(int)(Math.random()*10);
	}
}