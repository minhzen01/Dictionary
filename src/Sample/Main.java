package Sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Controller homeController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();
            homeController = loader.getController();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("DICTIONARY");
            primaryStage.getIcons().add(new Image("/images/icon.png"));
            primaryStage.show();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
