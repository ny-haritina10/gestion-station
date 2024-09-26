package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import remote.AvoirFCRemote;
import utilitaire.UtilDB;
import prevision.Prevision;

public class TestStationServlet extends HttpServlet {
    
    private AvoirFCRemote myBean; 

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            
            String jndiName = "java:global/bean-ejb/AvoirFC!remote.AvoirFCRemote"; 
            myBean = (AvoirFCRemote) ctx.lookup(jndiName);
        } catch (NamingException e) {
            throw new ServletException("Failed to lookup EJB", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        try (PrintWriter out = res.getWriter()) {
            try {
                Connection con = new UtilDB().GetConn();
                Prevision prev = myBean.genererPrevision("prev", con);

                out.println("<h1> Resultat here: " + prev.getCompte() + "</h1>");
            } catch (Exception e) {
                out.println("<h1>Error invoking EJB method</h1>");
                e.printStackTrace(out);
            }
        }
    }
}