package it.uniroma2.dicii.ispw.fersa.Entity;

import java.sql.Date;

public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String cf;
    private String residence;
    private Date   birth;
    private String birthPlace;
    private String email;


    public String getCf() { return cf; }

    public String getResidence() { return residence; }

    public Date getBirth() { return birth; }

    public String getBirthPlace() { return birthPlace; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() { return email; }



    public User(String username, String password, String name, String surname, String cf, String residence, Date birth,
                String birth_place, String email) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.residence = residence;
        this.birth = birth;
        this.birthPlace = birth_place;
        this.email = email;
    }

    public User(String username, String name, String surname, String cf, String residence, Date birth,
                String birth_place, String email) {

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.residence = residence;
        this.birth = birth;
        this.birthPlace = birth_place;
        this.email = email;
    }
}
