package it.uniroma2.dicii.ispw.fersa.Servlet.contractServlet;

import it.uniroma2.dicii.ispw.fersa.Bean.ContractBean;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Controller.ContractUpdateController;
import it.uniroma2.dicii.ispw.fersa.Entity.StateEnum;
import javafx.scene.control.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static it.uniroma2.dicii.ispw.fersa.Share.AlertHelper.showAlert;
import static it.uniroma2.dicii.ispw.fersa.Share.BundleSingleton.getMyBundle;

public class ContractUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("currentSessionUser");
        ResourceBundle bundle =(ResourceBundle) session.getAttribute("bundle");
        ContractUpdateController contractUpdateController = (ContractUpdateController) session.getAttribute("contractController");
        LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));
        Integer rentalFee = Integer.parseInt(req.getParameter("rentalFee"));
        ContractBean contractSelected =(ContractBean) session.getAttribute("contractSelected");
        String page = "";
        if (endDate.isBefore(startDate)){
            req.setAttribute("alertMsg", bundle.getString("contract.edit.date.error"));
            page = "contractEdit.jsp";
        }else{
            if (endDate.isBefore(LocalDateTime.now().toLocalDate()) | startDate.isBefore(LocalDateTime.now().toLocalDate())){
                req.setAttribute("alertMsg", bundle.getString("contract.edit.date.error"));
                page = "contractEdit.jsp";
            } else{
                contractSelected.setStart_date(startDate);
                contractSelected.setEnd_date(endDate);
                contractSelected.getRentable().setFee(rentalFee);
                contractSelected.setState(StateEnum.WAITING);
                contractUpdateController.updateContract(contractSelected);
                page="/contractPanelServlet";
            }
        }
        req.getRequestDispatcher(page).forward(req,resp);
    }
}
