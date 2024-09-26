package model.magasin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.Database;

public class Magasin {

    private int id;
    private String name;

    // Constructor
    public Magasin() 
    { }

    public Magasin(int id, String name) {
        setId(id);
        setName(name);
    }

    public static List<Magasin> getAll() {
        List<Magasin> magasins = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = Database.getConnection();
            String query = "SELECT id, name FROM Magasin";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                magasins.add(new Magasin(id, name));
            }
        } 
        
        catch (SQLException e) 
        { e.printStackTrace(); } 
        
        finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            }

            catch (SQLException e) 
            { e.printStackTrace(); }
        }

        return magasins;
    }

    
    public int getId() 
    { return id; }

    public void setId(int id) {
        if (id > 0) 
        { this.id = id; } 
        
        else 
        { throw new IllegalArgumentException("ID must be positive."); }
    }

    public String getName()
    { return name; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty() && name.length() <= 255) 
        { this.name = name; } 
        
        else 
        { throw new IllegalArgumentException("Name cannot be null, empty, or longer than 255 characters."); }
    }

    @Override
    public String toString() 
    { return "Magasin{id=" + id + ", name='" + name + "'}"; }
}