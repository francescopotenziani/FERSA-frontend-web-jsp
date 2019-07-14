package it.uniroma2.dicii.ispw.fersa.Servlet.contractServlet;

import it.uniroma2.dicii.ispw.fersa.Bean.ContractBean;
import it.uniroma2.dicii.ispw.fersa.Bean.RentableBean;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Controller.ContractDeleteController;
import it.uniroma2.dicii.ispw.fersa.Controller.ContractUpdateController;
import it.uniroma2.dicii.ispw.fersa.Entity.Contract;
import it.uniroma2.dicii.ispw.fersa.Entity.StateEnum;
import javafx.scene.control.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static it.uniroma2.dicii.ispw.fersa.Share.AlertHelper.showAlert;
import static it.uniroma2.dicii.ispw.fersa.Share.BundleSingleton.getMyBundle;

public class ContractEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("currentSessionUser");
        ResourceBundle bundle =(ResourceBundle) session.getAttribute("bundle");
        ContractUpdateController contractUpdateController = (ContractUpdateController) session.getAttribute("contractController");
        String page="";
        if (req.getParameter("editButton") != null){
            try {
                Integer rentableId = Integer.parseInt(req.getParameter("rentableid"));
                ContractBean contractBean = contractUpdateController.findContract(rentableId);
                Integer rentableFee = contractUpdateController.findRentableFee(rentableId);
                RentableBean rentableBean = new RentableBean();
                rentableBean.setFee(rentableFee);
                rentableBean.setId(rentableId);
                contractBean.setRentable(rentableBean);
                if (contractBean.getState() != StateEnum.UNAPPROVED){
                    session.setAttribute("alertMsg", bundle.getString("list.contract.already.approved"));
                    page="contractPanel.jsp";
                }else{

                session.setAttribute("contractSelected",contractBean);
                page="contractEdit.jsp";
                }
            } catch (NullPointerException e){
                e.printStackTrace();
                session.setAttribute("alertMsg", bundle.getString("contract.not.selected"));
                page="contractPanel.jsp";
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NumberFormatException e){
                session.setAttribute("alertMsg", bundle.getString("contract.not.selected"));
                page="contractPanel.jsp";
            }

        } else{
            //delete button is clicked
            try {
                Integer rentableId = Integer.parseInt(req.getParameter("rentableid"));
                ContractBean contractBean = contractUpdateController.findContract(rentableId);
                RentableBean rentableBean = new RentableBean();
                rentableBean.setId(rentableId);
                contractBean.setRentable(rentableBean);
                if (contractBean.getState() == StateEnum.UNAPPROVED){
                    ContractDeleteController controllerEditContract = new ContractDeleteController();
                    if (controllerEditContract.deleteContract(contractBean)){
                        session.setAttribute("alertMsg", bundle.getString("contract.delete.success"));
                        page="/contractPanelServlet";
                    } else{
                        session.setAttribute("alertMsg", bundle.getString("contract.delete.error"));
                        page="contractPanel.jsp";
                    }

                } else{
                    session.setAttribute("alertMsg", bundle.getString("list.contract.already.approved"));
                    page="contractPanel.jsp";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }  catch (NumberFormatException e){
                session.setAttribute("alertMsg", bundle.getString("contract.not.selected"));
                page="contractPanel.jsp";
            }
        }
        req.getRequestDispatcher(page).forward(req,resp);
    }
}
