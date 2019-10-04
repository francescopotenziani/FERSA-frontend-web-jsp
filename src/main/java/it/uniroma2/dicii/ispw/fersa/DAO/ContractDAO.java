package it.uniroma2.dicii.ispw.fersa.DAO;

import com.itextpdf.kernel.pdf.PdfReader;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Entity.*;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractDAO {
    private static String USER = "fersaUser";
    private static String PASS = "fersa";
    private static String DB_URL = "jdbc:mariadb://localhost/fersa";
    private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";

    public static ArrayList<Contract> findContractByUser(User user) throws SQLException {
        Statement stmt = null;
        Connection conn = null;
        Contract c;
        ArrayList<Contract> contractList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            String query = "SELECT * FROM contract WHERE `lessor` ='" +user.getUsername() +"'; ";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.first()) {
                return null;
            }
            rs.first();
            do {
                String r = rs.getString("renter");
                LocalDate start;
                try{
                    start = rs.getDate("start_date").toLocalDate();
                } catch (NullPointerException e){
                    start = null;
                }
                LocalDate end;
                try {
                    end = rs.getDate("end_date").toLocalDate();
                } catch (NullPointerException e){
                    end = null;
                }
                Integer id = rs.getInt("idRentable");
                StateEnum approved = StateEnum.valueOf(rs.getString("state"));
                UserDAO userDAO = new UserDAO();
                User renter = userDAO.findUserByUsername(r,conn);
                Rentable rentable = new Rentable(id);
                PdfReader pdfContract = new PdfReader(rs.getBinaryStream("pdfContract"));
                LocalDateTime localDateTime = null;
                Timestamp timestamp = rs.getTimestamp("date_to_expire");
                if (timestamp != null){
                localDateTime = new LocalDateTime(timestamp);
                }

                c = new Contract(user,rentable,renter,start,end,pdfContract,approved, localDateTime);
                contractList.add(c);
            } while (rs.next());

            conn.commit();
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            conn.rollback();
            e.printStackTrace();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } catch (IOException e) {
            conn.rollback();
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
        return contractList;
    }

    public static Contract findContractByRentable(Rentable rentable) throws SQLException {
        Statement stmt = null;
        Connection conn = null;
        Contract contract = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String query = "SELECT * FROM contract WHERE `idrentable` ='" +rentable.getId()+"'; ";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.first()) {
                return null;
            }
            rs.first();
            String r = rs.getString("renter");
            String l = rs.getString("lessor");
            LocalDate start;
            try{
                start = rs.getDate("start_date").toLocalDate();
            } catch (NullPointerException e){
                start = null;
            }
            LocalDate end;
            try {
                end = rs.getDate("end_date").toLocalDate();
            } catch (NullPointerException e){
                end = null;
            }
            Integer id = rs.getInt("idRentable");
            StateEnum approved = StateEnum.valueOf(rs.getString("state"));
            UserDAO userDAO = new UserDAO();
            User renter = userDAO.findUserByUsername(r,conn);
            User lessor = userDAO.findUserByUsername(l,conn);
            PdfReader pdfContract = new PdfReader(rs.getBinaryStream("pdfContract"));
            LocalDateTime localDateTime = null;
            Timestamp timestamp = rs.getTimestamp("date_to_expire");
            if (timestamp != null){
                localDateTime = new LocalDateTime(timestamp);
            }
            contract = new Contract(lessor,rentable,renter,start,end,pdfContract,approved, localDateTime);
            conn.commit();
        } catch (ClassNotFoundException e) {
            conn.rollback();
            e.printStackTrace();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } catch (IOException e) {
            conn.rollback();
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contract;
    }

    public static void findRentableTypeByContract(Contract contract) {
        Statement stmt = null;
        Connection conn;
        Rentable rentable = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM `fersa`.`rentable` left outer JOIN `fersa`.`apartment` ON (idrentable = idapartment)" +
                    " left outer join `fersa`.`room` ON (idrentable = idroom) left outer join `fersa`.`bed` ON " +
                    "(idrentable = idbed) where (idrentable =" + contract.getRentable().getId() + " and " +
                    "idapartment =" + contract.getRentable().getId() + ") or (idrentable =" + contract.getRentable().getId() +
                    " and idroom =" + contract.getRentable().getId() + ") or (idrentable =" + contract.getRentable().getId() +
                    " and idbed =" + contract.getRentable().getId() + ");";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return;
            }
            rs.first();

            try {
                Integer idApartment = rs.getInt("idapartment");
                Integer idRoom =  rs.getInt("idroom");
                Integer idBed = rs.getInt("idbed");

                if (idApartment != 0){
                    Integer fee =  rs.getInt("fee");
                    rentable = new Apartment(idApartment,fee);
                    contract.setRentable(rentable);
                } else if(idRoom != 0){
                    Integer room_fee = rs.getInt("fee");
                    rentable = new Room(idRoom,room_fee);
                    contract.setRentable(rentable);
                } else{
                    Integer fee = rs.getInt("fee");
                    rentable = new Bed(idBed,fee);
                    contract.setRentable(rentable);
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }


            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
    }

    public static Boolean findContractByBedID(int idBed){
        Statement stmt = null;
        Connection conn;
        Contract c;
        ArrayList<Contract> contractList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM contract WHERE `idRentable` ='" +idBed+"'; ";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.first()) {
                rs.close();
                stmt.close();
                conn.close();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        return true;
    }

    public static void updateContractByContract(Contract c){
        Connection conn;
        Statement stmt1 = null;
        Statement stmt2 = null;
        Timestamp timeExpireSql;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            String query1;
            try {
                timeExpireSql = new Timestamp(c.getTimeToExpire().toDateTime().getMillis()) ;
                query1 = "UPDATE `contract` " +
                        "SET `state`='" + c.getState() + "', `date_to_expire`='" + timeExpireSql + "' WHERE `idrentable`= " + c.getRentable().getId() + ";";
            } catch (NullPointerException e){
                timeExpireSql = null;
                query1 = "UPDATE `contract` " +
                        "SET `state`='" + c.getState() + "', `date_to_expire`=" + timeExpireSql + " WHERE `idrentable`= " + c.getRentable().getId() + ";";
            }
            String query2 = "UPDATE `rentable` SET `fee`=" + c.getRentable().getFee() + " WHERE `idrentable`=" + c.getRentable().getId() + ";";

            stmt1.execute(query1);
            stmt2.execute(query2);

            stmt1.close();
            stmt2.close();
            conn.commit();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static LocalDateTime findTimeToExpireByRentableID(int rentableID){
        Statement stmt = null;
        Connection conn;
        LocalDateTime localDateTime=null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT `date_to_expire` FROM contract WHERE `idRentable` =" +rentableID+"; ";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.first()) {
                return null;
            }
            rs.first();
                Timestamp timestamp = rs.getTimestamp("date_to_expire");
                if (timestamp != null){
                    localDateTime = new LocalDateTime(timestamp);
                }
                rs.close();
                stmt.close();
                conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        return localDateTime;
    }

    public static boolean deleteContractByContract(Contract contract){
        Connection conn;
        Statement stmt1 = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();
            String query1 = "DELETE FROM `fersa`.`contract`" +
                    "WHERE idrentable=" + contract.getRentable().getId() + ";";
            stmt1.execute(query1);

            stmt1.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
