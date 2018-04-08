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

    // get current player
    int player = players.currentPlayer();

    // get opposite player
    int opponent = (player == 0) ? 1 : 0;

  // Basic Strategy *****************************************
    // 1. always go for middle on first move. (medium/hard)
    // 2. check if there is a win on this move, if so make winning move (hard)
    // 3. check if opponent can win on this move, if so block win (medium/hard)
    // 4. randomly choose from remainder of board (easy/medium/hard)

    // Check if middle square is owned, if not take it (medium/hard)
    if (checkMiddle(board) && difficulty > 0) {
      board.setOwner(player, 1, 1);
    // if player can win on this move, go for win (hard)
    } else if (canWin(board, player) != null && difficulty == 2) {
      int[] position = canWin(board, player);
      board.setOwner(player, position[0], position[1]);   
    // check if opponent can win on this move, if so block win (medium/hard)
    } else if (canWin(board, opponent) != null && difficulty > 0) {
      int[] position = canWin(board, opponent);
      board.setOwner(player, position[0], position[1]);
    } else {
      // randomly choose square (all)
      easyTurn(board, player);
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
  
  /** check if middle of board is open */
  private static boolean checkMiddle(Board board) {
    if (board.isEmptyAt(1, 1)) {
      return true;
    } else 
      return false;
  }

  /** checks if given player can win on next move */
  private static int[] canWin(Board board, int player) {
    for (int i = 0; i < 3; i++) {
      // check each row to see if player can win next turn
      if (canRowWin(board, player, i) != null)
        return canRowWin(board, player, i);
      // check each column to see if player can win next turn
      if (canColumnWin(board, player, i) != null)
        return canColumnWin(board, player, i);
    }
    // check diagonals to see if player can win next turn
    if (canDiagonalWin(board, player) != null)
      return canDiagonalWin(board, player);

    return null;
  }

  /** checks a given row for win on next move */
  private static int[] canRowWin(Board board, int player, int row) {
    // Store owner of each square in row in variables
    int sq0 = board.getOwner(row, 0);
    int sq1 = board.getOwner(row, 1);
    int sq2 = board.getOwner(row, 2);

    // check each row for a combination of 2 squares owned by player and 1 empty square
    if (sq0 == player && sq1 == player && sq2 == -1)
      return new int[]{row, 2};
    else if (sq0 == player && sq1 == -1 && sq2 == player)
      return new int[]{row, 1};
    else if (sq0 == -1 && sq1 == player && sq2 == player)
      return new int[]{row, 0};

    // if no matches found return null
    return null;
  }

  /** checks a given column for win on next move */
  private static int[] canColumnWin(Board board, int player, int col) {
    // Store owner of each square in row in variables
    int sq0 = board.getOwner(0, col);
    int sq1 = board.getOwner(1, col);
    int sq2 = board.getOwner(2, col);

    // check each column for a combination of 2 squares owned by player and 1 empty square
    if (sq0 == player && sq1 == player && sq2 == -1)
      return new int[]{2, col};
    else if (sq0 == player && sq1 == -1 && sq2 == player)
      return new int[]{1, col};
    else if (sq0 == -1 && sq1 == player && sq2 == player)
      return new int[]{0, col};

    // if no matches found return null
    return null;
  }

  private static int[] canDiagonalWin(Board board, int player) {
    // if player owns center, check open corners for win
    if (board.getOwner(1, 1) == player) {
      if (board.getOwner(0, 0) == player && board.isEmptyAt(2, 2))
        return new int[] {2, 2};
      if (board.getOwner(2, 2) == player && board.isEmptyAt(0, 0))
        return new int[] {0, 0};
      if (board.getOwner(2, 0) == player && board.isEmptyAt(0, 2))
        return new int[] {0, 2};
      if (board.getOwner(0, 2) == player && board.isEmptyAt(2, 0))
        return new int[] {2, 0};
    // if center is open, check to see if 2 opposite corners are owned by player
    // in reality by second move of game, middle will always be filled, but will continue to check in case a different AI algorithm is used later
    } else if (checkMiddle(board)) {
      int[] middle = new int[]{1 , 1};
      if (board.getOwner(0, 0) == player && board.getOwner(2, 2) == player)
        return middle;
      else if (board.getOwner(2, 0) == player && board.getOwner(0, 2) == player)
        return middle;
    }
    return null;
  }  
}