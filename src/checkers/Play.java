package checkers;

import static checkers.CheckersConstants.BLACK;
import static checkers.CheckersConstants.WHITE;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.IntPredicate;

/**
 * Inherited static vars:
 * <ul>
 * <li> teams: all possible evaluators
 * <li> display: prints out board state after each move
 * <li> depth: must be an even number
 * <li> totalGames: must be an even number
 * <li> totalMoves: how long it plays until a tie is declared
 * </ul>
 */
public class Play {
    
    protected static List<String> teams = new ArrayList<>();
    protected static boolean display = false;  
    protected static int depth=6, totalGames=4, totalMoves=150;

    private static String submissionTest = null;

    public static void main(String[] args) {
        try {
            parseArgs(args);
            addSubmissions();
            if (submissionTest == null)
                throw new RuntimeException("no submission file given");

            String t1 = submissionTest;
            String t2 = teams.get( (int)(Math.random()*teams.size()) );

            Evaluator[] evals = getEvaluators(t1, t2);
            Evaluator t1eval = evals[0], t2eval = evals[1];

            playGames(t1eval, t2eval);

            
        } catch (RuntimeException e) {
            e.printStackTrace();
            printUsage();
        } catch (IOException e) {
            System.err.println("Issue reading flie");
            e.printStackTrace();
        } catch (ReflectiveOperationException e) {
            System.err.println("Supplied file not found");
            e.printStackTrace();
        }
    }

    protected static int checkInt(IntPredicate check, String checking) throws RuntimeException {
        try {
            int value = Integer.parseInt(checking);
            if (check.test(value))
                return value;
            else
                throw new RuntimeException("Invalid arguements");

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void parseArgs(String[] args) throws RuntimeException {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.charAt(0) == '-') {
                for (char letter: arg.toCharArray())
                    switch(letter) {
                        case 'd':
                            depth = checkInt(x -> x%2==0, args[++i]);
                            break;
                        case 'f':
                            submissionTest = args[++i]; //skips next arg
                            break;
                        case 'h':
                            printUsage();
                            break;
                        case 'V':
                            display = true;
                            break;
                        default:
                            break;
                    }
            }
        }
    }

    private static void printUsage() {
        System.out.println(
            "Usage: java checkers.Play [-hV] [-d <num>] -f <file>\n" + 
            "-h         Print this help message.\n" +
            "-V         Optional print out board after each move.\n" +
            "-d <num>   Optional number of depth to search for possible moves.\n" +
            "-f <file>  File submission testing\n");
    }

    protected static void addSubmissions(String name) throws IOException {
        String fileName = name==null
            ? "default_submissions.txt"
            : name;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                teams.add(line); //line should have just file name
            }

        } catch (FileNotFoundException e) {

            try {
                if (name!= null) //try using .txt extension
                    addSubmissions(name + ".txt");
            } catch (FileNotFoundException f) {
                addSubmissions(null);
            }
        }
    }

    protected static void addSubmissions() throws IOException {
        addSubmissions(null);
    }


    protected static Evaluator[] getEvaluators (String team1, String team2) throws ReflectiveOperationException {
        Evaluator t1eval = (Evaluator) Class.forName("submissions." + team1)
            .getConstructor().newInstance();
        Evaluator t2eval = (Evaluator) Class.forName("submissions." + team2)
            .getConstructor().newInstance();

        return new Evaluator[] {t1eval, t2eval};

    }

    /**
     * @return first idx is eval1 wins, second idx is eval2
     */
    protected static int[] playGames (Evaluator eval1, Evaluator eval2)
    {
        int win1=0, win2=0;
        String player1 = eval1.getName();
        String player2 = eval2.getName();

        int[] wins;
        int numRounds = totalGames/2;

        /* play the game */
        System.out.println("=============================");
        System.out.println(player1 + " vs. " + player2);
        System.out.println("=============================");

        System.out.println(player1 + "(White) Moves First");
        wins = runGame(numRounds, eval2, eval1);
        win1 += wins[0]; win2 += wins[1];

        System.out.println("\n\nHALFTIME: "+player1+"="+win1+"   "+player2+"="+win2);

        System.out.println("\n"+player2 + "(White) Moves First");
        wins = runGame(numRounds, eval1, eval2);
        win1 += wins[1]; win2 += wins[0];

        System.out.println("\nFINAL SCORE: " + player1 + "=" + win1 + "   "+player2+"=" + win2);

        return new int[] {win1, win2};
    }



    /**
     * @return first idx is white player
     */
    protected static int[] runGame(int numGames, Evaluator black, Evaluator white)
    {
        int win1 = 0, win2 = 0;
        String player1 = white.getName(); //player 1 goes first
        String player2 = black.getName();

        for (int i=0; i < numGames; i++) {
            Board b = new Board();
            Game g = new Game(b, depth, depth, display, black, white);
            int counter = 0;

            while(counter < totalMoves) {
                if (b.end_game(WHITE)) {
                    System.out.print(player1 + " Win  ");
                    win1++;
                    break;
                }
                g.comp_move(WHITE);
                counter++;
                if (display) printBoard(b);

                if (b.end_game(BLACK)) {
                    System.out.print(player2 + " Win  ");
                    win2++;
                    break;
                }
                g.comp_move(BLACK);
                counter++;

                if (display) printBoard(b);
            }

            if (counter==totalMoves)
                System.out.print("Tie:"
                    + player2 + "(" + b.checkerCount(BLACK) + ")"
                    + player1 + "(" + b.checkerCount(WHITE) + ")  ");
        }

        return new int[] {win1, win2};
    }



    private static void printBoard(Board board) {
        try {
            //System.out.print(String.format("\033[2J"));
            System.out.println(board);
            TimeUnit.SECONDS.sleep(1);
            
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
            e.printStackTrace();
        }
    } 
}