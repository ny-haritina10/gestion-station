package model.fournisseur;


import java.sql.*;
import base.BaseModel;

public class Fournisseur extends BaseModel<Fournisseur> {

    private int id;
    private String name;

    // Constructor
    public Fournisseur() 
    { }

    public Fournisseur(int id, String name) {
        setId(id);
        setName(name);
    }

    // Getters and Setters
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
    public String toString() {
        return "Fournisseur{id=" + id + ", name='" + name + "'}";
    }

    @Override
    protected Fournisseur mapRow(ResultSet result) 
        throws Exception 
    {
        int id = result.getInt("id");
        String name = result.getString("name");

        return new Fournisseur(id, name);
    }
}