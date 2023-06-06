package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //Image icon = new Image(getClass().getResourceAsStream("../images/icon.png"));
            //primaryStage.getIcons().add(icon);
            primaryStage.setTitle("La Santé");

            Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Sign.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            scene.setFill(Color.ALICEBLUE);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
