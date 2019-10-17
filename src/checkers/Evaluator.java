package checkers;

public interface Evaluator {
  
  public String getName();

  public int getSection();

  public int evaluateBlack(char [][] position);

  public int evaluateWhite(char [][] position);
}