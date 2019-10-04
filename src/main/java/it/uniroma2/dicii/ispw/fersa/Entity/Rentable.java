package it.uniroma2.dicii.ispw.fersa.Entity;

public class Rentable{
    protected Integer id;
    protected Integer fee;

    public Rentable(Integer id, Integer price) {
        this.id = id;
        this.fee = price;
    }

    public Rentable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public Integer getFee() { return fee; }
    public void setId(Integer id) { this.id = id; }
    public void setFee(Integer fee) { this.fee = fee; }

}
