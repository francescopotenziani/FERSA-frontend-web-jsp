package it.uniroma2.dicii.ispw.fersa.Controller;

import it.uniroma2.dicii.ispw.fersa.Bean.*;
import it.uniroma2.dicii.ispw.fersa.Entity.*;
import it.uniroma2.dicii.ispw.fersa.Share.Mailer;
import it.uniroma2.dicii.ispw.fersa.Share.ThreadMailer;
import org.joda.time.LocalDateTime;

import java.sql.SQLException;
import java.util.ArrayList;

import static it.uniroma2.dicii.ispw.fersa.DAO.ApartmentDAO.findApartmentByID;
import static it.uniroma2.dicii.ispw.fersa.DAO.BedDao.findBedByID;
import static it.uniroma2.dicii.ispw.fersa.DAO.BedDao.findRoomIdByBedId;
import static it.uniroma2.dicii.ispw.fersa.DAO.ContractDAO.*;
import static it.uniroma2.dicii.ispw.fersa.DAO.RentableDao.findRentableFeeByID;
import static it.uniroma2.dicii.ispw.fersa.DAO.RoomDao.findApartmentIDfromRoomID;
import static it.uniroma2.dicii.ispw.fersa.DAO.RoomDao.findById;

public class ContractUpdateController {

    public ContractUpdateController() {
    }

