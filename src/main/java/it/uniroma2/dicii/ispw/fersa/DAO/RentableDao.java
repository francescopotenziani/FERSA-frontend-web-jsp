package it.uniroma2.dicii.ispw.fersa.DAO;


import java.sql.*;

    public abstract class RentableDao {
    protected static String USER = "fersaUser";
    protected static String PASS = "fersa";
    protected static String DB_URL = "jdbc:mariadb://localhost/fersa";
    protected static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";


    public static Integer findRentableFeeByID(Integer id){
        Statement stmt = null;
        Connection conn;
        Integer rentableFee = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT fee FROM fersa.rentable"
                    + " WHERE idrentable = "+ id + ";";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            rs.first();
            rentableFee = rs.getInt("fee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentableFee;
    }
}
