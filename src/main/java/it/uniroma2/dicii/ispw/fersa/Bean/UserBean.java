package it.uniroma2.dicii.ispw.fersa.Bean;

import java.sql.Date;

public class UserBean {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String cf;
    private String residence;
    private Date birth;
    private String birthPlace;
    private String email;


    public void setUsername(String user) {
        this.username = user;
    }
    public String getUsername() {
        return this.username;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }
    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setSurname(String cogn) {
        this.surname = cogn;
    }
    public String getSurname() { return this.surname; }

    public String getCf() { return cf; }
    public void setCf(String cf) { this.cf = cf; }

    public String getResidence() { return residence; }
    public void setResidence(String residence) { this.residence = residence; }

    public Date getBirth() { return birth; }
    public void setBirth(Date birth) { this.birth = birth; }

    public String getBirthPlace() { return birthPlace; }
    public void setBirthPlace(String birthPlace) { this.birthPlace = birthPlace; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
