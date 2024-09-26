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

import remote.PersonRemote;
import utilitaire.UtilDB;
import person.Person;

public class PersonServlet extends HttpServlet {
    
    private PersonRemote myBean; 

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            
            String jndiName = "java:global/bean-ejb/Person!remote.PersonRemote"; 
            myBean = (PersonRemote) ctx.lookup(jndiName);
        } catch (NamingException e) {
            throw new ServletException("Failed to lookup EJB", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        try (PrintWriter out = res.getWriter()) {
            try {
                Person pers = myBean.getPers();

                out.println("<h1> Resultat here: " + pers.getName() + " -> " + pers.getAge() + "</h1>");
            } catch (Exception e) {
                out.println("<h1>Error invoking EJB method</h1>");
                e.printStackTrace(out);
            }
        }
    }
}