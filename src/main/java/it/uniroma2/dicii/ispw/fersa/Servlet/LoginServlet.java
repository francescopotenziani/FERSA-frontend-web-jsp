package it.uniroma2.dicii.ispw.fersa.Servlet;

import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Controller.UserController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

import static it.uniroma2.dicii.ispw.fersa.Share.BundleSingleton.getMyBundle;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        userBean.setUsername(req.getParameter("username"));
        userBean.setPassword(req.getParameter("password"));
        ResourceBundle bundle = getMyBundle();
        UserController controller = new UserController();
        userBean = controller.Login(userBean);

        if (userBean == null) {
            req.setAttribute("alertMsg", getMyBundle().getString("login.error"));
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.include(req, resp);
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute("currentSessionUser", userBean);
            resp.sendRedirect("userPanel.jsp");
        }
    }
}
