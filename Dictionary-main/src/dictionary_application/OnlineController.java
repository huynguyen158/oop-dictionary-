package dictionary_application;

import com.darkprograms.speech.translator.GoogleTranslate;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OnlineController implements Initializable {

    @FXML
    private TextField enWord;

    @FXML
    private TextArea viWord;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    /** Khởi tạo thanh Mode. */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("Mode.fxml"));
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

    /** Ngắt dòng cho các từ. */
    public static String convert(String s) {
        int k = 0;
        StringBuilder t = new StringBuilder();
        for(int i = 0; i < s.length(); i ++) {
            t.append(s.charAt(i));
            k++;
            // Nếu độ dài lớn hơn 45 và có dấu cách thì ta ngắt dòng.
            if (k > 45 && s.charAt(i) == ' ') {
                t.append("\n");
                k = 0;
            }
        }
        return t.toString();
    }

    /** Gọi sự kiện khi bấm nút Translate để dịch. */
    public void onClickedButtonTranslate() {
        String enText = enWord.getText();
        try {
            String viText = GoogleTranslate.translate("en-US", "vi", enText);
            viWord.setText(convert(viText));
        } catch (IOException e) {
                Check.showAlertWithError("Vui lòng kết nội mạng");
            }
    }

    /** Gọi sự kiện khi nhấn nút speak ở từ cần dịch. */
    @FXML
    public void onClickSpeakerButtonEn() {
        GoogleAPI api = new GoogleAPI();
        if (Check.internetIsAvailable()) {
            api.speak(enWord.getText());
        } else {
            Check.showAlertWithError("Vui lòng kết nội mạng");
        }
    }


    /** Gọi sự kiện khi nhấn nút speak ở từ dịch nghĩa. */
    @FXML
    public void onClickSpeakerButtonVi() {
        GoogleAPI api = new GoogleAPI();
        if(Check.internetIsAvailable()) {
            api.speak(viWord.getText());
        } else {
            Check.showAlertWithError("Vui lòng kết nội mạng");
        }
    }
}
