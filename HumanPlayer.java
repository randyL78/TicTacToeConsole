import java.io.Serializable;

/**
 * HumanPlayer
 * @author Randy Layne
 */
public class HumanPlayer extends Player implements Serializable {
  // ========== fields ==============
  private String name;
  private int wins;
  private int losses;
  private int ties;
  private static int count;
  private static final long serialVersionUID = -1099474242691759322L;
   
  // ========= constructors =========
  public HumanPlayer() {
    this("Player_" + ++count);
  }

  public HumanPlayer(String name) {
    this(name, 0, 0, 0);
  }

  public HumanPlayer(String name, int wins, int losses, int ties) {
    super(true, name);
    this.name = name;
    this.wins = wins;
    this.losses = losses;
    this.ties = ties;
    count++;
  }

  // ========= methods ==============
  /**
   * @param wins the wins to set
   */
  public void setWins(int wins) {
    this.wins = wins;
  }

  /**
   * @return the wins
   */
  public int getWins() {
    return wins;
  }

  /** increments the number of wins */
  public void addWin() {
    wins++;
  }

  /**
   * @param losses the losses to set
   */
  public void setLosses(int losses) {
    this.losses = losses;
  }

  /**
   * @return the losses
   */
  public int getLosses() {
    return losses;
  }

  /** increments the number of losses */
  public void addLoss() {
    losses++;
  }

  /**
   * @param ties the ties to set
   */
  public void setTies(int ties) {
    this.ties = ties;
  }

  /**
   * @return the ties
   */
  public int getTies() {
    return ties;
  }

  /** increment the number of ties*/
  public void addTie() {
    ties++;
  }

  /**
   * @return the total number of games player has played
   */
  public int getGamesPlayed() {
    return wins + losses + ties;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("Player %s has %d wins %d losses and %d ties", name, wins, losses, ties);
  }
  
}