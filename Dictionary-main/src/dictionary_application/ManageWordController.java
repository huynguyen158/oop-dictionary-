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
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageWordController extends DictionaryManagement implements Initializable {

    @FXML
    private TextField enWord;

    @FXML
    private TextArea viWord;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

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

    /** Gọi sự kiện khi nhấn nút Edit. */
    @FXML
    public void onClickButtonEdit() throws SQLException, ClassNotFoundException {
        String enText = enWord.getText();
        String viText = viWord.getText();
        if(updateData(enText, viText)) {
            Check.showResult("Thành công");
        } else {
            Check.showResult("Từ " + enWord.getText() + " không có.");
        }
    }

    /** Gọi sự kiện khi nhấn nút Add. */
    @FXML
    public void onClickButtonAdd() throws SQLException, ClassNotFoundException {
        String enText = enWord.getText();
        String viText = viWord.getText();
        if(insertData(enText, viText)) {
            Check.showResult("Thành công");
        } else {
            Check.showResult("Từ " + enWord.getText() + " đã có.");
        }
    }

    /** Gọi sự kiện khi nhấn nút Remove. */
    @FXML
    public void onClickButtonRemove() throws SQLException, ClassNotFoundException {
        String enText = enWord.getText();
        if(deleteData(enText)) {
            Check.showResult("Thành công");
        } else {
            Check.showResult("Không tìm thấy " + enWord.getText() + " để xóa.");
        }
    }
}
