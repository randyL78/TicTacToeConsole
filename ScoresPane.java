import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.geometry.Insets;

/** 
 * ScoresPane
 * defines a verticle box pane for housing player scores in main game
 * @author Randy Layne
 */
public class ScoresPane extends VBox {
  private Player player;
  private Text winsText;
  private Text lossesText;
  private Text tiesText;

  public ScoresPane(Player player) {
    this.player = player;

    // Display name
    Text nameText = new Text(player.getName());
    nameText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
    getChildren().addAll(nameText);

    if (player.isHuman()) {
      // Display scores of human players
      // Wins
      Text winsLabel = new Text("Wins:");
      winsText = new Text("" + ((HumanPlayer) player).getWins());
      // Losses
      Text lossesLabel = new Text("Losses:");
      lossesText = new Text("" + ((HumanPlayer) player).getLosses());
      // Ties
      Text tiesLabel = new Text("Ties:");
      tiesText = new Text("" + ((HumanPlayer) player).getTies());

      getChildren().addAll(winsLabel, winsText, lossesLabel, lossesText, tiesLabel, tiesText);
    } 

    // set pane styling
    setStyle("-fx-background-color: aqua");
    setSpacing(5);
    setPadding(new Insets(5, 10, 0, 10));
    setMinWidth(170);
  }

  /** Updates the displayed scores for the player */
  public void updateScores() {
    // verify player is human before attempting to convert
    if (player.isHuman()) {
      winsText.setText("" + ((HumanPlayer) player).getWins());
      lossesText.setText("" + ((HumanPlayer) player).getLosses());
      tiesText.setText("" + ((HumanPlayer) player).getTies());
    }  
  }
}