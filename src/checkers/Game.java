package checkers;
import java.util.ArrayList;
import java.util.List;
import static checkers.CheckersConstants.*;

public class Game {

    private static boolean changeColor = false; //not sure
    private Board b;
    private int BLACKdepth, WHITEdepth;
    private boolean display = false;
    private Evaluator blackPlayer, whitePlayer;

    public Game(Board newBoard, int newBLACKdepth, int newWHITEdepth, boolean newDisplay, Evaluator newBlackPlayer, Evaluator newWhitePlayer) {
        b = newBoard;
        display = newDisplay;
        blackPlayer = newBlackPlayer;
        whitePlayer = newWhitePlayer;

        changeColor = true; //to control who can set color
        blackPlayer.setColor(BLACK); //could have issues with threading
        whitePlayer.setColor(WHITE);
        changeColor = false;

        if (newBLACKdepth > 0) BLACKdepth = newBLACKdepth;
        else BLACKdepth = 6;
        if (newWHITEdepth > 0) WHITEdepth = newWHITEdepth;
        else WHITEdepth = 6;
    }

    public static boolean changeColor() { return changeColor; }

    public int invokeEvaluator(int color) {
        Evaluator invoking = color == BLACK
            ? blackPlayer
            : whitePlayer;
        return invoking.evaluateBoard(b.BoardCopy(color));
    }

    public void	compMove(int turn) {
        Move m;
        if (turn == BLACK) {
            if (display) {
                System.out.println(blackPlayer.getName() + "(Black) move - thinking");
            }
            m = minmax(turn, BLACKdepth, turn);
        }
        else {
            if (display) {
                System.out.println(whitePlayer.getName() + "(White) move - thinking");
            }
            m = minmax(turn, WHITEdepth, turn);
        }
        if (display) System.out.println(m);
        b.makeMove(m);		// make move
    }

    public Move minmax(int whoseMove, int level, int turn) {
        List<Move> possibleMoves;
        List<Integer> scores = new ArrayList<>();
        Move chosenMove;
        int best=Integer.MIN_VALUE, current=Integer.MIN_VALUE, worst=Integer.MAX_VALUE;

        // get moves for all checkers of player turn
        possibleMoves=b.findMoves(turn);
        chosenMove=possibleMoves.get(0);
        for (Move m : possibleMoves) {
            b.makeMove(m);
            // recurse
            current = minmaxR(whoseMove, level-1, -1*turn);
            scores.add(current);
            if (whoseMove==turn) {	// maximize level
                if (current>best) {
                    best=current;
                    chosenMove=m;
                }
//				else if (current==best && Math.random()>.5) {
//					chosenMove=m;
//				}
            }
            else {		// minimize level
                if (current<worst) {
                    worst=current;
                    chosenMove=m;
                }
//				else if (current==worst && Math.random()>.5) {
//					chosenMove=m;
//				}
            }
            b.unmakeMove(m);
        }
        return chosenMove;
    }

    public int minmaxR(int whoseMove, int level, int turn) {
        List<Move> possibleMoves;
        List<Integer> scores = new ArrayList<>();
        //String blackTeam=blackPlayer.getName();
        //String whiteTeam=whitePlayer.getName();
        int best=Integer.MIN_VALUE, current=Integer.MIN_VALUE, worst=Integer.MAX_VALUE;

        if (b.endGame(turn)) {	// turn player lost
            if (whoseMove==turn) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }

//		if (level == 0) return b.evaluate(whoseMove, blackTeam, whiteTeam);

        if (level == 0) {
            if (whoseMove==BLACK && whoseMove==turn)
                return invokeEvaluator(BLACK);
            else if (whoseMove==BLACK && whoseMove!=turn)
                return invokeEvaluator(WHITE);
            else if (whoseMove==WHITE && whoseMove==turn)
                return invokeEvaluator(WHITE);
            else
                return invokeEvaluator(BLACK);
        }

        else {
            // get moves for all checkers of player turn
            possibleMoves=b.findMoves(turn);
            for (Move m : possibleMoves) {
                b.makeMove(m);
                current = minmaxR(whoseMove, level-1, -1*turn);
                scores.add(current);
                if (whoseMove==turn) {	// maximize level
                    if (current>best)  // || (current==best && Math.random()>.5)
                        best=current;
                }
                else {		// minimize level
                    if (current<worst)  //  || (current==worst && Math.random()>.5)
                        worst=current;
                }
                b.unmakeMove(m);
            }
        }

        if (whoseMove==turn) return best;
        else return worst;
    }
}