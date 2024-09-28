package model.view.stock;

import java.sql.*;
import base.BaseModel;
import model.product.Product;
import model.pompe.Pompe;

public class StockRestant extends BaseModel<StockRestant> {

    private Product product;
    private Pompe pompe;
    private Date dateRavitaillement;
    private double qteInitiale;
    private double totalRavitaillee;
    private double totalVendue;
    private double qteStockRestantUnit;
    private double qteStockRestantAmount;

    // Constructor
    public StockRestant() {}

    public StockRestant(Product product, Pompe pompe, Date dateRavitaillement, double qteInitiale,
                        double totalRavitaillee, double totalVendue, double qteStockRestantUnit, double qteStockRestantAmount) {
        setProduct(product);
        setPompe(pompe);
        setDateRavitaillement(dateRavitaillement);
        setQteInitiale(qteInitiale);
        setTotalRavitaillee(totalRavitaillee);
        setTotalVendue(totalVendue);
        setQteStockRestantUnit(qteStockRestantUnit);
        setQteStockRestantAmount(qteStockRestantAmount);
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

    public Date getDateRavitaillement() {
        return dateRavitaillement;
    }

    public void setDateRavitaillement(Date dateRavitaillement) {
        this.dateRavitaillement = dateRavitaillement;
    }

    public double getQteInitiale() {
        return qteInitiale;
    }

    public void setQteInitiale(double qteInitiale) {
        this.qteInitiale = qteInitiale;
    }

    public double getTotalRavitaillee() {
        return totalRavitaillee;
    }

    public void setTotalRavitaillee(double totalRavitaillee) {
        this.totalRavitaillee = totalRavitaillee;
    }

    public double getTotalVendue() {
        return totalVendue;
    }

    public void setTotalVendue(double totalVendue) {
        this.totalVendue = totalVendue;
    }

    public double getQteStockRestantUnit() {
        return qteStockRestantUnit;
    }

    public void setQteStockRestantUnit(double qteStockRestantUnit) {
        this.qteStockRestantUnit = qteStockRestantUnit;
    }

    public double getQteStockRestantAmount() {
        return qteStockRestantAmount;
    }

    public void setQteStockRestantAmount(double qteStockRestantAmount) {
        this.qteStockRestantAmount = qteStockRestantAmount;
    }

    @Override
    public String toString() {
        return "StockRestant{product=" + product.getName() + ", pompe=" + pompe.getName() + 
               ", dateRavitaillement=" + dateRavitaillement + 
               ", qteInitiale=" + qteInitiale + ", totalRavitaillee=" + totalRavitaillee +
               ", totalVendue=" + totalVendue + ", qteStockRestantUnit=" + qteStockRestantUnit +
               ", qteStockRestantAmount=" + qteStockRestantAmount + "}";
    }

    @Override
    protected StockRestant mapRow(ResultSet result) throws Exception {
        Product product = new Product().getById(result.getInt("id_product"), Product.class, null);
        Pompe pompe = new Pompe().getById(result.getInt("pompe"), Pompe.class, null);
        Date dateRavitaillement = result.getDate("date_ravitaillement");
        double qteInitiale = result.getDouble("qte_initiale");
        double totalRavitaillee = result.getDouble("total_ravitaillee");
        double totalVendue = result.getDouble("total_vendue");
        double qteStockRestantUnit = result.getDouble("qte_stock_restant_unit");
        double qteStockRestantAmount = result.getDouble("qte_stock_restant_amount");

        return new StockRestant(product, pompe, dateRavitaillement, qteInitiale, totalRavitaillee, totalVendue, qteStockRestantUnit, qteStockRestantAmount);
    }
}