package model.pompe;

import java.sql.*;
import base.BaseModel;

public class Pompe extends BaseModel<Pompe>{

    private int id;
    private String name;

    // Constructor
    public Pompe() 
    { }

    public Pompe(int id, String name) {
        setId(id);
        setName(name);
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
    { return "Pompe{id=" + id + ", name='" + name + "'}"; }

    @Override
    protected Pompe mapRow(ResultSet result) throws Exception {
        int id = result.getInt("id");
        String name = result.getString("name");

        return new Pompe(id, name);
    }
}