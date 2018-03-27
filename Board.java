/**
 * Board
 */
public class Board {
  // array to contain squares
  private Square[][] squares;
  // Set the number of rows and squares of the board
  private static final int ROWS = 3;
  private static final int COLS = 3;

  /** constructor */
  public Board() {
    squares = new Square[ROWS][COLS];
    // loop through each row and column and add a Square
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        squares[i][j] = new Square();
      }
    }
  }

  // ============= methods ==================

  /** 
   * Checks if square at an index is unowned
   * @param row the index of the row to check
   * @param col the index of the column to check
   * @return true if square at index is unowned
   */
  public boolean isEmptyAt(int row, int col) {
    return squares[row][col].isEmpty();
  }

  /** 
   * Checks which player owns square at index
   * @param row the index of the row to check
   * @param col the index of the column to check
   * @return player # who owns square
   */
  public int getOwner(int row, int col) {
    return squares[row][col].getOwnedBy();
  }

  /** 
   * Sets which player owns square at index
   * @param player the player # to give ownership to
   * @param row the index of the row to check
   * @param col the index of the column to check
   */
  public void setOwner(int player, int row, int col) {
    squares[row][col].setOwnedBy(player);
  }

  /** clears the board */
  public void clear() {
    // loop through each row and column and clear the Square
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        squares[i][j].clear();
      }
    }    
  }

  /** clears the Square at the index
   * @param row the index of the row to clear
   * @param col the index of the column to clear
   */
  public void clearAt(int row, int col) {
    squares[row][col].clear();
  }

  
}