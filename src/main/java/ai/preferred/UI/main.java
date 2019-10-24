package ai.preferred.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 887, 587);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SMUSISWorkshop - Your Worth Prediction");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
