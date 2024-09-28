<%@ page import="model.view.ravitaillement.Ravitaillement" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ravitaillement Data</title>
</head>
<body>
    <div class="container">
        <h2>Ravitaillement Data</h2>

        <table border="2">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Pompe Name</th>
                    <th>Date Ravitaillement</th>
                    <th>Total Ravitaillement</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Retrieve the list of ravitaillement data from the request attributes
                    Ravitaillement[] ravitaillements = (Ravitaillement[]) request.getAttribute("ravitaillements");

                    // Check if the ravitaillements array is not null and has elements
                    if (ravitaillements != null && ravitaillements.length > 0) {
                        for (Ravitaillement ravitaillement : ravitaillements) {
                %>
                            <tr>
                                <td><%= ravitaillement.getProduct().getName() %></td>
                                <td><%= ravitaillement.getPompe().getName() %></td>
                                <td><%= ravitaillement.getDateRavitaillement() %></td>
                                <td><%= ravitaillement.getTotalRavitaillement() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4">No ravitaillement data available.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>