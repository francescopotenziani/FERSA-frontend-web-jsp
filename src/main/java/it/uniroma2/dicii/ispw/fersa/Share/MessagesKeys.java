package it.uniroma2.dicii.ispw.fersa.Share;

public enum MessagesKeys {
    USERNAME("%username")
    ;

    private final String toString;



    MessagesKeys(String string) {

        this.toString = string;

    }



    public String getString() {

        return this.toString;

    }



}
