package servlet;

import model.view.vente.VentesPerPompeAndDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VenteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
        try {
            VentesPerPompeAndDate[] ventes = new VentesPerPompeAndDate().getAll(VentesPerPompeAndDate.class, null, "v_ventes_per_pompe_and_date");
            
            req.setAttribute("ventes", ventes);
            req.getRequestDispatcher("vente.jsp").forward(req, res);
        } 


        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving ventes.", e);
        }
    }
}