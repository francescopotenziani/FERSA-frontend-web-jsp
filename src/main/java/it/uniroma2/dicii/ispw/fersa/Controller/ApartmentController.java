package it.uniroma2.dicii.ispw.fersa.Controller;

import it.uniroma2.dicii.ispw.fersa.Bean.ApartmentBean;
import it.uniroma2.dicii.ispw.fersa.Bean.BedBean;
import it.uniroma2.dicii.ispw.fersa.Bean.RoomBean;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Entity.*;

import java.util.ArrayList;

import static it.uniroma2.dicii.ispw.fersa.DAO.ApartmentDAO.*;
import static it.uniroma2.dicii.ispw.fersa.DAO.BedDao.*;
import static it.uniroma2.dicii.ispw.fersa.DAO.RoomDao.*;

public class ApartmentController {

    public ApartmentController(){}

    public ArrayList<ApartmentBean> findApartments(UserBean userbean){

        User user = new User(userbean.getUsername(),userbean.getPassword(),userbean.getName(),userbean.getSurname(),
                userbean.getCf(),userbean.getResidence(),userbean.getBirth(),userbean.getBirthPlace(),userbean.getEmail());

        ArrayList<Apartment> list = findApartmentByUser(user);
        ArrayList<ApartmentBean> listBean= new ArrayList<>();
        for(Apartment apt: list){
            ApartmentBean aptB = new ApartmentBean();
            UserBean userBean = userToUserbean(apt.getLessor());
            aptB.setId(apt.getId());
            aptB.setLessor(userBean);
            aptB.setAddress(apt.getAddress());
            aptB.setInsert_date(apt.getInsert_date());
            aptB.setFurnished(apt.getFurnished());
            aptB.setHeating_type(apt.getHeating_type());
            aptB.setWifi(apt.getWifi());
            aptB.setShared_room_space(apt.getShared_room_space());
            aptB.setTv(apt.getTv());
            aptB.setAirConditioning(apt.getAirconditioning());
            aptB.setWashingMachine(apt.getWashingMachine());
            aptB.setDryer(apt.getDryer());
            aptB.setDishWasher(apt.getDishWasher());
            aptB.setUtilityBills_Type(apt.getUtilityBillsType());
            aptB.setRentalFee(apt.getRentalFee());
            aptB.setCondominiumFee(apt.getCondominiumFee());
            aptB.setRentType(apt.getRentType());
            findRoom(aptB);
            findBeds(aptB);
            listBean.add(aptB);
        }
        return listBean;
    }

    public ApartmentBean findApartment(Integer apartmentId,UserBean userbean){
        User user = new User(userbean.getUsername(),userbean.getPassword(),userbean.getName(),userbean.getSurname(),
                userbean.getCf(),userbean.getResidence(),userbean.getBirth(),userbean.getBirthPlace(),userbean.getEmail());
        Apartment apt = findApartmentByID(apartmentId,user);
        ApartmentBean aptB = new ApartmentBean();
        UserBean userBean = userToUserbean(apt.getLessor());
        aptB.setId(apt.getId());
        aptB.setLessor(userBean);
        aptB.setAddress(apt.getAddress());
        aptB.setInsert_date(apt.getInsert_date());
        aptB.setFurnished(apt.getFurnished());
        aptB.setHeating_type(apt.getHeating_type());
        aptB.setWifi(apt.getWifi());
        aptB.setShared_room_space(apt.getShared_room_space());
        aptB.setTv(apt.getTv());
        aptB.setAirConditioning(apt.getAirconditioning());
        aptB.setWashingMachine(apt.getWashingMachine());
        aptB.setDryer(apt.getDryer());
        aptB.setDishWasher(apt.getDishWasher());
        aptB.setUtilityBills_Type(apt.getUtilityBillsType());
        aptB.setRentalFee(apt.getRentalFee());
        aptB.setCondominiumFee(apt.getCondominiumFee());
        aptB.setRentType(apt.getRentType());
        findRoom(aptB);
        findBeds(aptB);
        return aptB;

    }

