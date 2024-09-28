<%@ page import="model.view.creance.Creance" %>
<%@ page import="model.product.Product" %>

<!DOCTYPE html>
<html>
<head>
    <title>Unpaid Receivables</title>
</head>
<body>
    <div class="container">
        <h2>Unpaid Receivables</h2>

        <table border="2">
            <thead>
                <tr>
                    <th>Is Paid</th>
                    <th>Product ID</th>
                    <th>Product Name</th> 
                    <th>Date of Session</th>
                    <th>Transaction Type</th>
                    <th>Sales Amount</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Retrieve the list of creances from the request attributes
                    Creance[] creances = (Creance[]) request.getAttribute("creances");

                    // Check if the creances array is not null and has elements
                    if (creances != null && creances.length > 0) {
                        for (Creance creance : creances) {
                            Product product = creance.getProduct(); // Get the Product object
                %>
                            <tr>
                                <td><%= creance.getIsPayed() %></td>
                                <td><%= product.getId() %></td> 
                                <td><%= product.getName() %></td>
                                <td><%= creance.getDateSession() %></td>
                                <td><%= creance.getTypeTransaction() %></td>
                                <td><%= creance.getSommeVentes() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="6">No unpaid receivables found.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>