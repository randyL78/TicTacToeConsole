import java.io.IOException;
import java.util.ArrayList;

/**
 * TestFileActions
 * Tests the functionality of the file actions class not part of final project
 * @author Randy Layne
 */
public class TestFileActions {

  public static void main(String[] args) {
    // Create a player to save
    HumanPlayer player1 = new HumanPlayer("Ranbo", 3, 1, 4);
    try {
      FileActions.savePlayer(player1);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    // Create another player to save
    HumanPlayer player2 = new HumanPlayer("JohnBoy", 1, 11, 2);
    try {
      FileActions.savePlayer(player2);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

      // Create another player to save
      HumanPlayer player3 = new HumanPlayer("Bilbo", 14, 4, 9);
      try {
        FileActions.savePlayer(player3);
      } catch (IOException ex) {
        ex.printStackTrace();
      }

    // Load the players from save file
    ArrayList<HumanPlayer> players = new ArrayList<>();
    try {
      players = FileActions.loadPlayers();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    // Loop through players and display string representations of them
    for (int i = 0; i < players.size(); i++) {
      System.out.println(players.get(i).toString());
    }
    
  }
}