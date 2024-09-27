package model.cuve;

import java.sql.*;
import base.BaseModel;
import model.pompe.Pompe;
import model.product.Product;

public class Cuve extends BaseModel<Cuve> {

    private int id;
    private String name;
    private double qteMax;
    private double qteInitial;
    private Pompe pompe;
    private Product product;

    // Constructor
    public Cuve() 
    { }

    public Cuve(int id, String name, double qteMax, double qteInitial, Pompe pompe, Product product) {
        setId(id);
        setName(name);
        setQteMax(qteMax);
        setQteInitial(qteInitial);
        setPompe(pompe);
        setProduct(product);
    }

    public Pompe getPompe() 
    { return pompe; }

    public void setPompe(Pompe pompe) 
    { this.pompe = pompe; }

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

    public double getQteMax() 
    { return qteMax; }

    public void setQteMax(double qteMax) {
        if (qteMax >= 0) 
        { this.qteMax = qteMax; } 
        
        else 
        { throw new IllegalArgumentException("Max quantity must be non-negative."); }
    }

    public double getQteInitial() 
    { return qteInitial; }

    public void setQteInitial(double qteInitial) {
        if (qteInitial >= 0 && qteInitial <= qteMax) 
        { this.qteInitial = qteInitial; } 
        
        else 
        { throw new IllegalArgumentException("Initial quantity must be between 0 and max quantity."); }
    }

    @Override
    public String toString() 
    { return "Cuve{id=" + id + ", name='" + name + "', qteMax=" + qteMax + ", qteInitial=" + qteInitial + "}"; }

    @Override
    protected Cuve mapRow(ResultSet resultSet) 
        throws Exception 
    {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double qteMax = resultSet.getDouble("qte_max");
        double qteInitial = resultSet.getDouble("qte_initial");
        Pompe pompe = new Pompe().getById(resultSet.getInt("id_pompe"), Pompe.class, null);
        Product product = new Product().getById(resultSet.getInt("id_product"), Product.class, null);
        
        return new Cuve(id, name, qteMax, qteInitial, pompe, product);
    }

    public Product getProduct() 
    { return product; }

    public void setProduct(Product product) 
    { this.product = product; }
}