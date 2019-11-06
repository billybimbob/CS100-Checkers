package checkers;

import static checkers.CheckersConstants.BLACK;
import static checkers.CheckersConstants.WHITE;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            parseArgs(args);
            if (submissionTest == null)
                throw new RuntimeException("no submission file given");

            addSubmissions(); //always same submissions

            String t1 = submissionTest;
            String t2 = selectOpponent();

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
            throw new RuntimeException("Argument expected an integer");
        }
    }

    private static void parseArgs(String[] args) throws RuntimeException {
        try {
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
                        System.exit(1); // exit early
                        break;
                    case 'V':
                        display = true;
                        break;
                    default:
                        break;
                    }
            }
        }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Incorrect positional arg supplied");
        }
    }

    private static void printUsage() {
        System.out.println(
            "Usage: java -cp <classpath> checkers.Play [-hV] [-d <num>] -f <file>\n" + 
            "\t-h         Print this help message.\n" +
            "\t-V         Optional print out board after each move.\n" +
            "\t-d <num>   Optional number of depth to search for possible moves.\n" +
            "\t-f <file>  File submission testing, only include class name\n");
    }

    protected static void addSubmissions(String name) throws ReflectiveOperationException, IOException {

        InputStream reading = null; //should be closed with bufferedreader
        if (name == null) {
            reading = Play.class.getClassLoader().getResourceAsStream("default_submissions.txt"); 
        } else {
            reading = new FileInputStream(name);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(reading));) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split("\\s+").length == 1) //must be one word
                    teams.add(line); //line should have just file name
            }

        } catch (FileNotFoundException e) {
            
            if (name != null) {//try using .txt extension
                try {
                    addSubmissions(name + ".txt");
                } catch (FileNotFoundException f) {
                    addSubmissions(null);
                }
            } else {
                throw new IOException(e);
            }
        }
    }

    protected static void addSubmissions() throws ReflectiveOperationException, IOException {
        addSubmissions(null);
    }

    private static String selectOpponent() {
        String ret = null;
        while (ret == null) {
            System.out.print("Select the difficulty for your opponent"
                + "\n\tLevel ranges from 1-" + teams.size() + ": ");
            try {
                ret = teams.get( Integer.parseInt(keyboard.nextLine())-1 );
            } catch (RuntimeException e) {
                System.out.println("Plase enter a valid level");
            }

        }

        return ret;
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
        System.out.println("\n=============================");
        System.out.println(player1 + " vs. " + player2);
        System.out.println("=============================\n");

        System.out.println(player1 + "(White) Moves First");
        wins = runGame(numRounds, eval2, eval1);
        win1 += wins[0]; win2 += wins[1];

        System.out.println("\n\nHALFTIME: "+player1+"="+win1+"   "+player2+"="+win2);

        System.out.println("\n"+player2 + "(White) Moves First");
        wins = runGame(numRounds, eval1, eval2);
        win1 += wins[1]; win2 += wins[0];

        System.out.println("\n\nFINAL SCORE: " + player1 + "=" + win1 + "   "+player2+"=" + win2);

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
                if (b.endGame(WHITE)) {
                    System.out.print(player1 + " Win  ");
                    win1++;
                    break;
                }
                g.compMove(WHITE);
                counter++;
                if (display) printBoard(b);

                if (b.endGame(BLACK)) {
                    System.out.print(player2 + " Win  ");
                    win2++;
                    break;
                }
                g.compMove(BLACK);
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