    public ArrayList<ContractBean> findContract(UserBean userbean) throws SQLException {
        User user = new User(userbean.getUsername(), userbean.getPassword(), userbean.getName(), userbean.getSurname(),
                userbean.getCf(), userbean.getResidence(), userbean.getBirth(), userbean.getBirthPlace(),userbean.getEmail());

        ArrayList<Contract> contractList = findContractByUser(user);

        for (Contract c : contractList) {
            findRentableTypeByContract(c);
            if (c.getTimeToExpire() != null) {
                if (LocalDateTime.now().isAfter(c.getTimeToExpire())) {
                    c.setState(StateEnum.UNAPPROVED);
                    c.setTimeToExpire(null);
                    updateContractByContract(c);

                }
            }

            if (c.getRentable() instanceof Apartment) {
                c.setRentable(findApartmentByID(c.getRentable().getId(), c.getLessor()));
            } else if (c.getRentable() instanceof Room) {
                Integer apt_ID = findApartmentIDfromRoomID(c.getRentable().getId());
                Apartment apt = findApartmentByID(apt_ID, c.getLessor());
                c.setRentable(findById(c.getRentable().getId(), apt));
            } else {
                Integer room_ID = findRoomIdByBedId(c.getRentable().getId());
                Integer apt_ID = findApartmentIDfromRoomID(room_ID);
                Apartment apt = findApartmentByID(apt_ID, c.getLessor());
                Room room = (Room) findById(room_ID, apt);
                c.setRentable(findBedByID(c.getRentable().getId(), room));
                System.out.println("CICCIO1");
            }
        }
        ArrayList<ContractBean> contractBeans = new ArrayList<>();

        for (Contract c : contractList) {
            ContractBean cb = new ContractBean();
            UserBean lessorBean = userToUserbean(c.getLessor());
            UserBean renterBean = userToUserbean(c.getRenter());

            cb.setLessor(lessorBean);
            cb.setRenter(renterBean);
            cb.setStart_date(c.getStart_date());
            cb.setEnd_date(c.getEnd_date());
            cb.setState(c.getState());

            if (c.getRentable() instanceof Apartment) {
                ApartmentBean rentableBean = new ApartmentBean();
                rentableBean.setId(c.getRentable().getId());
                rentableBean.setRentalFee((((Apartment) c.getRentable()).getRentalFee()));
                rentableBean.setRentType(((Apartment) c.getRentable()).getRentType());
                rentableBean.setDishWasher(((Apartment) c.getRentable()).getDishWasher());
                rentableBean.setDryer(((Apartment) c.getRentable()).getDryer());
                rentableBean.setWifi(((Apartment) c.getRentable()).getWifi());
                rentableBean.setWashingMachine(((Apartment) c.getRentable()).getWashingMachine());
                rentableBean.setFurnished(((Apartment) c.getRentable()).getFurnished());
                rentableBean.setShared_room_space(((Apartment) c.getRentable()).getShared_room_space());
                rentableBean.setTv(((Apartment) c.getRentable()).getTv());
                rentableBean.setAirConditioning(((Apartment) c.getRentable()).getAirconditioning());
                rentableBean.setAddress(((Apartment) c.getRentable()).getAddress());
                rentableBean.setCondominiumFee(((Apartment) c.getRentable()).getCondominiumFee());
                rentableBean.setHeating_type(((Apartment) c.getRentable()).getHeating_type());
                rentableBean.setInsert_date(((Apartment) c.getRentable()).getInsert_date());
                rentableBean.setLessor(userToUserbean(c.getLessor()));
                rentableBean.setUtilityBills_Type(((Apartment) c.getRentable()).getUtilityBillsType());
                cb.setRentable(rentableBean);

            } else if (c.getRentable() instanceof Room) {
                RoomBean roomBean = new RoomBean();
                roomBean.setRoomID(c.getRentable().getId());
                roomBean.setRoomFee(c.getRentable().getFee());
                roomBean.setRoomNumber(((Room) c.getRentable()).getRoomNumber());
                ApartmentBean apartmentBean = new ApartmentBean();
                apartmentBean.setId(((Room) c.getRentable()).getApt().getId());
                apartmentBean.setRentalFee((((Room) c.getRentable()).getApt().getRentalFee()));
                apartmentBean.setRentType(((Room) c.getRentable()).getApt().getRentType());
                apartmentBean.setDishWasher(((Room) c.getRentable()).getApt().getDishWasher());
                apartmentBean.setDryer(((Room) c.getRentable()).getApt().getDryer());
                apartmentBean.setWifi(((Room) c.getRentable()).getApt().getWifi());
                apartmentBean.setWashingMachine(((Room) c.getRentable()).getApt().getWashingMachine());
                apartmentBean.setFurnished(((Room) c.getRentable()).getApt().getFurnished());
                apartmentBean.setShared_room_space(((Room) c.getRentable()).getApt().getShared_room_space());
                apartmentBean.setTv(((Room) c.getRentable()).getApt().getTv());
                apartmentBean.setAirConditioning(((Room) c.getRentable()).getApt().getAirconditioning());
                apartmentBean.setAddress(((Room) c.getRentable()).getApt().getAddress());
                apartmentBean.setCondominiumFee(((Room) c.getRentable()).getApt().getCondominiumFee());
                apartmentBean.setHeating_type(((Room) c.getRentable()).getApt().getHeating_type());
                apartmentBean.setInsert_date(((Room) c.getRentable()).getApt().getInsert_date());
                apartmentBean.setLessor(userToUserbean(c.getLessor()));
                apartmentBean.setUtilityBills_Type(((Room) c.getRentable()).getApt().getUtilityBillsType());
                roomBean.setApt(apartmentBean);
                cb.setRentable(roomBean);

            } else if (c.getRentable() instanceof Bed) {
                BedBean bedBean = new BedBean();
                RoomBean roomBean = new RoomBean();
                roomBean.setRoomID(((Bed) c.getRentable()).getRoom().getRoomID());
                ApartmentBean apartmentBean = new ApartmentBean();
                apartmentBean.setId(((Bed) c.getRentable()).getRoom().getApt().getId());
                apartmentBean.setRentalFee((((Bed) c.getRentable()).getRoom().getApt().getRentalFee()));
                apartmentBean.setRentType(((Bed) c.getRentable()).getRoom().getApt().getRentType());
                apartmentBean.setDishWasher(((Bed) c.getRentable()).getRoom().getApt().getDishWasher());
                apartmentBean.setDryer(((Bed) c.getRentable()).getRoom().getApt().getDryer());
                apartmentBean.setWashingMachine(((Bed) c.getRentable()).getRoom().getApt().getWashingMachine());
                apartmentBean.setFurnished(((Bed) c.getRentable()).getRoom().getApt().getFurnished());
                apartmentBean.setShared_room_space(((Bed) c.getRentable()).getRoom().getApt().getShared_room_space());
                apartmentBean.setTv(((Bed) c.getRentable()).getRoom().getApt().getTv());
                apartmentBean.setAirConditioning(((Bed) c.getRentable()).getRoom().getApt().getAirconditioning());
                apartmentBean.setAddress(((Bed) c.getRentable()).getRoom().getApt().getAddress());
                apartmentBean.setCondominiumFee(((Bed) c.getRentable()).getRoom().getApt().getCondominiumFee());
                apartmentBean.setHeating_type(((Bed) c.getRentable()).getRoom().getApt().getHeating_type());
                apartmentBean.setInsert_date(((Bed) c.getRentable()).getRoom().getApt().getInsert_date());
                apartmentBean.setLessor(userToUserbean(c.getLessor()));
                apartmentBean.setUtilityBills_Type(((Bed) c.getRentable()).getRoom().getApt().getUtilityBillsType());
                roomBean.setApt(apartmentBean);
                roomBean.setRoomNumber(((Bed) c.getRentable()).getRoom().getRoomNumber());
                bedBean.setRoom(roomBean);
                bedBean.setBedFee(((Bed) c.getRentable()).getBedFee());
                bedBean.setBedID(((Bed) c.getRentable()).getBedID());
                bedBean.setRoomID(((Bed) c.getRentable()).getRoomID());
                bedBean.setBedNumber(((Bed) c.getRentable()).getBedNumber());
                cb.setRentable(bedBean);
            }
            contractBeans.add(cb);
        }
        return contractBeans;
    }

