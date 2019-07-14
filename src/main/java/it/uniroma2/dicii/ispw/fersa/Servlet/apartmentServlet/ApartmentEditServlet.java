package it.uniroma2.dicii.ispw.fersa.Servlet.apartmentServlet;

import it.uniroma2.dicii.ispw.fersa.Bean.ApartmentBean;
import it.uniroma2.dicii.ispw.fersa.Bean.RoomBean;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Controller.ApartmentController;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class ApartmentEditServlet extends HttpServlet {
    private ApartmentBean apartmentSelected;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("currentSessionUser");
        ResourceBundle bundle =(ResourceBundle) session.getAttribute("bundle");
        ApartmentController apartmentController = (ApartmentController) session.getAttribute("apartmentController");
        Integer apartmentID = Integer.valueOf(req.getParameter("apartmentId"));
        apartmentSelected =  apartmentController.findApartment(apartmentID,userBean);

        Integer bedsNumber = 0;
        req.setAttribute("bedsNumber",bedsNumber);
        session.setAttribute("apartmentSelected",apartmentSelected);
        resp.sendRedirect("apartmentEdit.jsp");
//        req.getRequestDispatcher("apartmentEdit.jsp").include(req,resp);
    }
}
