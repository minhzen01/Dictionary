package Sample;

import Dictionary.DictionaryManagement;
import Dictionary.Word;
import Dictionary.Dictionary;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class Controller implements Initializable {
    public static DictionaryManagement manage = new DictionaryManagement();
    public Voice voice;

    public void Speak(String c) {
        voice = VoiceManager.getInstance().getVoice("kevin");//Getting voice
        if (voice != null) {
            voice.allocate();//Allocating Voice
        }
        try {
            voice.setRate(150);//Setting the rate of the voice
            voice.setPitch(90);//Setting the Pitch of the voice
            voice.setVolume(10);//Setting the volume of the voice
            voice.speak(c);//Calling speak() method

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private ListView<String> LVEng;

    @FXML
    private TextArea textVie;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button audioButton;

    private final Add addWindow = new Add();
    private final Delete deleteWindow = new Delete();
    private final Mod modifyWindow = new Mod();
    private ArrayList<String> lvEnggg = new ArrayList<>();

//    ArrayList<String> lvEnggg = new ArrayList<>();
//    public ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            manage.insertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < manage.getDic().getListWord().size(); i++) {
            lvEnggg.add(manage.getDic().getWord(i).getWord_target());
//            list.add(manage.getDic().getWord(i).getWord_target());
        }
        LVEng.getItems().addAll(lvEnggg);

        LVEng.setOnMouseClicked(event -> {
            String searchedWord = LVEng.getSelectionModel().getSelectedItem();
            manage.getDic().getListWord().forEach((i) -> {
                int index = i.getWord_target().indexOf(searchedWord);
                if (index == 0) {
                    String meaning = i.getWord_explain();
                    textVie.setText(searchedWord + "\n" + meaning);
                }
            });
            audioButton.setOnMouseClicked(event1 -> {
                Speak(searchedWord);
            });
        });

        tfSearch.setOnKeyReleased(event -> {
            String searchedWord = tfSearch.getText();
            LVEng.getItems().clear();
            manage.getDic().getListWord().forEach((i) -> {
                int index = i.getWord_target().indexOf(searchedWord);
                if (index == 0) {
                    LVEng.getItems().add(i.getWord_target());
//                    System.out.printf("%c %-20s%c %-20s%n", '|', i.getWord_target(), '|', i.getWord_explain());
                }
            });
            int index = lvEnggg.indexOf(searchedWord);
            if (index == -1) {
                textVie.setText(searchedWord + "\n" + "Không tìm thấy từ");
            } else {
                String meaning = manage.getDic().getListWord().get(index).getWord_explain();
                textVie.setText(searchedWord + "\n" + meaning);
            }
            audioButton.setOnMouseClicked(event1 -> {
                Speak(searchedWord);
            });
        });
    }

    public boolean contains(String key) {
        int index = lvEnggg.indexOf(key);

        return index != -1;
    }

    public void add(String text, String text1) {
        manage.getDic().addWord(text, text1);
        reloadDic();
    }

    public void reloadDic() {
        LVEng.getItems().clear();
        lvEnggg = new ArrayList<>();
        for (int i = 0; i < manage.getDic().getListWord().size(); i++) {
            lvEnggg.add(manage.getDic().getWord(i).getWord_target());
//            list.add(manage.getDic().getWord(i).getWord_target());
        }
//        Collections.sort(lvEnggg);
        LVEng.getItems().addAll(lvEnggg);
    }

    public void remove(String text, String text1) {
        manage.getDic().removeWord(text, text1);
        reloadDic();
    }

    public static void modify(String text, String text1) {
        manage.getDic().modifyWord(text, text1);
    }

    public void openAddWindow() {
        addWindow.run();
    }

    public void openModifyWindow() {
        modifyWindow.run();
    }

    public void openDeleteWindow() {
        deleteWindow.run();
    }


}
