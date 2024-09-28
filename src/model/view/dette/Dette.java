package model.view.dette;

import java.sql.*;
import base.BaseModel;
import model.product.Product;

public class Dette extends BaseModel<Dette> {

    private double amount;
    private String isPayed;
    private Product product;
    private Date dateSession;
    private String typeTransaction;

    // Constructor
    public Dette() {}

    public Dette(double amount, String isPayed, Product product, Date dateSession, String typeTransaction) {
        setAmount(amount);
        setIsPayed(isPayed);
        setProduct(product);
        setDateSession(dateSession);
        setTypeTransaction(typeTransaction);
    }

    // Getters and setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(String isPayed) {
        this.isPayed = isPayed;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    @Override
    protected Dette mapRow(ResultSet result) throws Exception {
        double amount = result.getDouble("amount");
        String isPayed = result.getString("is_payed");
        Product product = new Product().getById(result.getInt("id_product"), Product.class, null);
        Date dateSession = result.getDate("date_session");
        String typeTransaction = result.getString("type_transaction");

        return new Dette(amount, isPayed, product, dateSession, typeTransaction);
    }

    @Override
    public String toString() {
        return "Dette{amount=" + amount + ", isPayed=" + isPayed + ", product=" + product.getName() + 
               ", dateSession=" + dateSession + ", typeTransaction='" + typeTransaction + "'}";
    }
}