package servlet;

import model.view.creance.Creance;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreanceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
        try {
            Creance[] creances = new Creance().getAll(Creance.class, null, "v_get_creances");
            req.setAttribute("creances", creances);
            req.getRequestDispatcher("creance.jsp").forward(req, res);
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving creances.", e);
        }
    }
}