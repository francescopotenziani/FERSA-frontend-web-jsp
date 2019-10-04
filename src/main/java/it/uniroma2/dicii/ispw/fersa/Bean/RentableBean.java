package it.uniroma2.dicii.ispw.fersa.Bean;


import it.uniroma2.dicii.ispw.fersa.Visitor.Visitor;

public class RentableBean implements Visitable {
    protected Integer id;
    protected Integer fee;

    public void accept(Visitor v){
        v.visit(this);
    }

    public Integer getId() { return id; }

    public Integer getFee() { return fee; }

    public void setFee(Integer fee) { this.fee = fee; }

    public void setId(Integer id) { this.id = id; }
}
