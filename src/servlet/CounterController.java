package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.counter.Counter;
import model.pompe.Pompe;
import model.pompe.Pompiste;

public class CounterController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {   
        try {
            Pompiste[] pompistes = new Pompiste().getAll(Pompiste.class, null);
            Pompe[] pompes = new Pompe().getAll(Pompe.class, null);

            req.setAttribute("pompistes", pompistes);
            req.setAttribute("pompes", pompes);

            req.getRequestDispatcher("counter.jsp").forward(req, res);
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException 
    {

        try {
            String amountStr = req.getParameter("amount");

            double amount = Double.parseDouble(amountStr);
            Date date = Date.valueOf(req.getParameter("date-session"));

            Pompiste pompiste = new Pompiste().getById(Integer.valueOf(req.getParameter("pompiste")), Pompiste.class, null);
            Pompe pompe = new Pompe().getById(Integer.valueOf(req.getParameter("pompe")), Pompe.class, null);
            
            Counter counter = new Counter();

            counter.setAmount(amount);
            counter.setDateSession(date);
            counter.setPompe(pompe);
            counter.setPompiste(pompiste);

            counter.insert();

            resp.sendRedirect("index.jsp");
        } 
        
        catch (Exception e) 
        { e.printStackTrace(); }
    }
}