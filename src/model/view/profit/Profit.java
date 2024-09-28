package model.view.profit;

import java.sql.*;
import base.BaseModel;
import model.product.Product;
import model.pompe.Pompe;

public class Profit extends BaseModel<Profit> {

    private Product product;
    private Pompe pompe;
    private Date dateProfit;
    private double qteVendue;
    private double qteAchete;
    private double puVente;
    private double puAchat;
    private double amountStock;
    private double profit;

    // Constructor
    public Profit() {}

    public Profit(Product product, Pompe pompe, Date dateProfit, double qteVendue, double qteAchete, double puVente, 
                  double puAchat, double amountStock, double profit) {
        setProduct(product);
        setPompe(pompe);
        setDateProfit(dateProfit);
        setQteVendue(qteVendue);
        setQteAchete(qteAchete);
        setPuVente(puVente);
        setPuAchat(puAchat);
        setAmountStock(amountStock);
        setProfit(profit);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Pompe getPompe() {
        return pompe;
    }

    public void setPompe(Pompe pompe) {
        this.pompe = pompe;
    }

    public Date getDateProfit() {
        return dateProfit;
    }

    public void setDateProfit(Date dateProfit) {
        this.dateProfit = dateProfit;
    }

    public double getQteVendue() {
        return qteVendue;
    }

    public void setQteVendue(double qteVendue) {
        this.qteVendue = qteVendue;
    }

    public double getQteAchete() {
        return qteAchete;
    }

    public void setQteAchete(double qteAchete) {
        this.qteAchete = qteAchete;
    }

    public double getPuVente() {
        return puVente;
    }

    public void setPuVente(double puVente) {
        this.puVente = puVente;
    }

    public double getPuAchat() {
        return puAchat;
    }

    public void setPuAchat(double puAchat) {
        this.puAchat = puAchat;
    }

    public double getAmountStock() {
        return amountStock;
    }

    public void setAmountStock(double amountStock) {
        this.amountStock = amountStock;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Profit{product=" + product.getName() + ", pompe=" + pompe.getName() + 
               ", dateProfit=" + dateProfit + ", qteVendue=" + qteVendue + 
               ", qteAchete=" + qteAchete + ", puVente=" + puVente + 
               ", puAchat=" + puAchat + ", amountStock=" + amountStock + 
               ", profit=" + profit + "}";
    }

    @Override
    protected Profit mapRow(ResultSet result) throws Exception {
        Product product = new Product().getById(result.getInt("product_id"), Product.class, null);
        Pompe pompe = new Pompe().getById(result.getInt("pump_id"), Pompe.class, null);
        Date dateProfit = result.getDate("date_profit");
        double qteVendue = result.getDouble("qte_vendue");
        double qteAchete = result.getDouble("qte_achete");
        double puVente = result.getDouble("PU_vente");
        double puAchat = result.getDouble("PU_achat");
        double amountStock = result.getDouble("amount_stock");
        double profit = result.getDouble("profit");

        return new Profit(product, pompe, dateProfit, qteVendue, qteAchete, puVente, puAchat, amountStock, profit);
    }
}