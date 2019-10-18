package checkers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tournament {
	public static String [] classTeams = {"TeamA","TeamB","TeamC","TeamD"};
	public static List<String> teams = new ArrayList<>(Arrays.asList(classTeams));
	public static int[][] results = new int[teams.size()][teams.size()];
	
	public static void main(String[] args) {
		boolean display=false;
		for (String arg: args)
			if (arg.equals("-v")) {
				display = true;
				break;
			}

		try {
			int win1, win2;
			int[] wins;

			for (int ii=0; ii<teams.size(); ii++) {
				for (int jj=ii+1; jj<teams.size(); jj++) {
					String t1=teams.get(ii);
					String t2=teams.get(jj);
					Evaluator[] evals = Play.getEvaluators(t1, t2);
					Evaluator t1eval = evals[0], t2eval = evals[1];

					wins = Play.playGames(display, t1eval, t2eval);
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
		} catch (ReflectiveOperationException e) {
	        e.printStackTrace();
		}
	}
}