    public void updateApartment(ApartmentBean aptBean){
        Apartment apt = new Apartment(aptBean.getId(), userBeanToUser(aptBean.getLessor()),aptBean.getAddress(),aptBean.getInsert_date(),
                aptBean.getFurnished(),aptBean.getHeating_type(),aptBean.getWifi(),aptBean.getShared_room_space(),
                aptBean.getTv(),aptBean.getAirConditioning(),aptBean.getWashingMachine(),aptBean.getDryer(),
                aptBean.getDishWasher(),aptBean.getRentalFee(),aptBean.getCondominiumFee(),aptBean.getUtilityBillsType(),
                aptBean.getRentType());
        updateApartmentByID(apt);

        for (RoomBean roomBean: aptBean.getRooms()){
            Room room = new Room(apt, roomBean.getRoomID(), roomBean.getRoomFee(), roomBean.getRoomNumber());
            updateRoomByRoom(room);
        }
        for (BedBean bedBean: aptBean.getBeds()){
            Bed bed = new Bed(bedBean.getRoom().getRoomID(),bedBean.getBedID(),bedBean.getBedFee(), bedBean.getBedNumber());
            updateBedByBed(bed);
        }
    }

    public void deleteBed(BedBean bedBean){
        Apartment apt = new Apartment(bedBean.getRoom().getApartment().getId(), userBeanToUser(bedBean.getRoom().getApartment().getLessor()),
                bedBean.getRoom().getApartment().getAddress(),bedBean.getRoom().getApartment().getInsert_date(),
                bedBean.getRoom().getApartment().getFurnished(),bedBean.getRoom().getApartment().getHeating_type(),
                bedBean.getRoom().getApartment().getWifi(),bedBean.getRoom().getApartment().getShared_room_space(),
                bedBean.getRoom().getApartment().getTv(),bedBean.getRoom().getApartment().getAirConditioning(),
                bedBean.getRoom().getApartment().getWashingMachine(),bedBean.getRoom().getApartment().getDryer(),
                bedBean.getRoom().getApartment().getDishWasher(),bedBean.getRoom().getApartment().getRentalFee(),
                bedBean.getRoom().getApartment().getCondominiumFee(),bedBean.getRoom().getApartment().getUtilityBillsType(),
                bedBean.getRoom().getApartment().getRentType());
        Bed bed = new Bed(bedBean.getRoomID(),bedBean.getBedID(),bedBean.getBedFee(), bedBean.getBedNumber());
        deleteBedbyBed(bed);
    }

    public void insertBed(BedBean bedBean){
        Apartment apt = new Apartment(bedBean.getRoom().getApartment().getId(), userBeanToUser(bedBean.getRoom().getApartment().getLessor()),
                bedBean.getRoom().getApartment().getAddress(),bedBean.getRoom().getApartment().getInsert_date(),
                bedBean.getRoom().getApartment().getFurnished(),bedBean.getRoom().getApartment().getHeating_type(),
                bedBean.getRoom().getApartment().getWifi(),bedBean.getRoom().getApartment().getShared_room_space(),
                bedBean.getRoom().getApartment().getTv(),bedBean.getRoom().getApartment().getAirConditioning(),
                bedBean.getRoom().getApartment().getWashingMachine(),bedBean.getRoom().getApartment().getDryer(),
                bedBean.getRoom().getApartment().getDishWasher(),bedBean.getRoom().getApartment().getRentalFee(),
                bedBean.getRoom().getApartment().getCondominiumFee(),bedBean.getRoom().getApartment().getUtilityBillsType(),
                bedBean.getRoom().getApartment().getRentType());
        Room room = new Room(apt,bedBean.getRoom().getRoomID(),bedBean.getRoom().getRoomFee(), bedBean.getRoom().getRoomNumber());
        Bed bed = new Bed(bedBean.getRoom().getRoomID(),room, bedBean.getBedID(),bedBean.getBedFee(), bedBean.getBedNumber());
        bed.setBedID(insertBedbyBed(bed));
    }

    public void updateBedsAndRoomsApartment(ApartmentBean apartmentBean){
        findRoom(apartmentBean);
        findBeds(apartmentBean);
    }

    public void findRoom(ApartmentBean aptBean){
        Apartment apt = new Apartment(aptBean.getId(), userBeanToUser(aptBean.getLessor()),aptBean.getAddress(),aptBean.getInsert_date(),
                aptBean.getFurnished(),aptBean.getHeating_type(),aptBean.getWifi(),aptBean.getShared_room_space(),
                aptBean.getTv(),aptBean.getAirConditioning(),aptBean.getWashingMachine(),aptBean.getDryer(),
                aptBean.getDishWasher(),aptBean.getRentalFee(),aptBean.getCondominiumFee(),aptBean.getUtilityBillsType(),
                aptBean.getRentType());
        ArrayList<Room> rooms = findRoomByApartmentID(apt);
        ArrayList<RoomBean> listRoom = new ArrayList<>();
        for (Room r: rooms){
            RoomBean rBean = new RoomBean();
            rBean.setApt(aptBean);
            rBean.setRoomID(r.getRoomID());
            rBean.setRoomFee(r.getRoomFee());
            rBean.setRoomNumber(r.getRoomNumber());
            listRoom.add(rBean);
        }
        aptBean.setRooms(listRoom);
    }

