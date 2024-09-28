package servlet;

import model.view.stock.StockRestant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StockRestantController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
        try {
            StockRestant[] stockRestantList = new StockRestant().getAll(StockRestant.class, null, "v_stock_restant");
            
            req.setAttribute("stockRestantList", stockRestantList);
            req.getRequestDispatcher("stock.jsp").forward(req, res);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving stock restant data.", e);
        }
    }
}