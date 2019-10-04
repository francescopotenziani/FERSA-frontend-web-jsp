package it.uniroma2.dicii.ispw.fersa.Controller;

import it.uniroma2.dicii.ispw.fersa.Bean.ContractBean;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Entity.Contract;
import it.uniroma2.dicii.ispw.fersa.Entity.Rentable;
import it.uniroma2.dicii.ispw.fersa.Entity.User;

import static it.uniroma2.dicii.ispw.fersa.DAO.ContractDAO.deleteContractByContract;

public class ContractDeleteController {
    public ContractDeleteController(){}

    public boolean deleteContract(ContractBean c){
        User less = new User(c.getLessor().getUsername(), c.getLessor().getPassword(), c.getLessor().getName(), c.getLessor().getSurname(),
                c.getLessor().getCf(), c.getLessor().getResidence(), c.getLessor().getBirth(), c.getLessor().getBirthPlace(),c.getLessor().getEmail());
        Rentable rent = new Rentable(c.getRentable().getId(), c.getRentable().getFee());
        User renter = new User(c.getRenter().getUsername(), c.getRenter().getPassword(), c.getRenter().getName(), c.getRenter().getSurname(),
                c.getRenter().getCf(), c.getRenter().getResidence(), c.getRenter().getBirth(), c.getRenter().getBirthPlace(),c.getRenter().getEmail());
        Contract contract = new Contract(less, rent, renter, c.getStart_date(), c.getEnd_date(), c.getPdfContract(), c.getState(), null);
        if(deleteContractByContract(contract)){
            return true;
        }else{
            return false;
        }
    }

    public  User userBeanToUser(UserBean userBean){
        User user = new User(userBean.getUsername(),userBean.getName(),userBean.getSurname(),userBean.getCf(),userBean.getResidence(),userBean.getBirth(),userBean.getBirthPlace(),userBean.getEmail());
        return user;
    }
}
