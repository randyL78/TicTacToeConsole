import javafx.scene.layout.Pane;

/**
 * SquarePane
 * The GUI representation of an individual square
 * @author Randy Layne
 */
public class SquarePane extends Pane {
  public SquarePane() {
    setStyle("-fx-border-color: blue");
    setPrefSize(300, 300);
  }
  
}