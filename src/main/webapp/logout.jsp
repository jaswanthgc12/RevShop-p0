<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #E6F9E6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .logout-message {
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="logout-message">
        <h1>You have been logged out</h1>
        <p>Thank you for visiting RevShop!</p>
        <a href="login.jsp" class="btn btn-primary">Login Again</a>
        <a href="menu.jsp" class="btn btn-secondary">Go to Home</a>
    </div>

</body>
</html>
