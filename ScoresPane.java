import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.geometry.Insets;

/** 
 * ScoresPane
 * defines a verticle box pane for housing player scores in main game
 */
public class ScoresPane extends VBox {
  public ScoresPane(Player player) {
    // Display name
    Text nameText = new Text(player.getName());
    nameText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
    getChildren().addAll(nameText);

    if (player.isHuman()) {
      // Display scores of human players
      // Wins
      Text winsLabel = new Text("Wins:");
      Text winsText = new Text("" + ((HumanPlayer) player).getWins());
      // Losses
      Text lossesLabel = new Text("Losses:");
      Text lossesText = new Text("" + ((HumanPlayer) player).getLosses());
      // Ties
      Text tiesLabel = new Text("Ties:");
      Text tiesText = new Text("" + ((HumanPlayer) player).getTies());

      getChildren().addAll(winsLabel, winsText, lossesLabel, lossesText, tiesLabel, tiesText);
    } 

    // set pane styling
    setStyle("-fx-background-color: aqua");
    setSpacing(5);
    setPadding(new Insets(5, 10, 0, 10));
    setMinWidth(170);
  }
}