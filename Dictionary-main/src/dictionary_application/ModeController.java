package dictionary_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeController {

    @FXML
    public Button Home;

    @FXML
    public Button Online;

    @FXML
    public Button About;

    @FXML
    public Button ManageWord;

    public ModeController() {
    }

    /** Click chọn tab Home.*/
    @FXML
    private void onClickButtonToHome(ActionEvent event) throws IOException {
        Parent toHomePage = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene toHomePage_scene = new Scene(toHomePage);
        Stage toHomePage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toHomePage_stage.setScene(toHomePage_scene);
        toHomePage_stage.show();
    }

    /** Click chọn tab Online.*/
    @FXML
    private void onClickButtonToOnline(ActionEvent event) throws IOException {
        Parent toGgPage = FXMLLoader.load(getClass().getResource("Online.fxml"));
        Scene toGgPage_scene = new Scene(toGgPage);
        Stage toGgPage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toGgPage_stage.setScene(toGgPage_scene);
        toGgPage_stage.show();
    }

    /** Click chọn tab Manage Word.*/
    @FXML
    private void onClickButtonToManageWord(ActionEvent event) throws IOException {
        Parent toManagePage = FXMLLoader.load(getClass().getResource("ManageWord.fxml"));
        Scene toGgPage_scene = new Scene(toManagePage);
        Stage toGgPage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toGgPage_stage.setScene(toGgPage_scene);
        toGgPage_stage.show();
    }

    /** Click chọn tab About.*/
    @FXML
    private void onClickButtonToAbout(ActionEvent event) throws IOException {
        Parent toAboutPage = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene toGgPage_scene = new Scene(toAboutPage);
        Stage toGgPage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toGgPage_stage.setScene(toGgPage_scene);
        toGgPage_stage.show();
    }
}
