<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration - RevShop</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #667eea, #764ba2);
            font-family: 'Arial', sans-serif;
        }

        .register-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            margin-bottom: 5px;
        }

        .form-group input, 
        .form-group textarea {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        textarea {
            resize: none;
            height: 100px;
        }

        .btn-primary {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-primary:hover {
            background: linear-gradient(to right, #6a5cde, #7c58a7);
        }

        .message {
            margin-bottom: 15px;
            text-align: center;
        }

        .errorMessage {
            color: red;
        }

        .successMessage {
            color: green;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>User Registration</h2>

        <!-- Display error message -->
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
            <p class="message errorMessage"><%= errorMessage %></p>
        <% } %>

        <!-- Display success message -->
        <% String successMessage = (String) request.getAttribute("successMessage"); %>
        <% if (successMessage != null) { %>
            <p class="message successMessage"><%= successMessage %></p>
        <% } %>

        <!-- Registration Form -->
        <form action="RegistedController" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="phone_number">Phone Number:</label>
                <input type="number" id="phone_number" name="phone_number" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" required></textarea>
            </div>

            <button type="submit" class="btn-primary">Register</button>
        </form>
    </div>
</body>
</html>
