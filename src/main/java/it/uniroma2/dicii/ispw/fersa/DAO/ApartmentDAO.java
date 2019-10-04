package it.uniroma2.dicii.ispw.fersa.DAO;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Entity.Apartment;
import it.uniroma2.dicii.ispw.fersa.Entity.User;

import java.sql.*;
import java.util.ArrayList;

public class ApartmentDAO extends RentableDao{

    public static Apartment findApartmentByID(Integer id, User user){
        Statement stmt = null;
        Connection conn;
        Apartment a = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM fersa.apartment, fersa.rentable"
                    + " WHERE apartment.user = '" + user.getUsername() + "' AND apartment.idapartment = rentable.idrentable AND rentable.idrentable="+
                    id + ";";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            rs.first();
                Integer idapartment = rs.getInt("idapartment");
                String address = rs.getString("address");
                Date d = rs.getDate("insert_date");
                Boolean f = rs.getBoolean("furnished");
                String ht = rs.getString("heating_type");
                Boolean wifi = rs.getBoolean("wi-fi");
                Boolean srs = rs.getBoolean("shared_room_space");
                Boolean tv = rs.getBoolean("tv");
                Boolean ac = rs.getBoolean("airconditioning");
                Boolean wm = rs.getBoolean("washing_machine");
                Boolean dryer = rs.getBoolean("dryer");
                Boolean dish_washer = rs.getBoolean("dish_washer");
                String utilityBillsType = rs.getString("utilityBillsType");
                Integer rentalFee = rs.getInt("fee");
                Integer condominiumFee = rs.getInt("condominiumFee");
                String rt = rs.getString("rent_type");

                a = new Apartment(idapartment, user, address, d, f, ht, wifi, srs, tv, ac, wm, dryer, dish_washer, rentalFee,
                        condominiumFee, utilityBillsType, rt);

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
        return a;
    }
    public static ArrayList<Apartment> findApartmentByUser(User user) {
        Statement stmt = null;
        Connection conn;
        Apartment a;
        ArrayList<Apartment> list = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM fersa.apartment, fersa.rentable"
                    + " WHERE apartment.user = '" + user.getUsername() + "' AND apartment.idapartment = rentable.idrentable;";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()) {
                return null;
            }
            list = new ArrayList<Apartment>();
            rs.first();
            do {
                Integer id = rs.getInt("idapartment");
                String address = rs.getString("address");
                Date d = rs.getDate("insert_date");
                Boolean f = rs.getBoolean("furnished");
                String ht = rs.getString("heating_type");
                Boolean wifi = rs.getBoolean("wi-fi");
                Boolean srs = rs.getBoolean("shared_room_space");
                Boolean tv = rs.getBoolean("tv");
                Boolean ac = rs.getBoolean("airconditioning");
                Boolean wm = rs.getBoolean("washing_machine");
                Boolean dryer = rs.getBoolean("dryer");
                Boolean dish_washer = rs.getBoolean("dish_washer");
                String utilityBillsType = rs.getString("utilityBillsType");
                Integer rentalFee = rs.getInt("fee");
                Integer condominiumFee = rs.getInt("condominiumFee");
                String rt = rs.getString("rent_type");

                a = new Apartment(id, user, address, d, f, ht, wifi, srs, tv, ac, wm, dryer, dish_washer, rentalFee,
                        condominiumFee, utilityBillsType, rt);
                list.add(a);
            } while (rs.next());

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
        return list;
    }

    public static void updateApartmentByID(Apartment apt) {
        Connection conn;
        Statement stmt1 = null;
        Statement stmt2 = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();

            String query1 = "UPDATE `fersa`.`apartment` SET" +
                    "`furnished`=" + apt.getFurnished() + ","+
                    "`condominiumFee`=" + apt.getCondominiumFee() + "," +
                    "`heating_type`='" + apt.getHeating_type() + "'," +
                    "`wi-fi`=" + apt.getWifi() + "," +
                    "`shared_room_space`=" + apt.getShared_room_space() + "," +
                    "`tv`=" + apt.getTv() + "," +
                    "`airconditioning`=" + apt.getAirconditioning() + "," +
                    "`washing_machine`=" + apt.getWashingMachine() + "," +
                    "`dryer`=" + apt.getDryer()+ "," +
                    "`dish_washer`=" + apt.getDishWasher() + "," +
                    "`utilityBillsType`='" + apt.getUtilityBillsType() + "'," +
                    "`rent_type`='" + apt.getRentType() + "' " +
                    "WHERE `idapartment` = " + apt.getId() + ";";

            String query2 = "UPDATE `fersa`.rentable SET `fee` =" + apt.getRentalFee()+" WHERE `idrentable` = " +
                    apt.getId() +";";
            stmt1.execute(query1);
            stmt2.execute(query2);
            conn.commit();

            stmt1.close();
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
}
