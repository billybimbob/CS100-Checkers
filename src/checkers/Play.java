package checkers;
import static checkers.CheckersConstants.BLACK;
import static checkers.CheckersConstants.WHITE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Play {
    
    public static String [] classTeams = {"RandomA", "RandomB"}; // List your class names here
    public static List<String> teams = new ArrayList<>(Arrays.asList(classTeams));
    
    public static void main(String[] args) {
        boolean display = false;  // display the board after every move
        for (String arg: args)
            if (arg.equals("-v")) {
                display = true;
                break;
            }

        try {
            String t1=teams.get(0);
            String t2=teams.get(1);

            Evaluator[] evals = getEvaluators(t1, t2);
            Evaluator t1eval = evals[0], t2eval = evals[1];
            
            playGames(display, t1eval, t2eval);
               
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }


    static Evaluator[] getEvaluators (String team1, String team2) throws ReflectiveOperationException {
        Evaluator t1eval = (Evaluator) Class.forName("submissions." + team1)
            .getConstructor().newInstance();
        Evaluator t2eval = (Evaluator) Class.forName("submissions." + team2)
            .getConstructor().newInstance();

        return new Evaluator[] {t1eval, t2eval};

    }

    /**
     * @return first idx is eval1, second idx is eval2
     */
    public static int[] playGames (boolean display, Evaluator eval1, Evaluator eval2)
    {
        // change these first three ints if you want
        // depth must be an even number
        // totalMoves is how long it plays until a tie is declared
        // totalGames must be divisible by 4
        int depth=4, totalMoves=150, totalGames=4, win1=0, win2=0;
        String player1 = eval1.getName();
        String player2 = eval2.getName();
        
        int[] wins;
        int numRounds = totalGames/2;
        
        /* play the game */
        
        System.out.println(player1 + " White Moves First");
        wins = runGame(numRounds, totalMoves, depth, display, eval2, eval1);
        win1 += wins[0]; win2 += wins[1];
        
        System.out.println("\n\nHALFTIME: "+player1+"="+win1+"   "+player2+"="+win2);

        System.out.println("\n"+player2 + " White Moves First");
        wins = runGame(numRounds, totalMoves, depth, display, eval1, eval2);
        win1 += wins[1]; win2 += wins[0];

        System.out.println("\nFINAL SCORE: " + player1 + "=" + win1 + "   "+player2+"=" + win2);

        return new int[] {win1, win2};
    }

    /**
     * @return first idx is white player
     */
    public static int[] runGame(int numGames, int maxMoves, int depth, boolean display, Evaluator black, Evaluator white)
    {
        int win1 = 0, win2 = 0;
        String player1 = white.getName(); //player 1 goes first
        String player2 = black.getName();

        for (int i=0; i < numGames; i++) {
            Board b = new Board();
            Game g = new Game(b, depth, depth, display, black, white);
            int counter = 0;
            
            while(counter < maxMoves) {
                if (b.end_game(WHITE)) {
                    System.out.print(player1 + " Win  ");	
                    win1++;
                    break;
                }
                g.comp_move(WHITE);		
                counter++;
                if (display) System.out.println(b);		
                
                if (b.end_game(BLACK)) {
                    System.out.print(player2 + " Win  ");	
                    win2++;
                    break;
                }
                g.comp_move(BLACK);
                counter++;

                if (display){
                    //System.out.print(String.format("\033[2J"));
                    System.out.println(b);
                }	
            }

            if (counter==maxMoves) 
                System.out.print("Tie:" 
                    + player2 + "(" + b.checkerCount(BLACK) + ")" 
                    + player1 + "(" + b.checkerCount(WHITE) + ")  ");	
        }

        return new int[] {win1, win2};
    }
}