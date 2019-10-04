package it.uniroma2.dicii.ispw.fersa.Controller;

import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Entity.User;

import static it.uniroma2.dicii.ispw.fersa.DAO.UserDAO.findUserByUsernameAndPassword;

public class UserController {

    public UserController(){}

    public UserBean Login(UserBean userBean){
        User u = findUserByUsernameAndPassword(userBean.getUsername(),userBean.getPassword());
        if (u == null){
            userBean = null;
        } else{
            userBean.setName(u.getName());
            userBean.setSurname(u.getSurname());
            userBean.setUsername(u.getUsername());
            userBean.setCf(u.getCf());
            userBean.setResidence(u.getResidence());
            userBean.setBirth(u.getBirth());
            userBean.setBirthPlace(u.getBirthPlace());
            userBean.setEmail(u.getEmail());
        }
        return userBean;
    }
}
