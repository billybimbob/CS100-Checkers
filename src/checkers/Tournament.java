package checkers;

import java.io.IOException;

/**
 * In order to run tournament, 2 requirements are needed:
 * <ol>
 * <li> submission class files located in {@code submissions} package
 * <li> have a submissions.txt file that contains all the submission class
 * file names (without any file extensions or directory path)
 * </ol>
 */
public class Tournament extends Play {
    
    public static int[][] results;
    private static String submissions = null;
    
    public static void main(String[] args) {
        try {
            parseArgs(args);
            addSubmissions(submissions);
            if (submissions == null)
                System.out.println("No submission file given, using default");

            int win1, win2;
            int[] wins;

            results = new int[teams.size()][teams.size()];

            for (int ii=0; ii < teams.size(); ii++) {
                for (int jj=ii+1; jj < teams.size(); jj++) {
                    String t1=teams.get(ii);
                    String t2=teams.get(jj);
                    Evaluator[] evals = Play.getEvaluators(t1, t2);
                    Evaluator t1eval = evals[0], t2eval = evals[1];

                    wins = Play.playGames(t1eval, t2eval);
                    win1 = wins[0]; win2 = wins[1];

                    results[ii][jj] = win1;
                    results[jj][ii] = win2;
                }
            }
            
            for (int ii=0; ii<teams.size(); ii++) {
                int rowTotal=0;
                String t1=teams.get(ii);
                for (int jj=0; jj<teams.size(); jj++) {
                    rowTotal=rowTotal+results[ii][jj];
                    System.out.print(results[ii][jj]+"\t");
                }
                System.out.print(t1+" totalWins="+rowTotal+"\n");
            }

            
        } catch (RuntimeException e) {
            e.printStackTrace();
            printUsage();
        } catch (IOException e) {
            System.err.println("Issue reading flie");
            e.printStackTrace();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    protected static void parseArgs(String[] args) throws RuntimeException {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.charAt(0) == '-') {
                for (char letter: arg.toCharArray()) //expect order of args match letter order
                    switch(letter) {
                        case 'd':
                            depth = checkInt(x -> x%2==0, args[++i]);
                            break;
                        case 's':
                            submissions = args[++i]; //skips next arg
                            break;
                        case 'g':
                            totalGames = checkInt(x -> x%2==0, args[++i]);
                            break;
                        case 'h':
                            printUsage();
                            break;
                        case 'm':
                            totalMoves = checkInt(x -> x>0, args[++i]);
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
            "Usage: java checkers.Play [-hV] [-d <num>] [-g <num>] [-m <num>] -s <file>\n" + 
            "-h         Print this help message.\n" +
            "-V         Optional print out board after each move.\n" +
            "-d <num>   Optional number of depth to search for possible moves.\n" +
            "-g <num>   Optional number of games played for each match.\n" +
            "-m <num>   Optional number of moves until a tie is determined.\n" +
            "-f <file>  Text file of all submissions\n");
    }

}