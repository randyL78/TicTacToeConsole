import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * FileActions
 * Performs saving and loading of players to a txt file
 * @author Randy Layne
 */
public class FileActions {
  // file name to be used for all saving and loading processes
  private static final String FILE_NAME = "saves.dat";

  /** saves the stats of a player to save file */
  public static void savePlayer(HumanPlayer player) throws IOException {

    // create a boolean variable to store whether player exists or not, initially set to false
    boolean playerExists = false;

    // Create a list to store loaded players in
    ArrayList<HumanPlayer> players = new ArrayList<>();

    try (
      ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))
    ) {
      // loop through each Player looking for current player's name
      while(true) {
        HumanPlayer humanPlayer = (HumanPlayer) input.readObject();
        if (humanPlayer.getName().equals(player.getName())) {
          players.add(player);
          playerExists = true;
        } else {
          players.add(humanPlayer);
        }
      }

    // suppress EOF error, when thrown all information will have been gathered
    } catch(EOFException ex) {

     // suppress other IO exceptions if they get thrown treat as if player doesn't exist and create new save file 
    } catch(FileNotFoundException ex) {
    } catch(ClassNotFoundException ex) {

    } 

    // if player isn't in list, add player
    if(!playerExists) 
        players.add(player);

    try (
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
    ) {
      // loop through each player in list and save to file
      for (int i = 0; i < players.size(); i++) {
        output.writeObject(players.get(i));
      }
    }
  }

  public static ArrayList<HumanPlayer> loadPlayers() throws IOException {
    // Initialize an list of players to store loaded players in
    ArrayList<HumanPlayer> players = new ArrayList<>();

    // load the players from the save file
    try ( 
      ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME));
    ) {


      while(true) {
        HumanPlayer humanPlayer = (HumanPlayer) input.readObject();

        players.add(humanPlayer);
    
      }
    } catch(ClassNotFoundException ex) {
      ex.printStackTrace();
    } catch(EOFException ex) {
      // No need to do anything here, when EOF reached, all players are loaded
    }  

    return players;
  }

  public static HumanPlayer loadPlayer(HumanPlayer player) throws IOException {
    // Load players into a list
    ArrayList<HumanPlayer> players = loadPlayers();

    // loop through players
    for (int i = 0; i < players.size(); i++) {
      // if player is found, update from loaded player
      if (players.get(i).getName().equals(player.getName()))
        player = players.get(i);
    }

    return player;
  }
  
}