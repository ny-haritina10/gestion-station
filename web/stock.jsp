<%@ page import="model.view.stock.StockRestant" %>

<!DOCTYPE html>
<html>
<head>
    <title>Stock Restant Data</title>
</head>
<body>
    <div class="container">
        <h2>Stock Restant</h2>

        <table border="2">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Pompe</th>
                    <th>Date Ravitaillement</th>
                    <th>Quantité Initiale</th>
                    <th>Total Ravitaillee</th>
                    <th>Total Vendue</th>
                    <th>Quantité Stock Restant (Unit)</th>
                    <th>Quantité Stock Restant (Amount)</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Retrieve the list of stockRestantList from the request attributes
                    StockRestant[] stockRestantList = (StockRestant[]) request.getAttribute("stockRestantList");

                    // Check if the stockRestantList array is not null and has elements
                    if (stockRestantList != null && stockRestantList.length > 0) {
                        for (StockRestant stockRestant : stockRestantList) {
                %>
                            <tr>
                                <td><%= stockRestant.getProduct().getName() %></td>
                                <td><%= stockRestant.getPompe().getName() %></td>
                                <td><%= stockRestant.getDateRavitaillement() %></td>
                                <td><%= stockRestant.getQteInitiale() %></td>
                                <td><%= stockRestant.getTotalRavitaillee() %></td>
                                <td><%= stockRestant.getTotalVendue() %></td>
                                <td><%= stockRestant.getQteStockRestantUnit() %></td>
                                <td><%= stockRestant.getQteStockRestantAmount() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="8">No stock restant data available.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>