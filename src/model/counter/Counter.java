package model.counter;

import java.sql.*;
import java.util.Date;

import base.BaseModel;
import database.Database;
import model.pompe.Pompe;
import model.pompe.Pompiste;
import model.fournisseur.Fournisseur;  
import model.product.Product;  

import enums.TypeTransaction;

public class Counter extends BaseModel<Counter> {

    private int id;
    private double amount;
    private String isPayed;
    private Pompe pompe;
    private Pompiste pompiste;
    private Fournisseur fournisseur;  
    private Product product;  
    private Date dateSession;

    // Constructor
    public Counter() { }

    public Counter(int id, double amount, String isPayed, Pompe pompe, Pompiste pompiste, Fournisseur fournisseur, Product product, Date dateSession) {
        setId(id);
        setAmount(amount);
        setIsPayed(isPayed);
        setPompe(pompe);
        setPompiste(pompiste);
        setFournisseur(fournisseur);
        setProduct(product);
        setDateSession(dateSession);
    }

    public void insert() throws SQLException {
        String sql = "INSERT INTO Counter (id, amount, is_payed, id_pompe, id_pompiste, id_fournisseur, id_product, date_session, type_transaction) " +
                     "VALUES (seq_counter.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, getAmount());
            statement.setString(2, isPayed());

            statement.setInt(3, getPompe().getId());

            // Nullable foreign key for Pompiste
            if (getPompiste() != null) {
                statement.setInt(4, getPompiste().getId());
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            // Nullable foreign key for Fournisseur
            if (getFournisseur() != null) {
                statement.setInt(5, getFournisseur().getId());
            } else {
                statement.setNull(5, Types.INTEGER);
            }

            statement.setInt(6, getProduct().getId());

            statement.setDate(7, new java.sql.Date(getDateSession().getTime()));
            statement.setString(8, getTypeTransaction());

            // Execute the insert
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Counter inserted successfully.");
            } else {
                System.out.println("Insert failed.");
            }
        }
    }

    public String getTypeTransaction() {
        // RAVITAILLEMENT
        if (this.getPompiste() == null) {
            return TypeTransaction.CASH_OUT.getTypeTransation();
        }

        // VENTE PRODUIT
        else {
            return TypeTransaction.CASH_IN.getTypeTransation();
        }
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be positive.");
        }
    }

    public String isPayed()
    { return isPayed; }

    public void setIsPayed(String isPayed)
    { this.isPayed = isPayed; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount must be non-negative.");
        }
    }

    public Pompe getPompe() { return pompe; }

    public void setPompe(Pompe pompe) {
        if (pompe != null) {
            this.pompe = pompe;
        } else {
            throw new IllegalArgumentException("Pompe cannot be null.");
        }
    }

    public Pompiste getPompiste() { return pompiste; }

    public void setPompiste(Pompiste pompiste) { this.pompiste = pompiste; }

    public Fournisseur getFournisseur() { return fournisseur; }

    public void setFournisseur(Fournisseur fournisseur) { this.fournisseur = fournisseur; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) {
        if (product != null) {
            this.product = product;
        } else {
            throw new IllegalArgumentException("Product cannot be null.");
        }
    }

    public Date getDateSession() { return dateSession; }

    public void setDateSession(Date dateSession) { this.dateSession = dateSession; }

    @Override
    public String toString() {
        return "Counter{id=" + id + ", amount=" + amount + ", pompe=" + pompe + ", pompiste=" + pompiste + ", fournisseur=" + fournisseur + ", product=" + product + ", dateSession=" + dateSession + "}";
    }

    @Override
    protected Counter mapRow(ResultSet result) 
        throws Exception 
    {
        int id = result.getInt("id");
        double amount = result.getDouble("amount");
        String isPayed = result.getString("is_payed");
        Pompe pompe = new Pompe().getById(result.getInt("id_pompe"), Pompe.class, null);
        Pompiste pompiste = new Pompiste().getById(result.getInt("id_pompiste"), Pompiste.class, null);
        Fournisseur fournisseur = new Fournisseur().getById(result.getInt("id_fournisseur"), Fournisseur.class, null);
        Product product = new Product().getById(result.getInt("id_product"), Product.class, null);
        Date dateSession = result.getDate("date_session");

        return new Counter(id, amount, isPayed, pompe, pompiste, fournisseur, product, dateSession);
    }
}