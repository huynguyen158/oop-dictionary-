package dictionary_application;

import java.sql.*;
//Tham khảo
//https://itphutran.com/ket-noi-sqlite-voi-jdbc-trong-java/
//https://teamvietdev.com/huong-dan-ket-noi-sqlite-trong-java
//Sử dụng thư viện sqlite-jdbc-3.21.0.jar
public class DictionaryManagement {

    //Đường dẫn database
    // https://github.com/yenthanh132/avdict-database-sqlite-converter
    private static final String DB_URL = "jdbc:sqlite:src\\dictionary_application\\dict_hh.db";

    /** Kết nối Database. */
    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_URL);
    }

    /** Tìm kiếm từ. */
    public static String wordLookUp(String word_target) {
        String c = "";

        try {
            Connection con = connect();
            String sql = "SELECT * FROM av WHERE word = '" + word_target + "'";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()) {
                c = rs.getString("description");
            }

            con.close();
            rs.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /** Them tu vao database */
    public static boolean insertData(String word_target, String word_explain) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if ("".equals(kq)) {
            try {
                String sql = "INSERT INTO av(word, description) VALUES (?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_target);
                ps.setString(2, word_explain);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /** Sửa từ. */
    public static boolean updateData(String word_target, String word_explain) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if (!"".equals(kq)) {
            try {
                String sql = "UPDATE av SET description = ? WHERE word == ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_explain);
                ps.setString(2, word_target);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /** Xóa từ. */
    public static boolean deleteData(String word_target) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if (!"".equals(kq)) {
            try {
                String sql = "DELETE FROM av WHERE word == ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_target);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}


