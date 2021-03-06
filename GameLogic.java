/**
 * GameLogic
 * Class for testing board to check for wins 
 * @author Randy Layne
 */
public class GameLogic {
  /** checks if player won */
  public static boolean checkWin(Board board, int currentPlayer) {
    boolean won = false;
    
    won = checkRows(board, currentPlayer) || won;
    won = checkColumns(board, currentPlayer) || won;
    won = checkDecreaseDiagonal(board, currentPlayer) || won;
    won = checkIncreaseDiagonal(board, currentPlayer) || won;

    return won;
  }

  /** checks if player won with three in a row*/
  private static boolean checkRows(Board board, int currentPlayer) {
    boolean won = false;
    // loop through each row and check for winner
    for  (int i = 0; i < 3; i++) {
      won = checkRow(board, currentPlayer, i) || won; 
    }
    return won;
  }

  /** check a single row for winner */
  private static boolean checkRow(Board board, int currentPlayer, int row) {
    for (int i = 0; i < 3; i++) {
      if (board.getOwner(row, i) != currentPlayer)
        return false;
    }
    return true;
  }

  /** checks if player won with three in a row */
  private static boolean checkColumns(Board board, int currentPlayer) {
    boolean won = false;
    // loop through each column and check for winner
    for  (int i = 0; i < 3; i++) {
      won = checkColumn(board, currentPlayer, i) || won; 
    }
    return won;
  }
  
  /** check a single column for winner */
  private static boolean checkColumn(Board board, int currentPlayer, int col) {
    for (int i = 0; i < 3; i++) {
      if (board.getOwner(i, col) != currentPlayer)
        return false;
    }
    return true;
  }

  /** check diagonal with negative slope for win */
  private static boolean checkDecreaseDiagonal(Board board, int currentPlayer) {
    // loop through each square on the diagonal
    // if any square doesn't belong to the current player, return false
    for (int i = 0; i < 3; i++) {
      if (board.getOwner(i, i) != currentPlayer)
        return false;
    }
    return true;
  }

  /** check diagonal with positive slope for win */
  private static boolean checkIncreaseDiagonal(Board board, int currentPlayer) {
    // loop through each square on the diagonal
    // if any square doesn't belong to the current player, return false
    for (int i = 0; i < 3; i++) {
      if (board.getOwner(i, 2 - i) != currentPlayer)
        return false;
    }
    return true;
  }

  /** check for a tie */
  public static boolean checkTie(Board board) {
    // if board is full, return true;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // if any square is unowned, there is not a tie so return false
        if (board.isEmptyAt(i, j))
          return false;
      }
    }
    return true;
  }

}