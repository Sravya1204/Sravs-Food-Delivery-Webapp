<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sravya_Foods</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Arial', sans-serif;
      background-image: url('images/res3.jpg'); 
      background-size: cover;
      background-position: center;
      text-align: center;
      color: #fff;
    }
    .container {
      position: relative;
      height: 100vh;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
    h1 {
    font-family: inherit;
      font-size: 4em;
      font-weight: bold;
      color: #ff4d4d;
      margin-bottom: 10px;
      margin-top: -100px; 
      text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.7); 
    }
    p {
      font-size: 1.8em;
      font-weight: 300;
      color: #f3f3f3; 
      margin: 10px 0;
      text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.6);
    }
    .button {
      padding: 15px 40px;
      font-size: 1.3em;
      font-weight: bold;
      background: linear-gradient(45deg, #ff7f50, #ff4500); 
      color: #fff;
      text-decoration: none;
      border: none;
      border-radius: 10px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    .button:hover {
      background: linear-gradient(45deg, #ff6347, #ff2e00);
      transform: scale(1.1);
      box-shadow: 0 6px 12px rgba(0, 0, 0, 0.5);
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>🛵 NomNomNow 🛵</h1>
    <p>Serving Happiness on Every Plate!</p></br>
    <a href="home" class="button">Let’s Feast!</a>
  </div>
</body>
</html>
