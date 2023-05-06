package dictionary_application;

import javafx.scene.control.Alert;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Check {

    /** Kiểm tra kết nối Internet .*/
    public static boolean internetIsAvailable() {
        try {
            final URL url = new URL("https://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    /** Bảng thông báo cần kết nối Internet .*/
    public static void showAlertWithError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Error connect Internet!!!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /** Bảng thông báo chỉnh sửa thành công hay thất bại .*/
    public static void showResult(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kết Quả");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
