package dictionary_application;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController extends DictionaryManagement implements Initializable  {


    @FXML
    private TextField enWord;

    @FXML
    private TextArea viWord;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    /** Khởi tạo thanh Mode. */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            VBox box = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mode.fxml")));
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Gọi sự kiện khi nhấn nút speak ở từ cần dịch. */
    @FXML
    public void onClickSpeakerButtonEn() {
        Voice voice = new Voice();
        voice.say(enWord.getText());
    }

    /** Gọi sự kiện khi nhấn nút Translate để dịch nghĩa. */
    @FXML
    public void onClickButtonTranslate() {
        String enText = enWord.getText();
        String viText = wordLookUp(enText);
        if("".equals(viText)) {
            viText += "Không có từ. Chuyển sang chế độ online để tìm kiếm.";
        }
        viWord.setText(viText);
    }
}
