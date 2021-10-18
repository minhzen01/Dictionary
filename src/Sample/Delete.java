package Sample;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Delete {
    @FXML
    private TextField EngWord;

    @FXML
    private Button deleteButton;

    public void run() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Delete.fxml"));
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Xóa từ");
            window.setScene(scene);
            window.getIcons().add(new Image("/images/icon.png"));
            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Submit() {
        if (EngWord.getText().length() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập từ. Vui lòng kiểm tra lại!");
            alert.showAndWait();
        } else if (!Main.homeController.contains(EngWord.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Từ bạn nhập không có trong từ điển!");
            alert.showAndWait();
        } else {
            Main.homeController.remove(EngWord.getText(), null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Xóa từ thành công!");
            alert.show();
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
        }
    }

}
