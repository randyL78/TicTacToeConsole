/**
 * AI
 * Methods for controlling computer player in tic tac toe game
 * @author Randy Layne
 */
public class AI {
  public static final int MAX_DIFFICULTY = 2;
  public static void takeTurn(Board board, int difficulty) {
    switch (difficulty) {
      case 0:
        easyTurn(board);
        break;
      case 1:
        mediumTurn(board);
        break;
      case 2:
        hardTurn(board);
        break;
      default:
        throw new IllegalArgumentException("Difficulty must be in the range of 0 - " + MAX_DIFFICULTY);
    }
  }

  private static void easyTurn(Board board) {
    // Make a turn based on a random index of available Squares
  }
  
  private static void mediumTurn(Board board) {
    // Make a turn based on a basic algorithm
  }

  private static void hardTurn(Board board) {
    // Make a turn based on a random index of available Squares
  }
}