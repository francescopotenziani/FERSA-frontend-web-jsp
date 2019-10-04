package it.uniroma2.dicii.ispw.fersa.Bean;


import it.uniroma2.dicii.ispw.fersa.Visitor.Visitor;

public interface Visitable {
     void accept(Visitor v);
}
