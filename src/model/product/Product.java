package model.product;

import java.sql.*;
import base.BaseModel;
import model.annexe.unit.Unit;

public class Product extends BaseModel<Product> {

    private int id;
    private Unit unit;
    private String name;
    private double puAchat;
    private double puVente;

    // Constructor
    public Product() 
    { }

    public Product(int id, Unit unit, String name, double puAchat, double puVente) {
        setId(id);
        setUnit(unit);
        setName(name);
        setPuAchat(puAchat);
        setPuVente(puVente);
    }

    public int getId() 
    { return id; }

    public void setId(int id) {
        if (id > 0) 
        { this.id = id; } 
        
        else 
        { throw new IllegalArgumentException("ID must be positive."); }
    }

    public Unit getUnit() 
    { return unit; }

    public void setUnit(Unit unit) {
        if (unit != null) 
        { this.unit = unit; } 
        
        else 
        { throw new IllegalArgumentException("Unit can't b null."); }
    }

    public String getName() 
    { return name; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty() && name.length() <= 255) 
        { this.name = name; } 
        
        else 
        { throw new IllegalArgumentException("Name cannot be null, empty, or longer than 255 characters."); }
    }

    public double getPuAchat() 
    { return puAchat; }

    public void setPuAchat(double puAchat) {
        if (puAchat >= 0) 
        { this.puAchat = puAchat; } 
        
        else 
        { throw new IllegalArgumentException("Purchase price must be non-negative."); }
    }

    public double getPuVente() 
    { return puVente; }

    public void setPuVente(double puVente) {
        if (puVente >= 0) 
        { this.puVente = puVente; } 
        
        else 
        { throw new IllegalArgumentException("Sale price must be non-negative."); }
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", unit=" + unit + ", name='" + name + "', puAchat=" + puAchat + ", puVente=" + puVente + "}";
    }

    @Override
    protected Product mapRow(ResultSet result) 
        throws Exception 
    {
        int id = result.getInt("id");
        Unit unit = new Unit().getById(result.getInt("id_unit"), Unit.class, null);
        String name = result.getString("name");
        double puAchat = result.getDouble("PU_achat");
        double puVente = result.getDouble("PU_vente");

        return new Product(id, unit, name, puAchat, puVente);
    }
}