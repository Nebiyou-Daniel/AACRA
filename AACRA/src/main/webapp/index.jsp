<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" type="image/x-icon" href="">
	<title>Welcome to AACRA!</title>
</head>
<body>
	<div class="hero">
		<div class="header">
			<ul>
				<li><a href="">Home</a></li>			
				<li><a href="">About</a></li>
				<li><a href="">Contact us</a></li>
			</ul>
		</div>
		
		<div>
			<h1>Welcome to AACRA!</h1>
			<p> A website that enables you to view criminal records!</p>
			<div>
				<button onclick="redirectToLoginPage()">Login</button>
				<button onclick="redirectToSignupPage()">Sign up</button>
			</div>
		</div>
	</div>
	
	
    <script>
        function redirectToLoginPage() {
            window.location.href = "pages/LoginPage.jsp";
        }
        function redirectToSignupPage() {
            window.location.href = "pages/SignupPage.jsp";
        }
    </script>
</body>
</html>