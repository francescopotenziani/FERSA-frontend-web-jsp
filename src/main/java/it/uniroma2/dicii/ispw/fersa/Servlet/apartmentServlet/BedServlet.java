package it.uniroma2.dicii.ispw.fersa.Servlet.apartmentServlet;

import it.uniroma2.dicii.ispw.fersa.Bean.ApartmentBean;
import it.uniroma2.dicii.ispw.fersa.Bean.BedBean;
import it.uniroma2.dicii.ispw.fersa.Controller.ApartmentController;
import it.uniroma2.dicii.ispw.fersa.Controller.ContractUpdateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        ApartmentController apartmentController = (ApartmentController) session.getAttribute("apartmentController");
        ApartmentBean apartmentBean = (ApartmentBean)(session.getAttribute("apartmentSelected"));
        ResourceBundle bundle =(ResourceBundle) session.getAttribute("bundle");

        String page="";
        if (req.getParameter("addButton") != null) {
            try {
                Integer bedId = Integer.parseInt(req.getParameter("bedBeanId"));
                BedBean bedBean = apartmentController.findBed(bedId,apartmentBean);
                Integer bedFee = Integer.parseInt(req.getParameter("bedFee"));
                BedBean newBedBean = new BedBean();
                newBedBean.setRoom(bedBean.getRoom());
                newBedBean.setBedNumber(bedBean.getRoom().getBeds().size() + 1);
                newBedBean.setBedFee(bedFee);
                apartmentController.insertBed(newBedBean);
                apartmentController.findRoom(apartmentBean);
                apartmentController.findBeds(apartmentBean);
                req.setAttribute("alertMsg", bundle.getString("add.bed.success"));
                page = "apartmentEdit.jsp";
            } catch (NumberFormatException ne) {
                req.setAttribute("alertMsg", bundle.getString("add.bed.error"));
                page = "apartmentEdit.jsp";
            } catch (NullPointerException e) {
                req.setAttribute("alertMsg", bundle.getString("add.bed.error.select.room"));
                page = "apartmentEdit.jsp";
            }
        }else if (req.getParameter("deleteButton") != null) {
            //delete button is clicked
            try{
                int bedId = Integer.parseInt(req.getParameter("bedBeanId"));
                BedBean bedBean = apartmentController.findBed(bedId,apartmentBean);
                ContractUpdateController contractController = new ContractUpdateController();
                if(bedBean.getRoom().getBeds().size()<=1){
                    req.setAttribute("alertMsg", bundle.getString("list.room.invalid.selection"));
                    page = "apartmentEdit.jsp";
                } else if( contractController.checkForContract(bedId)) {
                    req.setAttribute("alertMsg", bundle.getString("list.room.bed.with.contract"));
                    page = "apartmentEdit.jsp";
                } else{
                    apartmentController.deleteBed(bedBean);
                    req.setAttribute("alertMsg", bundle.getString("delete.bed.suucess"));
                    apartmentController.findRoom(apartmentBean);
                    apartmentController.findBeds(apartmentBean);
                    page = "apartmentEdit.jsp";
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                req.setAttribute("alertMsg", bundle.getString("add.bed.error.select.room"));
                page = "apartmentEdit.jsp";
            }
        } else{
            try {
                apartmentBean.setFee(Integer.parseInt(req.getParameter("rentalFee")));
                apartmentBean.setCondominiumFee(Integer.parseInt(req.getParameter("condominiumFee")));
                if (req.getParameter("airConditioning") != null){
                apartmentBean.setAirConditioning(Boolean.valueOf(req.getParameter("airConditioning")));
                } else{
                    apartmentBean.setAirConditioning(false);
                }
                if (req.getParameter("tv") != null){
                    apartmentBean.setTv(Boolean.valueOf(req.getParameter("tv")));
                } else{
                    apartmentBean.setTv(false);
                }
                if (req.getParameter("sharedRoom") != null){
                    apartmentBean.setShared_room_space(Boolean.valueOf(req.getParameter("sharedRoom")));
                } else{
                    apartmentBean.setShared_room_space(false);
                }
                if(req.getParameter("wifi") != null){
                    apartmentBean.setWifi(Boolean.valueOf(req.getParameter("wifi")));
                } else{
                    apartmentBean.setWifi(false);
                }
                if (req.getParameter("furnished") != null){
                    apartmentBean.setFurnished(Boolean.valueOf(req.getParameter("furnished")));
                }else{
                    apartmentBean.setFurnished(false);
                }
                if(req.getParameter("washingMachine") != null){
                    apartmentBean.setWashingMachine(Boolean.valueOf(req.getParameter("washingMachine")));
                } else{
                    apartmentBean.setWashingMachine(false);
                }
                if (req.getParameter("dryer") != null){
                    apartmentBean.setDryer(Boolean.valueOf(req.getParameter("dryer")));
                } else{
                    apartmentBean.setDryer(false);
                }
                if (req.getParameter("dishWasher") != null){
                    apartmentBean.setDishWasher(Boolean.valueOf(req.getParameter("dishWasher")));
                } else{
                    apartmentBean.setDishWasher(false);
                }

                if(Integer.parseInt(req.getParameter("heating")) == 1){
                    apartmentBean.setHeating_type("apartmentEdit.heatingType.Indipendent");
                }else{
                    apartmentBean.setHeating_type("apartmentEdit.heatingType.Shared");
                }

                if (Integer.parseInt(req.getParameter("utilityBills")) == 1){
                    apartmentBean.setUtilityBills_Type("apartmentEdit.condominiumFee.Included");
                } else{
                    apartmentBean.setUtilityBills_Type("apartmentEdit.condominiumFee.NotIncluded");
                }

                if(Integer.parseInt(req.getParameter("rentType")) == 1){
                    apartmentBean.setRentType("apartmentEdit.rentType.allApartmentRent");
                } else if(Integer.parseInt(req.getParameter("rentType")) == 2){
                    apartmentBean.setRentType("apartmentEdit.rentType.roomRent");
                } else{
                    apartmentBean.setRentType("apartmentEdit.rentType.notSpecified");
                }
                apartmentController.updateApartment(apartmentBean);
                page = "/apartmentPanelServlet";
            }catch (NumberFormatException e){
                req.setAttribute("alertMsg", bundle.getString("submit.error"));
            }
        }
        req.getRequestDispatcher(page).forward(req,resp);
    }
}