    public Boolean checkForContract(Integer idBed) {

        return findContractByBedID(idBed);
    }

    public ContractBean findContract(RentableBean rentableBean) throws SQLException {
        Rentable rentable = new Rentable(rentableBean.getId());
        Contract contract = findContractByRentable(rentable);
        ContractBean contractBean = new ContractBean();

        contractBean.setRentable(rentableBean);
        contractBean.setState(contract.getState());
        contractBean.setLessor(userToUserbean(contract.getLessor()));
        contractBean.setRenter(userToUserbean(contract.getRenter()));
        contractBean.setStart_date(contract.getStart_date());
        contractBean.setEnd_date(contract.getEnd_date());
        return contractBean;
    }

    public ContractBean findContract(Integer id) throws SQLException {
        Rentable rentable = new Rentable(id);
        Contract contract = findContractByRentable(rentable);
        ContractBean contractBean = new ContractBean();
        contractBean.setState(contract.getState());
        contractBean.setLessor(userToUserbean(contract.getLessor()));
        contractBean.setRenter(userToUserbean(contract.getRenter()));
        contractBean.setStart_date(contract.getStart_date());
        contractBean.setEnd_date(contract.getEnd_date());
        return contractBean;
    }

    public void updateContract(ContractBean c) {
        LocalDateTime localDateTime = new LocalDateTime();
        localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusDays(7);

        User lessor = new User(c.getLessor().getUsername(), c.getLessor().getPassword(), c.getLessor().getName(), c.getLessor().getSurname(),
                c.getLessor().getCf(), c.getLessor().getResidence(), c.getLessor().getBirth(), c.getLessor().getBirthPlace(),c.getLessor().getEmail());
        Rentable rentable = new Rentable(c.getRentable().getId(), c.getRentable().getFee());
        User renter = new User(c.getRenter().getUsername(), c.getRenter().getPassword(), c.getRenter().getName(), c.getRenter().getSurname(),
                c.getRenter().getCf(), c.getRenter().getResidence(), c.getRenter().getBirth(), c.getRenter().getBirthPlace(),c.getRenter().getEmail());
        Contract contract = new Contract(lessor, rentable, renter, c.getStart_date(), c.getEnd_date(), c.getPdfContract(), c.getState(), localDateTime);
        updateContractByContract(contract);
        ThreadMailer t = new ThreadMailer(contract,this);
        Thread thread = new Thread(t);
        thread.start();
    }

