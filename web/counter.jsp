<%@ page import="model.pompe.Pompiste" %>
<%@ page import="model.pompe.Pompe" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <h1>Gas Station Counter</h1>

  <form action="" method="post">
    <p>
      Amount: 
      <input type="number" name="amount" placeholder="Enter amount" required>
    </p>

    <p>
      Date session: 
      <input type="date" name="date-session" required>
    </p>

    <p>
      Pompistes: 
      <select name="pompiste" id="pompiste-select">
        <% 
          Pompiste[] pompistes = (Pompiste[]) request.getAttribute("pompistes");
          if (pompistes != null) {
            for (Pompiste pompiste : pompistes) {
        %>
              <option value="<%= pompiste.getId() %>"><%= pompiste.getName() %></option>
        <% 
            }
          } else {
        %>
          <option value="">No Pompistes available</option>
        <% 
          }
        %>
      </select>
    </p>

    <p>
      Pompes: 
      <select name="pompe" id="pompe-select">
        <% 
          Pompe[] pompes = (Pompe[]) request.getAttribute("pompes");
          if (pompes != null) {
            for (Pompe pompe : pompes) {
        %>
              <option value="<%= pompe.getId() %>"><%= pompe.getName() %></option>
        <% 
            }
          } else {
        %>
          <option value="">No Pompes available</option>
        <% 
          }
        %>
      </select>
    </p>

    <p>
      <p>
        TRUE:
        <input type="radio" name="isPayed" value="TRUE"> 
      </p>

      <p>
        FALSE:
        <input type="radio" name="isPayed" value="FALSE">
      </p>
    </p>

    <input type="submit" value="Submit">
  </form>
</body>
</html>