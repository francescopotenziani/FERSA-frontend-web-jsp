package it.uniroma2.dicii.ispw.fersa.Servlet;

import com.google.gwt.resources.client.impl.ImageResourcePrototype;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

import static it.uniroma2.dicii.ispw.fersa.Share.BundleSingleton.*;

public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (req.getParameter("lang").equals("it")){
            session.setAttribute("language","it_IT");
        }else{
            session.setAttribute("language","en_US");
        }
        resp.sendRedirect(req.getParameter("page"));
    }
}
