package it.uniroma2.dicii.ispw.fersa.Entity;

import java.util.ArrayList;

public class Room extends Rentable{
    private Apartment apt;
    private ArrayList<Bed> beds;
    private Integer roomNumber;

    public Room(Apartment apt, Integer roomID, Integer roomFee, Integer roomNumber) {
        super(roomID,roomFee);
        this.apt = apt;
        this.roomNumber = roomNumber;
    }

    public Room(Apartment apt, Integer roomID, ArrayList<Bed> beds, Integer roomFee, Integer roomNumber) {
        super(roomID,roomFee);
        this.apt = apt;
        this.beds = beds;
        this.roomNumber = roomNumber;
    }

    public Room(Integer id, Integer room_fee) {
        super(id,room_fee);
    }

    public Apartment getApt() { return apt; }
    public Integer getRoomID() { return id; }
    public ArrayList<Bed> getBeds() { return beds; }
    public Integer getRoomFee() { return fee; }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setBeds(ArrayList<Bed> beds) { this.beds = beds; }
}
