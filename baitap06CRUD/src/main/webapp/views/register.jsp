<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style>
body {
	font-family: 'Segoe UI', sans-serif;
	background: #f1f8e9;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 350px;
	background: white;
	padding: 25px;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

h2 {
	text-align: center;
	color: #2e7d32;
}

input {
	width: 100%;
	padding: 10px;
	margin-top: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

button {
	width: 100%;
	padding: 10px;
	margin-top: 15px;
	border: none;
	border-radius: 6px;
	background: #42a5f5;
	color: white;
	font-weight: bold;
	cursor: pointer;
}

button:hover {
	background: #1e88e5;
}

.switch-link {
	text-align: center;
	margin-top: 15px;
}

.switch-link a {
	text-decoration: none;
	color: #66bb6a;
	font-weight: bold;
}

.switch-link a:hover {
	text-decoration: underline;
}

.error {
	color: red;
	text-align: center;
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Register</h2>
		<form method="post"
			action="${pageContext.request.contextPath}/register">
			<input type="text" name="fullname" placeholder="Full Name" required />
			<input type="text" name="username" placeholder="Username" required />
			<input type="password" name="password" placeholder="Password"
				required /> <input type="password" name="confirmPassword"
				placeholder="Confirm Password" required />
			<button type="submit">Register</button>
		</form>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>

		<div class="switch-link">
			Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng
				nhập</a>
		</div>
	</div>
</body>
</html>
