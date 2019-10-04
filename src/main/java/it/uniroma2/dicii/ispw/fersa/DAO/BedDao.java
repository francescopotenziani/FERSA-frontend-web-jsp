package it.uniroma2.dicii.ispw.fersa.DAO;

import it.uniroma2.dicii.ispw.fersa.Entity.Apartment;
import it.uniroma2.dicii.ispw.fersa.Entity.Bed;
import it.uniroma2.dicii.ispw.fersa.Entity.Rentable;
import it.uniroma2.dicii.ispw.fersa.Entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class BedDao extends RentableDao{

    public static Rentable findBedByID(Integer bed_ID, Room room){
        Statement stmt = null;
        Connection conn;
        Bed bed = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT room_id, fee, bed_number FROM bed, rentable WHERE idbed = idrentable AND idbed=" +
                    bed_ID + ";";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.first()) {
                return null;
            }
            rs.first();
            Integer bed_fee = rs.getInt("fee");
            Integer bedNumber = rs.getInt("bed_number");
            bed = new Bed(room.getRoomID(),room,bed_ID,bed_fee,bedNumber);
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bed;
    }

    public static void deleteBedbyBed(Bed bed) {
        Connection conn;
        Statement stmt1 = null;
        Statement stmt2 = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            conn.setAutoCommit(false);
            String query1 = "DELETE FROM `fersa`.`rentable`" +
                    "WHERE idrentable=" + bed.getBedID() + ";";
            String query3 = "SELECT FROM `fersa`.`contract`" +
                    "WHERE idRentable= " + bed.getBedID() +";";
            String query2 = "DELETE FROM `fersa`.`bed`" +
                    "WHERE idBed= "+ bed.getBedID() + ";";
            stmt1.execute(query1);
            stmt2.execute(query2);

            conn.commit();

            stmt1.close();
            stmt2.close();
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
                if (stmt2 != null) {
                    stmt2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Bed> findBedsByApartmentID(Apartment apt) {
        Statement stmt = null;
        Connection conn;
        ArrayList<Bed> bedList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT `idbed`,`room_id`,`room_apartment_id`,`fee`,`bed_number` FROM `apartment`, `bed`, `rentable` " +
                    "WHERE `apartment`.`idapartment` = " + apt.getId() + " AND `bed`.`room_apartment_id` = " + apt.getId() + " AND " +
                    "`bed`.`idbed` = `rentable`.`idrentable` order by `room_id`;";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            rs.first();
            do {
                Integer room_ID = rs.getInt("room_id");
                Integer bed_ID = rs.getInt("idbed");
                Integer bedFee = rs.getInt("fee");
                Integer bedNumber = rs.getInt("bed_number");

                Bed r = new Bed(room_ID, bed_ID, bedFee, bedNumber);
                bedList.add(r);
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
        return bedList;
    }

    public static ArrayList<Bed> findBedsbyRoomId(Room room){
        Statement stmt = null;
        Connection conn;
        ArrayList<Bed> bedList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query="SELECT `idbed`,`room_id`,`room_apartment_id`,`fee`,`bed_number` FROM room,`bed`, `rentable` WHERE" +
                    " `room`.`idroom`=" + room.getRoomID() +" AND `bed`.`room_id`=" + room.getRoomID() + " AND " +
                    "`bed`.`idbed` = `rentable`.`idrentable` order by `room_id`;";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            rs.first();
            do {
                Integer room_ID = rs.getInt("room_id");
                Integer bed_ID = rs.getInt("idbed");
                Integer bedFee = rs.getInt("fee");
                Integer bedNumber = rs.getInt("bed_number");

                Bed r = new Bed(room_ID,room, bed_ID, bedFee, bedNumber);
                bedList.add(r);
            } while (rs.next());

            rs.close();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
        return bedList;
    }


    public static Integer insertBedbyBed(Bed newBed) {
        Connection conn;
        Statement stmt1 = null;
        PreparedStatement stmt2 = null;
        Integer last_id = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            stmt1= conn.createStatement();
            String query1 = "INSERT INTO `fersa`.`rentable` (`fee`) VALUES(" + newBed.getBedFee() + ");";
            String query2 = "INSERT INTO `fersa`.`bed`(`idbed`,`bed_number`,`room_id`," +
                    "`room_apartment_id`) VALUES (?,?,?,?);";
            stmt1.executeUpdate(query1,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt1.getGeneratedKeys();
            if(rs.next()){
                last_id = rs.getInt(1);
            }

            stmt2 = conn.prepareStatement(query2);
            stmt2.setInt(1, last_id);
            stmt2.setInt(2,newBed.getBedNumber());
            stmt2.setInt(3,newBed.getRoomID());
            stmt2.setInt(4,newBed.getRoom().getApt().getId());
            stmt2.execute();

            conn.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }

                if (stmt2 != null) {
                    stmt2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return last_id;
    }

    public static void updateBedByBed(Bed bed) {
        Connection conn;
        Statement stmt = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "UPDATE `rentable` " +
                    "SET `fee`=" + bed.getBedFee() + " WHERE `idrentable`= " + bed.getBedID() + ";";
            stmt.execute(query);

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

    public static Integer findRoomIdByBedId(Integer bedId){
        Statement stmt;
        Connection conn;
        Integer room_ID = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT room_id from bed WHERE idbed =" + bedId + ";";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.first()) {
                return null;
            }
            rs.first();
            room_ID = rs.getInt("room_id");

            rs.close();
            stmt.close();
            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room_ID;
    }

    public static void updateBedNumberByBed(Bed bed){
        Connection conn;
        Statement stmt = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "UPDATE `fersa`.`bed` SET `bed_number` = " + bed.getBedNumber() +
                    " WHERE `idbed` = " + bed.getBedID() + ";";
            stmt.execute(query);
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
}
