import javafx.application.Application;
import javafx.stage.Stage;

/**
 * TestModal
 * used to test the Modal Class
 */
public class TestModal extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Modal messageModal = new Modal("Modal","Hi, I'm a modal!");
    messageModal.getBtnPlay().setOnAction(e -> {
      messageModal.close();
    });
  }
}