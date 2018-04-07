/**
 * AI
 * Methods for controlling computer player in tic tac toe game
 * @author Randy Layne
 */
public class AI {
  public static final int MAX_DIFFICULTY = 2;
  public static void takeTurn(Board board, Players players) {
    // Get the difficulty level of current computer player
    int difficulty = ((ComputerPlayer) (players.players[players.currentPlayer()])).getDifficulty();

    switch (difficulty) {
      case 0:
        easyTurn(board, players.currentPlayer());
        break;
      case 1:
        mediumTurn(board, players.currentPlayer());
        break;
      case 2:
        hardTurn(board, players.currentPlayer());
        break;
      default:
        throw new IllegalArgumentException("Difficulty must be in the range of 0 - " + MAX_DIFFICULTY);
    }
  }

  private static void easyTurn(Board board, int player) {
    // loop until random square is not taken 
    boolean taken = true;
    do {
      // get random row and column number
      int row = (int) (Math.random() * 3);
      int col = (int) (Math.random() * 3);
      if (board.isEmptyAt(row, col)) {
        board.setOwner(player, row, col);
        taken = false;
      }

    } while (taken);

  }
  
  private static void mediumTurn(Board board, int player) {
    // Make a turn based on a basic algorithm
  }

  private static void hardTurn(Board board, int player) {
    // Make a turn based on a more strategic algorithm
  }
}