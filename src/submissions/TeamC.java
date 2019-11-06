package submissions;
import checkers.AbstractEvaluator;

// Charlie - kings more and regular pieces getting closer to kings are worth more
public class TeamC extends AbstractEvaluator {

    /*
     * have methods
     * ownChecker(char): own regular checker pieces
     * ownKing(char):    own king checker pieces
     * oppChecker(char): opponent's regular checker pieces
     * oppKing(char):    opponent's king checker pieces
     */

    public TeamC() {
        super.name = "Charlie";
        super.section = 3;
    }
    
    @Override
    public int evaluateBoard(char [][] board) { 
        int value=0, i, j;
        for (i=1;i<=8;i++) {
            for (j=1;j<=8;j++) {
                char spot = board[i][j];
                if (super.oppChecker(spot)) value -= 3;
                if (super.oppKing(spot))    value -= 5;
                if (super.ownChecker(spot)) value += 3;
                if (super.ownKing(spot))    value += 5;
            }
        }
        value=value*100;
        for (i=2;i<=5;i++) { //opp close
            for (j=1;j<=8;j++) {
                if (super.oppChecker(board[i][j])) value -= (8-i);
            }
        }
        for (i=4;i<=7;i++) { //own far
            for (j=1;j<=8;j++) {
                if (super.ownChecker(board[i][j])) value += i;
            }
        }
        return value*100+(int)(Math.random()*10);
    }

}