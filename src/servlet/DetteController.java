package servlet;

import model.view.dette.Dette;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
        try {
            Dette[] dettes = new Dette().getAll(Dette.class, null, "v_get_dette");
            
            req.setAttribute("dettes", dettes);
            req.getRequestDispatcher("dette.jsp").forward(req, res);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving debts.", e);
        }
    }
}