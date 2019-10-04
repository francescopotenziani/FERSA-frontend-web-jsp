package it.uniroma2.dicii.ispw.fersa.Bean;


import it.uniroma2.dicii.ispw.fersa.Visitor.Visitor;

public class BedBean extends RentableBean implements Visitable {
    private Integer roomID;
    private RoomBean room;
    private Integer bedNumber;

    public Integer getBedNumber() { return bedNumber; }

    public RoomBean getRoom() {
        return room;
    }

    public Integer getBedID() {
        return id;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public Integer getBedFee() {
        return fee;
    }

    public void setBedNumber(Integer bedNumber) { this.bedNumber = bedNumber; }

    public void setRoom(RoomBean room) {
        this.room = room;
    }

    public void setBedID(Integer bedID) {
        this.id = bedID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public void setBedFee(Integer bedFee) { this.fee = bedFee; }

    public void accept(Visitor v){
        v.visit(this);
    }
}
