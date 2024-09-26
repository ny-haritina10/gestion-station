package model.counter;

import java.sql.*;
import java.util.Date;

import base.BaseModel;
import database.Database;
import model.pompe.Pompe;
import model.pompe.Pompiste;

import enums.TypeTransaction;

public class Counter extends BaseModel<Counter> {

    private int id;
    private double amount;
    private Pompe pompe;
    private Pompiste pompiste;
    private Date dateSession;

    // Constructor
    public Counter() 
    { }

    /*
     * If pompiste == NULL
     *  => Ravitaillement CASH OUT
     * Else 
     *  => ACHAT CASH IN
    */

    public Counter(int id, double amount, Pompe pompe, Pompiste pompiste, Date dateSession) {
        setId(id);
        setAmount(amount);
        setPompe(pompe);
        setPompiste(pompiste);
        setDateSession(dateSession);
    }

    public void insert() 
        throws SQLException 
    {
        String sql = "INSERT INTO Counter (id, amount, id_pompe, id_pompiste, date_session, type_transaction) " +
                    "VALUES (seq_counter.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, getAmount());
            statement.setInt(2, getPompe().getId());

            if (getPompiste() != null) {
                statement.setInt(3, getPompiste().getId());
            } else {
                statement.setNull(3, Types.INTEGER); // Nullable foreign key
            }

            statement.setDate(4, new java.sql.Date(getDateSession().getTime()));
            statement.setString(5, getTypeTransaction());

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
        if (this.getPompiste() == null)  
        { return TypeTransaction.CASH_OUT.getTypeTransation(); }

        // VENTE PRODUIT
        else 
        { return TypeTransaction.CASH_IN.getTypeTransation(); }
    }

    public int getId() 
    { return id; }

    public void setId(int id) {
        if (id > 0) 
        { this.id = id; } 
        
        else 
        { throw new IllegalArgumentException("ID must be positive."); }
    }

    public double getAmount() 
    { return amount; }

    public void setAmount(double amount) {
        if (amount >= 0) 
        { this.amount = amount; } 
        
        else 
        { throw new IllegalArgumentException("Amount must be non-negative."); }
    }

    public Pompe getPompe() 
    { return pompe; }

    public void setPompe(Pompe pompe) {
        if (pompe != null) 
        { this.pompe = pompe; } 
        
        else 
        { throw new IllegalArgumentException("Pompe cannot be null."); }
    }

    public Pompiste getPompiste() 
    { return pompiste; }

    public void setPompiste(Pompiste pompiste) 
    { this.pompiste = pompiste; }

    public Date getDateSession() 
    { return dateSession; }

    public void setDateSession(Date dateSession) 
    { this.dateSession = dateSession; }

    @Override
    public String toString() {
        return "Counter{id=" + id + ", amount=" + amount + ", pompe=" + pompe + ", pompiste=" + pompiste + ", dateSession=" + dateSession + "}";
    }

    @Override
    protected Counter mapRow(ResultSet result) 
        throws Exception 
    {
        int id = result.getInt("id");
        double amount = result.getDouble("amount");
        Pompe pompe = new Pompe().getById(result.getInt("id_pompe"), Pompe.class, null);
        Pompiste pompiste = new Pompiste().getById(result.getInt("id_pompiste"), Pompiste.class, null);
        Date dateSession = result.getDate("date_session");

        return new Counter(id, amount, pompe, pompiste, dateSession);
    }
}