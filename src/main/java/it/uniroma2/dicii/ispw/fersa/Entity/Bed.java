package it.uniroma2.dicii.ispw.fersa.Entity;

public class Bed extends Rentable{
    private Integer roomID;
    private Room room;
    private Integer bedNumber;

    public Bed(Integer roomID, Integer bedID, Integer bedFee, Integer bedNumber) {
        super(bedID,bedFee);
        this.roomID = roomID;
        this.bedNumber = bedNumber;
    }

    public Bed(Integer roomID, Room room, Integer bedID, Integer bedFee,Integer bedNumber){
        super(bedID,bedFee);
        this.roomID = roomID;
        this.room = room;
        this.bedNumber = bedNumber;
    }

    public Bed(Integer id, Integer fee) {
        super(id,fee);
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Integer getRoomID() { return roomID; }

    public Integer getBedID() { return id; }

    public Integer getBedFee() { return fee; }

    public Room getRoom() { return room; }

    public void setBedID(Integer idBed) { id=idBed;
    }
}
