package servlet;

import model.view.profit.Profit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfitController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
        try {
            Profit[] profitList = new Profit().getAll(Profit.class, null, "v_profit");  
            req.setAttribute("profitList", profitList);
            req.getRequestDispatcher("profit.jsp").forward(req, res);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving profit data.", e);
        }
    }
}