    public boolean checkContract(ContractBean c) {
        User lessor = new User(c.getLessor().getUsername(), c.getLessor().getPassword(), c.getLessor().getName(), c.getLessor().getSurname(),
                c.getLessor().getCf(), c.getLessor().getResidence(), c.getLessor().getBirth(), c.getLessor().getBirthPlace(),c.getLessor().getEmail());
        Rentable rentable = new Rentable(c.getRentable().getId(), c.getRentable().getFee());
        User renter = new User(c.getRenter().getUsername(), c.getRenter().getPassword(), c.getRenter().getName(), c.getRenter().getSurname(),
                c.getRenter().getCf(), c.getRenter().getResidence(), c.getRenter().getBirth(), c.getRenter().getBirthPlace(),c.getRenter().getEmail());
        Contract contract = new Contract(lessor, rentable, renter, c.getStart_date(), c.getEnd_date(), c.getPdfContract(), c.getState(), null);
        LocalDateTime timeToExpire = findTimeToExpireByRentableID(c.getRentable().getId());
        if (timeToExpire != null) {
            if (LocalDateTime.now().isAfter(timeToExpire)) {
                contract.setState(StateEnum.UNAPPROVED);
                c.setState(StateEnum.UNAPPROVED);
                updateContractByContract(contract);
                return true;
            }
        }
        return false;
    }

    public void sendEditoContractEmail(Contract contract) {
        Mailer mailer = Mailer.getInstance();
//        User less = new User(c.getLessor().getUsername(), c.getLessor().getPassword(), c.getLessor().getName(), c.getLessor().getSurname(),
//                c.getLessor().getCf(), c.getLessor().getResidence(), c.getLessor().getBirth(), c.getLessor().getBirthPlace(),c.getLessor().getEmail());
//        Rentable rent = new Rentable(c.getRentable().getId(), c.getRentable().getFee());
//        User renter = new User(c.getRenter().getUsername(), c.getRenter().getPassword(), c.getRenter().getName(), c.getRenter().getSurname(),
//                c.getRenter().getCf(), c.getRenter().getResidence(), c.getRenter().getBirth(), c.getRenter().getBirthPlace(),c.getRenter().getEmail());
//        Contract contract = new Contract(less, rent, renter, c.getStart_date(), c.getEnd_date(), c.getPdfContract(), c.getState(), null);

        findRentableTypeByContract(contract);
        if (contract.getRentable() instanceof Apartment) {
            contract.setRentable(findApartmentByID(contract.getRentable().getId(), contract.getLessor()));
        } else if (contract.getRentable() instanceof Room) {
            Integer apt_ID = findApartmentIDfromRoomID(contract.getRentable().getId());
            Apartment apt = findApartmentByID(apt_ID, contract.getLessor());
            contract.setRentable(findById(contract.getRentable().getId(), apt));
        } else {
            Integer room_ID = findRoomIdByBedId(contract.getRentable().getId());
            Integer apt_ID = findApartmentIDfromRoomID(room_ID);
            Apartment apt = findApartmentByID(apt_ID, contract.getLessor());
            Room room = (Room) findById(room_ID, apt);
            contract.setRentable(findBedByID(contract.getRentable().getId(), room));
        }
        if (!mailer.sendEditContractEmail(contract)) {
            mailer.sendEditContractEmail(contract);
        }
    }

    public UserBean userToUserbean(User user){
        UserBean userBean = new UserBean();
        userBean.setUsername(user.getUsername());
        userBean.setName(user.getName());
        userBean.setSurname(user.getSurname());
        userBean.setCf(user.getCf());
        userBean.setBirth(user.getBirth());
        userBean.setBirthPlace(user.getBirthPlace());
        userBean.setResidence(user.getResidence());
        userBean.setEmail(user.getEmail());
        return userBean;
    }

    public Integer findRentableFee (Integer id){
        return findRentableFeeByID(id);
    }
}
