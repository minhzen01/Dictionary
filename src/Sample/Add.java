package Sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Add {
    @FXML
    private TextField EngWord;

    @FXML
    private TextField VieWord;

    @FXML
    private Button addButton;

    public void run() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Thêm từ mới");
            window.setScene(scene);
            window.getIcons().add(new Image("/images/icon.png"));
            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Submit (ActionEvent event) {
        if (EngWord.getText().length() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập từ.");
            alert.show();
        }
        else if (VieWord.getText().length() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập nghĩa.");
            alert.show();
        }
        else {
            Main.homeController.add(EngWord.getText(), VieWord.getText());

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thêm từ thành công!");
            alert.show();
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();

        }

    }



}
