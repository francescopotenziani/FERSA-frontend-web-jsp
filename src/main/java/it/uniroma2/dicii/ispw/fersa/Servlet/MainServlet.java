package it.uniroma2.dicii.ispw.fersa.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

import static it.uniroma2.dicii.ispw.fersa.Share.BundleSingleton.getMyBundle;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResourceBundle bundle = getMyBundle();
        HttpSession session = req.getSession(true);
        session.setAttribute("language", "en_US");
        session.setAttribute("bundle",bundle);
        resp.sendRedirect("login.jsp");
    }
}
