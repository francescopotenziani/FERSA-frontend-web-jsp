package it.uniroma2.dicii.ispw.fersa.Bean;



import it.uniroma2.dicii.ispw.fersa.Visitor.Visitor;

import java.util.ArrayList;

public class RoomBean extends RentableBean implements Visitable {
    private ApartmentBean apt;
    private ArrayList<BedBean> beds;
    private Integer room_number;

    public void setRoomNumber(Integer room_number) {
        this.room_number = room_number;
    }

    public void setApt(ApartmentBean apt) { this.apt = apt; }

    public void setRoomID(Integer roomID) { this.id = roomID;}

    public void setBeds(ArrayList<BedBean> beds) { this.beds = beds; }

    public void setRoomFee(Integer roomFee) { this.fee = roomFee; }

    public Integer getRoomNumber() {
        return room_number;
    }



    public ApartmentBean getApartment() { return apt; }

    public Integer getRoomID() { return id; }

    public ArrayList<BedBean> getBeds() { return beds; }

    public Integer getRoomFee() { return fee; }

    public void accept(Visitor v){
        v.visit(this);
    }

}
