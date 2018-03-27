/**
 * Square
 * Represents one Square of a Tic Tac Toe board
 * @author Randy Layne
 */
public class Square {
  private int ownedBy;

  Square() {
    // Set each square to empty initially
    clear();
  }

  /**
   * @return which player ownes square
   */
  public int getOwnedBy() {
    return ownedBy;
  }

  /**
   * @param player the player who owns square
   */
  public void setOwnedBy(int player) {
    ownedBy = player;
  }

  /** 
   * @return true if square is unowned
   */
  public boolean isEmpty() {
    return (ownedBy == -1);
  }

  /**
   * removes ownership of square
   */
  public void clear() {
    ownedBy = -1;
  }


}