    public void findBeds(ApartmentBean aptBean){
        Apartment apt = new Apartment(aptBean.getId(), userBeanToUser(aptBean.getLessor()),aptBean.getAddress(),aptBean.getInsert_date(),
                aptBean.getFurnished(),aptBean.getHeating_type(),aptBean.getWifi(),aptBean.getShared_room_space(),
                aptBean.getTv(),aptBean.getAirConditioning(),aptBean.getWashingMachine(),aptBean.getDryer(),
                aptBean.getDishWasher(),aptBean.getRentalFee(),aptBean.getCondominiumFee(),aptBean.getUtilityBillsType(),
                aptBean.getRentType());

        ArrayList<Bed> beds = findBedsByApartmentID(apt);
        ArrayList<BedBean> bedList= new ArrayList<>();
        for (Bed b: beds){
            BedBean bBean = new BedBean();
            bBean.setRoomID(b.getRoomID());
            bBean.setBedID(b.getBedID());
            bBean.setBedFee(b.getBedFee());
            bBean.setBedNumber(b.getBedNumber());
            bedList.add(bBean);
        }
        aptBean.setBeds(bedList);

        for(RoomBean roomBean: aptBean.getRooms()){
            ArrayList<BedBean> bedArray= new ArrayList<>();
            for(BedBean bedBean: aptBean.getBeds()){
                if (roomBean.getRoomID().equals(bedBean.getRoomID())){
                    bedArray.add(bedBean);
                }
            }
            roomBean.setBeds(bedArray);
        }

        for (RoomBean r: aptBean.getRooms()){
            for (BedBean b: bedList){
                if (r.getRoomID().equals(b.getRoomID())){
                    b.setRoom(r);
                }
            }
        }
    }

    public void updateBedsNumber(ArrayList<BedBean> bedList) {
        ArrayList<Bed> beds = new ArrayList<Bed>();
        for (BedBean b: bedList){
            Bed bed = new Bed(b.getRoomID(),b.getBedID(),b.getBedFee(),b.getBedNumber());
            beds.add(bed);
        }
        for (Bed b: beds){
            updateBedNumberByBed(b);
        }
    }

    public BedBean findBed(Integer bedId, ApartmentBean aptBean){
        Integer roomId = findRoomIdByBedId(bedId);
        Apartment apt = new Apartment(aptBean.getId(), userBeanToUser(aptBean.getLessor()),aptBean.getAddress(),aptBean.getInsert_date(),
                aptBean.getFurnished(),aptBean.getHeating_type(),aptBean.getWifi(),aptBean.getShared_room_space(),
                aptBean.getTv(),aptBean.getAirConditioning(),aptBean.getWashingMachine(),aptBean.getDryer(),
                aptBean.getDishWasher(),aptBean.getRentalFee(),aptBean.getCondominiumFee(),aptBean.getUtilityBillsType(),
                aptBean.getRentType());
        Room room = findRoomById(roomId,apt);
        room.setBeds(findBedsbyRoomId(room));
        ArrayList<BedBean> bedBeanArrayList = new ArrayList<>();
        for (Bed b: room.getBeds()){
            BedBean bedBean = new BedBean();
            bedBean.setBedNumber(b.getBedNumber());
            bedBean.setBedFee(b.getBedFee());
            bedBean.setBedID(b.getBedID());
            bedBean.setRoomID(b.getRoomID());
            bedBeanArrayList.add(bedBean);
        }
        RoomBean roomBean = new RoomBean();

        roomBean.setApt(aptBean);
        roomBean.setRoomID(roomId);
        roomBean.setRoomNumber(room.getRoomNumber());
        roomBean.setRoomFee(room.getRoomFee());
        roomBean.setBeds(bedBeanArrayList);

        Bed bed = (Bed) findBedByID(bedId,room);
        BedBean bedBean = new BedBean();
        bedBean.setBedID(bedId);
        bedBean.setBedFee(bed.getBedFee());
        bedBean.setBedNumber(bed.getBedNumber());
        bedBean.setRoom(roomBean);
        return bedBean;
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

    public  User userBeanToUser(UserBean userBean){
        User user = new User(userBean.getUsername(),userBean.getName(),userBean.getSurname(),userBean.getCf(),userBean.getResidence(),userBean.getBirth(),userBean.getBirthPlace(),userBean.getEmail());
        return user;
    }
}
