/**
 * Players
 * Holds player array and tracks turn
 * @author Randy Layne
 */
public class Players {
  // ========== fields ==============
  private boolean firstPlayerTurn = true;
  public Player[] players;

  // ========= constructors =========
  public Players() {
    players = new Player[2];
  }

  public Players(Player player1, Player player2) {
    players = new Player[2];
    players[0] = player1;
    players[1] = player2;
  }

  public Players(Player[] players) {
    this.players = players;
  }
  
  // ========= methods ==============
  /** 
   * flips the current player
   * @return the index of the current player
   */
  public int flip() {
    firstPlayerTurn = !firstPlayerTurn;
    return currentPlayer();
  }

  /**
   * @return firstPlayerTurn
   */
  public boolean isFirstPlayerTurn() {
    return firstPlayerTurn;
  }

  /**
   * @return the index of the current player
   */
  public int currentPlayer() {
    return (firstPlayerTurn) ? 0 : 1;
  }

  /**
   * replace the Player at given index
   */
  public void replace(Player player, int index) {
    players[index] = player;
  }

  public String getCurrentName() {
    return players[currentPlayer()].getName();
  }

}