/**
 * Player
 * Base Player class for Tic Tac Toe players
 * @author Randy Layne
 */
public class Player {
  private boolean isHuman;
  protected String name;

  /** default constructor */
  public Player() {
    this(true, "Player");
  }

  /** constructor 
   * @param type human or computer player
   */
  public Player(String type, String name) {
    if (type.toLowerCase().equals("human")) {
      isHuman = true;
    } else if (type.toLowerCase().equals("computer")) {
      isHuman = false;
    } else {
      throw new IllegalArgumentException("Value for type must be 'computer' or 'human'");
    }
    this.name = name;
  }

  /** constructor
   * @param isHuman boolean
   */
  public Player(boolean isHuman, String name) {
    this.isHuman = isHuman;
    this.name = name;
  }

  /**
   * @return isHuman
   */
  public boolean isHuman() {
    return isHuman;
  }
  
  /**
   * @return human or computer
   */
  public String getType() {
    return (isHuman) ? "human" : "computer";
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  protected void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("A %s player", getType());
  }

}