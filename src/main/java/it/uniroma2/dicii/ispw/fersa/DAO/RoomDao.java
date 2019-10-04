package it.uniroma2.dicii.ispw.fersa.DAO;

import it.uniroma2.dicii.ispw.fersa.Entity.Apartment;
import it.uniroma2.dicii.ispw.fersa.Entity.Rentable;
import it.uniroma2.dicii.ispw.fersa.Entity.Room;

import java.sql.*;
import java.util.ArrayList;


public class RoomDao extends RentableDao {

    public static Rentable findById(Integer room_id, Apartment apt) {
        Statement stmt = null;
        Connection conn;
        Room room = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT idroom, fee, room_number from room, rentable WHERE idroom = idrentable AND idroom=" +
                    room_id + ";";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.first()) {
                return null;
            }
            rs.first();
            Integer room_ID = rs.getInt("idroom");
            Integer room_fee = rs.getInt("fee");
            Integer room_number = rs.getInt("room_number");
            room = new Room(apt,room_ID,room_fee, room_number);
            rs.close();
            stmt.close();
            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    public static Integer findApartmentIDfromRoomID(Integer room_id){
    Statement stmt = null;
    Connection conn;
    Integer apt_ID = null;
        try {
        Class.forName(DRIVER_CLASS_NAME);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String query = "SELECT apartment_id from room WHERE idroom =" + room_id + ";";
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.first()) {
            return null;
        }
        rs.first();
        apt_ID = rs.getInt("apartment_id");

        rs.close();
        stmt.close();
        conn.close();


    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return apt_ID;
}


    public static ArrayList<Room> findRoomByApartmentID(Apartment apt) {
        Statement stmt = null;
        Connection conn;
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT `idroom`,`fee`,`room_number` FROM `apartment`, `room`,`rentable`" +
                    "WHERE `apartment`.`idapartment` = " + apt.getId() + " AND `room`.`apartment_id` = " + apt.getId() + " AND" +
                    " `room`.`idroom` = `rentable`.`idrentable`;";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            rs.first();
            do {
                Integer room_ID = rs.getInt("idroom");
                Integer room_fee = rs.getInt("fee");
                Integer room_number = rs.getInt("room_number");

                Room r = new Room(apt, room_ID, room_fee, room_number);
                roomList.add(r);
            } while (rs.next());

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
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
        return roomList;
    }

    public static void updateRoomByRoom(Room room) {
        Connection conn;
        Statement stmt = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "UPDATE `rentable` SET" +
                    "`fee`=" + room.getRoomFee() + " " +
                    "WHERE `idrentable`=" + room.getRoomID() + ";";
            stmt.execute(query);
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

    public static Room findRoomById(Integer room_id, Apartment apartment) {
        Statement stmt = null;
        Connection conn;
        Room room = null;
        try{
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM room, rentable WHERE idroom=" +room_id + " AND idrentable =" + room_id + ";";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            rs.first();
            Integer room_fee = rs.getInt("fee");
            Integer room_number = rs.getInt("room_number");
            room= new Room(apartment,room_id,room_fee,room_number);
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return room;
    }
}
