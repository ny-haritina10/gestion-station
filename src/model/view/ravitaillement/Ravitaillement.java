package model.view.ravitaillement;

import java.sql.*;
import base.BaseModel;
import model.product.Product;
import model.pompe.Pompe;

public class Ravitaillement extends BaseModel<Ravitaillement> {

    private Product product;
    private Pompe pompe;
    private Date dateRavitaillement;
    private double totalRavitaillement;

    // Constructor
    public Ravitaillement() 
    { }

    public Ravitaillement(Product product, Pompe pompe, Date dateRavitaillement, double totalRavitaillement) {
        setProduct(product);
        setPompe(pompe);
        setDateRavitaillement(dateRavitaillement);
        setTotalRavitaillement(totalRavitaillement);
    }

    public Product getProduct() 
    { return product; }

    public void setProduct(Product product) {
        if (product != null) 
        { this.product = product; } 
        else 
        { throw new IllegalArgumentException("Product must not be null."); }
    }

    public Pompe getPompe() 
    { return pompe; }

    public void setPompe(Pompe pompe) {
        if (pompe != null) 
        { this.pompe = pompe; } 
        else 
        { throw new IllegalArgumentException("Pompe must not be null."); }
    }

    public Date getDateRavitaillement() 
    { return dateRavitaillement; }

    public void setDateRavitaillement(Date dateRavitaillement) {
        if (dateRavitaillement != null) 
        { this.dateRavitaillement = dateRavitaillement; } 
        else 
        { throw new IllegalArgumentException("Date of ravitaillement cannot be null."); }
    }

    public double getTotalRavitaillement() 
    { return totalRavitaillement; }

    public void setTotalRavitaillement(double totalRavitaillement) {
        if (totalRavitaillement >= 0) 
        { this.totalRavitaillement = totalRavitaillement; } 
        else 
        { throw new IllegalArgumentException("Total ravitaillement must be non-negative."); }
    }

    @Override
    public String toString() {
        return "Ravitaillement{Product=" + product.getName() + ", Pompe=" + pompe.getName() + 
               ", DateRavitaillement=" + dateRavitaillement + 
               ", TotalRavitaillement=" + totalRavitaillement + "}";
    }

    @Override
    protected Ravitaillement mapRow(ResultSet result) 
        throws Exception 
    {
        Product product = new Product().getById(result.getInt("id_product"), Product.class, null);
        Pompe pompe = new Pompe().getById(result.getInt("pompe"), Pompe.class, null);
        Date dateRavitaillement = result.getDate("date_ravitaillement");
        double totalRavitaillement = result.getDouble("total_ravitaillement");

        return new Ravitaillement(product, pompe, dateRavitaillement, totalRavitaillement);
    }
}