package it.uniroma2.dicii.ispw.fersa.Servlet.contractServlet;


import it.uniroma2.dicii.ispw.fersa.Bean.*;
import it.uniroma2.dicii.ispw.fersa.Controller.ContractUpdateController;
import it.uniroma2.dicii.ispw.fersa.Entity.Bed;
import org.apache.commons.collections4.BidiMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ContractPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("currentSessionUser");
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
        ContractUpdateController contractUpdateController = new ContractUpdateController();
        session.setAttribute("contractController",contractUpdateController);
        List<ContractBean> contractBeanList = null;
        try {
            contractBeanList = contractUpdateController.findContract(userBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContractListTypeVisitor visitor = new ContractListTypeVisitor();
        for (ContractBean contractBean: contractBeanList) {
           contractBean.getRentable().accept(visitor);
        }
        BidiMap<ContractBean,ApartmentBean> apartmentContractList = visitor.getApartmentContractList();
        BidiMap<ContractBean,RoomBean> roomContractList = visitor.getRoomContractList();
        BidiMap<ContractBean,BedBean> bedContractList = visitor.getBedContractList();
        session.setAttribute("apartmentContracts", apartmentContractList);
        session.setAttribute("roomContracts", roomContractList);
        session.setAttribute("bedContracts", bedContractList);
        req.getRequestDispatcher("contractPanel.jsp").forward(req,resp);
    }
}
