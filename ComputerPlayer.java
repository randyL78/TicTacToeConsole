/**
 * ComputerPlayer
 * @author Randy Layne
 */
public class ComputerPlayer extends Player {
  // Set the maximum difficulty range, will have only 2 modes to start (0, 1)
  public static final int MAX_DIFFICULTY = 1;
  private int difficulty;
  private final static String NAME = "Computer";

  /** default constructor */
  public ComputerPlayer() {
    super(false, NAME);
  }

  /** constructor
   * @param difficulty the level of difficulty for the AI
   */
  public ComputerPlayer(int difficulty) {
    super(false, NAME);
    // use setter so that there is error handling
    setDifficulty(difficulty);
  }

  /**
   * @param difficulty the difficulty to set
   */
  public void setDifficulty(int difficulty) {
    if (difficulty >= 0 && difficulty <= MAX_DIFFICULTY) 
      this.difficulty = difficulty;
    else
      throw new IllegalArgumentException("Difficulty must be 0 to " + MAX_DIFFICULTY);
  }

  /**
   * @return the difficulty
   */
  public int getDifficulty() {
    return difficulty;
  }

  @Override
  public String toString() {
    return super.toString() + " with difficulty of " + difficulty;
  }
}