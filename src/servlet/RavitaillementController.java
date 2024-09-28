package servlet;

import model.view.ravitaillement.Ravitaillement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RavitaillementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
        try {
            Ravitaillement[] ravitaillements = new Ravitaillement().getAll(Ravitaillement.class, null, "v_ravitaillement");    
            req.setAttribute("ravitaillements", ravitaillements);
            req.getRequestDispatcher("ravitaillement.jsp").forward(req, res);
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving ravitaillement data.", e);
        }
    }
}