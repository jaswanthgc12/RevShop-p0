<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RevShop - Home</title>
    <link rel="stylesheet" href="css/styles.css"> 
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            transition: all 0.3s ease;
        }

        html, body {
            height: 100%; 
        }

        body {
            display: flex;
            flex-direction: column; 
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; 
            color: #333;
        }

        header {
            background-color: #007bff; 
            color: white;
            padding: 20px 0;
            text-align: center;
            animation: fadeIn 1s; /* Header fade-in effect */
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
        }

        nav {
            text-align: center;
            margin-top: 20px;
        }

        nav ul {
            list-style-type: none;
            padding: 0;
        }

        nav ul li {
            display: inline;
            margin: 0 15px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        nav ul li a:hover {
            background-color: #0056b3;
        }

        main {
            flex: 1;
            padding: 20px;
            margin: 20px auto;
            max-width: 1000px; 
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .content-wrapper {
            display: flex; 
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap; 
        }

        .text-content {
            flex: 2; 
            padding-right: 20px; 
        }

        .button-content {
            flex: 1; 
            display: flex;
            flex-direction: column; 
            align-items: flex-end; 
        }

        .button-content .btn {
            margin-bottom: 10px; 
            padding: 10px 20px;
            font-size: 1.1em;
            color: white;
            background-color: #28a745; 
            border: none;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s, transform 0.3s; 
        }

        .button-content .btn:hover {
            background-color: #218838; 
            transform: translateY(-2px); 
        }

        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 0;
            width: 100%;
            position: relative;
        }

        footer p {
            margin: 0;
        }

        /* Media queries for responsiveness */
        @media (max-width: 800px) {
            .content-wrapper {
                flex-direction: column; /* Stack content vertically on small screens */
                align-items: flex-start; /* Align to the left */
            }

            .button-content {
                align-items: flex-start; /* Align buttons to the left on small screens */
            }
        }
    </style>
</head>
<body>

    <header>
        <h1>Welcome to RevShop</h1>
        <nav>
            <ul>
                <li><a href="menu.jsp">Buyer Dashboard</a></li>
                <li><a href="sellerDashboard.jsp">Seller Dashboard</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <div class="content-wrapper">
            
            <div class="text-content">
                <h2>Your One-Stop Shop for Everything!</h2>
                <p>Explore a wide range of products and enjoy a seamless shopping experience.</p>
                <p>Whether you are looking for the latest gadgets, 
                <br>fashionable clothing, or home essentials, 
                <br>RevShop has it all!</p>
                <p>Join us and discover amazing deals and discounts every day!</p>
            </div>

            
            <div class="button-content">
                <a href="register.jsp" class="btn">Register as Buyer</a>
                <a href="sellerRegistration.jsp" class="btn">Register as Seller</a>
            </div>
        </div>
    </main>

    <footer>
        <p>&copy; 2024 RevShop. All rights reserved.</p>
    </footer>

</body>
</html>
