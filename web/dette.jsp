<%@ page import="model.view.dette.Dette" %>
<%@ page import="model.product.Product" %>

<!DOCTYPE html>
<html>
<head>
    <title>Unpaid Debts</title>
</head>
<body>
    <div class="container">
        <h2>Unpaid Debts</h2>

        <table border="2">
            <thead>
                <tr>
                    <th>Amount</th>
                    <th>Is Paid</th>
                    <th>Product ID</th>
                    <th>Date of Session</th>
                    <th>Transaction Type</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Retrieve the list of dettes from the request attributes
                    Dette[] dettes = (Dette[]) request.getAttribute("dettes");

                    // Check if the dettes array is not null and has elements
                    if (dettes != null && dettes.length > 0) {
                        for (Dette dette : dettes) {
                %>
                            <tr>
                                <td><%= dette.getAmount() %></td>
                                <td><%= dette.getIsPayed() %></td>
                                <td><%= dette.getProduct().getName() %></td>
                                <td><%= dette.getDateSession() %></td>
                                <td><%= dette.getTypeTransaction() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No unpaid debts found.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>