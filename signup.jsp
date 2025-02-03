<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup</title>
    <link rel="stylesheet" href="./signUp.css">
</head>
<body>
    <div class="container">
        <h2>Signup</h2>

        <form action="signup" method="POST">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required /><br>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required /><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required /><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required /><br>

            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" required /><br>

            <label for="address">Address:</label>
            <textarea id="address" name="address" required></textarea><br>

            <label for="role">Role:</label>
            <select id="role" name="role">
                <option value="customer">Customer</option>
                <option value="admin">Admin</option>
            </select><br>

            <button type="submit">Create Account</button>
        </form>
    </div>
</body>
</html>
