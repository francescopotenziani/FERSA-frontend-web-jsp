package it.uniroma2.dicii.ispw.fersa.Entity;

import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;

import java.sql.Date;
import java.util.ArrayList;
public class Apartment extends Rentable{
    private User lessor;
    private String address;
    private Date insert_date;
    private ArrayList<Room> rooms;
    private ArrayList<Bed> beds;
    private Boolean furnished;
    private String heating_type;
    private Boolean wifi;
    private Boolean shared_room_space;
    private Boolean tv;
    private Boolean airconditioning;
    private Boolean washingMachine;
    private Boolean dryer;
    private Boolean dishWasher;
    private Integer condominiumFee;
    private String utilityBillsType;
    private String rentType;

    public Apartment(Integer id, String address, Date d, Boolean f, String ht, Boolean wifi, Boolean srs, Boolean tv, Boolean ac, Boolean wm, Boolean dryer, Boolean dish_washer, Integer rentalFee, Integer condominiumFee, String utilityBillsType, String rt) {
        super(id,rentalFee);
    }


    public Integer getId() {
        return id;
    }

    public User getLessor() {
        return lessor;
    }

    public String getAddress() {
        return address;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public String getHeating_type() {
        return heating_type;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public Boolean getShared_room_space() {
        return shared_room_space;
    }

    public Boolean getTv() {
        return tv;
    }

    public Boolean getAirconditioning() {
        return airconditioning;
    }

    public Boolean getWashingMachine() {
        return washingMachine;
    }

    public Boolean getDryer() {
        return dryer;
    }

    public Boolean getDishWasher() {
        return dishWasher;
    }

    public Integer getRentalFee() {
        return fee;
    }

    public Integer getCondominiumFee() {
        return condominiumFee;
    }

    public String getUtilityBillsType() {
        return utilityBillsType;
    }

    public String getRentType() { return rentType; }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }


    public Apartment(Integer id, Integer fee){
        super(id,fee);
    }

    public Apartment(Integer id, User lessor, String address, Date insert_date, Boolean furnished, String heating_type,
                     Boolean wifi, Boolean shared_room_space, Boolean tv, Boolean airconditioning, Boolean washingMachine,
                     Boolean dryer, Boolean dishWasher, Integer rentalFee, Integer condominiumFee, String utilityBillsType,
                     String rentType) {
        super(id,rentalFee);
        this.lessor = lessor;
        this.address = address;
        this.insert_date = insert_date;
        this.furnished = furnished;
        this.heating_type = heating_type;
        this.wifi = wifi;
        this.shared_room_space = shared_room_space;
        this.tv = tv;
        this.airconditioning = airconditioning;
        this.washingMachine = washingMachine;
        this.dryer = dryer;
        this.dishWasher = dishWasher;
        this.condominiumFee = condominiumFee;
        this.utilityBillsType = utilityBillsType;
        this.rentType = rentType;
    }
}

