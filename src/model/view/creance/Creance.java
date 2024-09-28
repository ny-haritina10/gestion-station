package model.view.creance;

import model.product.Product; 
import java.sql.*;
import base.BaseModel;

public class Creance extends BaseModel<Creance> {

    private boolean isPayed;
    private Product product; 
    private Date dateSession;
    private String typeTransaction;
    private double sommeVentes;

    // Constructor
    public Creance() {}

    public Creance(boolean isPayed, Product product, Date dateSession, String typeTransaction, double sommeVentes) {
        setIsPayed(isPayed);
        setProduct(product);
        setDateSession(dateSession);
        setTypeTransaction(typeTransaction);
        setSommeVentes(sommeVentes);
    }

    // Getters and setters
    public boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Product getProduct() { // Updated getter for Product
        return product;
    }

    public void setProduct(Product product) { // Updated setter for Product
        this.product = product;
    }

    public Date getDateSession() {
        return dateSession;
    }

    public void setDateSession(Date dateSession) {
        this.dateSession = dateSession;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public double getSommeVentes() {
        return sommeVentes;
    }

    public void setSommeVentes(double sommeVentes) {
        this.sommeVentes = sommeVentes;
    }

    @Override
    protected Creance mapRow(ResultSet result) throws Exception {
        boolean isPayed = result.getBoolean("is_payed");
        int productId = result.getInt("id_product"); 
        Product product = new Product().getById(productId, Product.class, null); 
        Date dateSession = result.getDate("date_session");
        String typeTransaction = result.getString("type_transaction");
        double sommeVentes = result.getDouble("somme_ventes");

        return new Creance(isPayed, product, dateSession, typeTransaction, sommeVentes);
    }

    @Override
    public String toString() {
        return "Creance{isPayed=" + isPayed + ", product=" + product.getName() + 
               ", dateSession=" + dateSession + ", typeTransaction='" + typeTransaction + 
               "', sommeVentes=" + sommeVentes + "}";
    }
}