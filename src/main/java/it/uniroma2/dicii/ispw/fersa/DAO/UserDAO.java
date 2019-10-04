package it.uniroma2.dicii.ispw.fersa.DAO;

import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Entity.User;

import java.sql.*;


public class UserDAO {
    private static String USER = "fersaUser";
    private static String PASS = "fersa";
    private static String DB_URL = "jdbc:mariadb://localhost/fersa";
    private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
    private Connection conn;


    public static User findUserByUsernameAndPassword(String username, String password) {
        Statement stmt = null;
        Connection conn = null;
        User u = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT name, surname, username, password, cf, address, birth, birth_place,email FROM " +
                    "User WHERE username = '" + username + "' and password = '" + password + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }

            rs.first();

            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String usrname= rs.getString("username");
            String cf = rs.getString("cf");
            String address = rs.getString("address");
            Date birth = rs.getDate("birth");
            String birth_place = rs.getString("birth_place");
            String email = rs.getString("email");

            u = new User(usrname,null,name,surname,cf,address,birth,birth_place,email);

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException se) {
            se.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    public User findUserByUsername(String username, Connection connection){
        Statement stmt = null;
        User u = null;
        try {

            getConnection(connection);
            stmt = conn.createStatement();
            String query = "SELECT name, surname, username, password, cf, address, birth, birth_place, email FROM " +
                    "User WHERE username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            rs.first();
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String usrname= rs.getString("username");
            String cf = rs.getString("cf");
            String address = rs.getString("address");
            Date birth = rs.getDate("birth");
            String birth_place = rs.getString("birth_place");
            String email = rs.getString("email");
            u = new User(usrname,null,name,surname,cf,address,birth,birth_place,email);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    private void getConnection(Connection conn) {
        this.conn = conn;
    }
}
