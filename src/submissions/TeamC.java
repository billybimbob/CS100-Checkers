package submissions;
import checkers.AbstractEvaluator;

/** Charlie - kings more and sides on other side more */
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
    public int evaluateBoard (char [][] board) {
        int value=0, i, j;
        for (i=1;i<=8;i++) {
            for (j=1;j<=8;j++) {
                char spot = board[i][j];
                if (super.oppChecker(spot)) value=value-3;
                if (super.oppKing(spot))    value=value-5;
                if (super.ownChecker(spot)) value=value+3;
                if (super.ownKing(spot))    value=value+5;
            }
        }
        value=value*100;
        for (i=1;i<=4;i++) { //opp close to you
            if (super.oppChecker(board[i][1])) value-=1;
            if (super.oppChecker(board[i][8])) value-=1;
        }
        for (i=5;i<=8;i++) { //own farther
            if (super.ownChecker(board[i][1])) value+=1;
            if (super.ownChecker(board[i][8])) value+=1;
        }
        for (i=1;i<=8;i++) { //on sides
            if (super.ownKing(board[i][1])) value=value+1;
            if (super.ownKing(board[i][8])) value=value+1;
            if (super.oppKing(board[i][1])) value=value-1;
            if (super.oppKing(board[i][8])) value=value-1;
        }
        return value*10+(int)(Math.random()*10);
    }

}