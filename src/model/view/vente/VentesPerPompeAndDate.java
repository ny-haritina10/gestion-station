package model.view.vente;

import java.sql.*;
import java.util.Date;
import base.BaseModel;

public class VentesPerPompeAndDate extends BaseModel<VentesPerPompeAndDate> {

    private int idProduct;
    private int idPompe;
    private Date dateVente;
    private double sommeVentes;
    private String isPayed;

    // Constructors
    public VentesPerPompeAndDate() 
    { }

    public VentesPerPompeAndDate(int idProduct, int idPompe, Date dateVente, double sommeVentes, String isPayed) {
        setIdProduct(idProduct);
        setIdPompe(idPompe);
        setDateVente(dateVente);
        setSommeVentes(sommeVentes);
        setIsPayed(isPayed);
    }

    // Getters and Setters
    public int getIdProduct() 
    { return idProduct; }

    public void setIdProduct(int idProduct) {
        if (idProduct > 0) 
        { this.idProduct = idProduct; } 
        
        else 
        { throw new IllegalArgumentException("Product ID must be positive."); }
    }

    public int getIdPompe() 
    { return idPompe; }

    public void setIdPompe(int idPompe) {
        if (idPompe > 0) 
        { this.idPompe = idPompe; } 
        
        else 
        { throw new IllegalArgumentException("Pompe ID must be positive."); }
    }

    public Date getDateVente() 
    { return dateVente; }

    public void setDateVente(Date dateVente) 
    { this.dateVente = dateVente; }

    public double getSommeVentes() 
    { return sommeVentes; }

    public void setSommeVentes(double sommeVentes) {
        if (sommeVentes >= 0) 
        { this.sommeVentes = sommeVentes; } 
        
        else 
        { throw new IllegalArgumentException("Somme des ventes must be non-negative."); }
    }

    public String getIsPayed() 
    { return isPayed; }

    public void setIsPayed(String isPayed) 
    { this.isPayed = isPayed; }

    @Override
    public String toString() {
        return "VentesPerPompeAndDate{idProduct=" + idProduct + ", idPompe=" + idPompe + ", dateVente=" + dateVente + 
               ", sommeVentes=" + sommeVentes + ", isPayed=" + isPayed + "}";
    }

    // Mapping the ResultSet to VentesPerPompeAndDate object
    @Override
    protected VentesPerPompeAndDate mapRow(ResultSet result) 
        throws Exception 
    {
        int idProduct = result.getInt("id_product");
        int idPompe = result.getInt("id_pompe");
        Date dateVente = result.getDate("date_vente");
        double sommeVentes = result.getDouble("somme_ventes");
        String isPayed = result.getString("is_payed");

        return new VentesPerPompeAndDate(idProduct, idPompe, dateVente, sommeVentes, isPayed);
    }
}