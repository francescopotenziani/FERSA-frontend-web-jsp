package it.uniroma2.dicii.ispw.fersa.Servlet.apartmentServlet;

import it.uniroma2.dicii.ispw.fersa.Bean.ApartmentBean;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Controller.ApartmentController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;
import java.util.ResourceBundle;

public class ApartmentPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("currentSessionUser");
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
        ApartmentController apartmentController = new ApartmentController();
        session.setAttribute("apartmentController",apartmentController);
        List<ApartmentBean> apartments = apartmentController.findApartments(userBean);
        req.setAttribute("apartments", apartments);
        req.getRequestDispatcher("apartmentPanel.jsp").forward(req,resp);
    }
}
