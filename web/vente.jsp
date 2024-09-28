<%@ page import="model.view.vente.VentesPerPompeAndDate" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ventes per Pompe and Date</title>
</head>
<body>
    <div class="container">
        <h2>Ventes per Pompe and Date</h2>

        <table border="2">
            <thead>
                <tr>
                    <th>ID Product</th>
                    <th>ID Pompe</th>
                    <th>Date Vente</th>
                    <th>Somme Ventes</th>
                    <th>Is Payed</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Retrieve the list of ventes from the request attributes
                    VentesPerPompeAndDate[] ventes = (VentesPerPompeAndDate[]) request.getAttribute("ventes");

                    // Check if the ventes array is not null and has elements
                    if (ventes != null && ventes.length > 0) {
                        for (VentesPerPompeAndDate vente : ventes) {
                %>
                            <tr>
                                <td><%= vente.getIdProduct() %></td>
                                <td><%= vente.getIdPompe() %></td>
                                <td><%= vente.getDateVente() %></td>
                                <td><%= vente.getSommeVentes() %></td>
                                <td><%= vente.getIsPayed() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No sales data available.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>