package it.uniroma2.dicii.ispw.fersa.Bean;
import it.uniroma2.dicii.ispw.fersa.Visitor.Visitor;

import java.sql.Date;
import java.util.ArrayList;

public class ApartmentBean extends RentableBean implements Visitable {
    private UserBean lessor;
    private String address;
    private Date insert_date;
    private ArrayList<RoomBean> rooms;
    private ArrayList<BedBean> beds;
    private Boolean furnished;
    private String heating_type;
    private Boolean wifi;
    private Boolean shared_room_space;
    private Boolean tv;
    private Boolean airConditioning;
    private Boolean washingMachine;
    private Boolean dryer;
    private Boolean dishWasher;
    private String utilityBillsType;
    private Integer condominiumFee;
    private String rentType;


    public void setId(Integer id) {
        this.id = id;
    }
    public void setLessor(UserBean user) { this.lessor = user; }
    public void setAddress(String addr){
        this.address = addr;
    }
    public void setFurnished(Boolean b) { this.furnished = b; }
    public void setHeating_type(String h) { this.heating_type = h; }
    public void setWifi(Boolean w) { this.wifi = w; }
    public void setShared_room_space(Boolean s) { this.shared_room_space = s; }
    public void setTv(Boolean t) { this.tv = t; }
    public void setAirConditioning(Boolean a) { this.airConditioning = a; }
    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }
    public void setDryer(Boolean dryer) { this.dryer = dryer; }
    public void setDishWasher(Boolean dishWasher) { this.dishWasher = dishWasher; }
    public void setWashingMachine(Boolean washingMachine) { this.washingMachine = washingMachine; }
    public void setUtilityBills_Type(String utility_Type) { this.utilityBillsType = utility_Type; }
    public void setRentalFee(Integer rentalFee) { this.fee = rentalFee; }
    public void setCondominiumFee(Integer condominiumFee) { this.condominiumFee = condominiumFee; }
    public void setRooms(ArrayList<RoomBean> rooms) { this.rooms = rooms; }
    public void setBeds(ArrayList<BedBean> bedList) { this.beds = bedList; }
    public void setRentType(String rentType) {this.rentType = rentType;
    }
    public Integer getId() { return this.id; }
    public UserBean getLessor() { return this.lessor; }
    public String getAddress() { return this.address; }
    public Date getInsert_date() {
        return insert_date;
    }
    public Boolean getFurnished() { return this.furnished; }
    public String getHeating_type() { return this.heating_type; }
    public Boolean getWifi() { return this.wifi; }
    public Boolean getShared_room_space() { return this.shared_room_space; }
    public Boolean getTv() { return this.tv; }
    public Boolean getAirConditioning() { return this.airConditioning; }
    public Boolean getWashingMachine() { return washingMachine; }
    public Boolean getDryer() { return dryer; }
    public Boolean getDishWasher() { return dishWasher; }
    public String getUtilityBillsType() { return utilityBillsType; }
    public Integer getRentalFee() { return fee; }
    public Integer getCondominiumFee() { return condominiumFee; }
    public ArrayList<RoomBean> getRooms() { return rooms; }
    public ArrayList<BedBean> getBeds() { return beds; }
    public String getRentType() {  return rentType; }

    public void accept(Visitor v){
        v.visit(this);
    }
}
