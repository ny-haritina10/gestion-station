<%@ page import="model.view.profit.Profit" %>
<%@ page import="model.pompe.Pompe" %>


<!DOCTYPE html>
<html>
<head>
    <title>Profit Data</title>
</head>
<body>
    <div class="container">
        <h2>Profit Information</h2>

        <table border="2">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Pump</th>
                    <th>Date</th>
                    <th>Quantity Sold</th>
                    <th>Quantity Bought</th>
                    <th>Selling Price</th>
                    <th>Purchase Price</th>
                    <th>Stock Amount</th>
                    <th>Profit</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Retrieve the list of profitList from the request attributes
                    Profit[] profitList = (Profit[]) request.getAttribute("profitList");

                    // Check if the profitList array is not null and has elements
                    if (profitList != null && profitList.length > 0) {
                        for (Profit profit : profitList) {
                %>
                            <tr>
                                <td><%= profit.getProduct().getName() %></td>
                                <td><%= profit.getPompe().getName() %></td>
                                <td><%= profit.getDateProfit() %></td>
                                <td><%= profit.getQteVendue() %></td>
                                <td><%= profit.getQteAchete() %></td>
                                <td><%= profit.getPuVente() %></td>
                                <td><%= profit.getPuAchat() %></td>
                                <td><%= profit.getAmountStock() %></td>
                                <td><%= profit.getProfit() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="9">No profit data available.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>