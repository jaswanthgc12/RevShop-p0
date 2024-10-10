<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.2s;
        }

        .container:hover {
            transform: scale(1.02);
        }

        h2 {
            text-align: center;
            color: #4CAF50;
        }

        label {
            font-weight: bold;
            display: block;
            margin: 10px 0 5px;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 15px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        textarea:focus {
            border-color: #4CAF50;
            outline: none;
        }

        input[type="file"] {
            padding: 5px;
            margin: 10px 0;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s;
        }

        button:hover {
            background-color: #45a049;
            transform: scale(1.05);
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        .container {
            animation: fadeIn 0.5s ease-in;
        }

        .required {
            color: red;
            font-size: 0.9em;
        }

    </style>
</head>
<body>
    <div class="container">
        <h2>Add New Product</h2>
        <form action="AddProductServlet" method="post" enctype="multipart/form-data">
            <label for="productName">Product Name <span class="required">*</span>:</label>
            <input type="text" id="productName" name="productName" required>

            <label for="description">Description <span class="required">*</span>:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="price">Price <span class="required">*</span>:</label>
            <input type="text" id="price" name="price" required>

            <label for="discountedPrice">Discounted Price:</label>
            <input type="text" id="discountedPrice" name="discountedPrice">

            <label for="image">Upload Image:</label>
            <input type="file" id="image" name="image" accept="image/jpeg, image/png" required>

            <label for="quantity">Quantity <span class="required">*</span>:</label>
            <input type="number" id="quantity" name="quantity" required>

            <label for="category">Category <span class="required">*</span>:</label>
            <input type="text" id="category" name="category" required>

            <button type="submit">Add Product</button>
        </form>
    </div>
</body>
</html>
