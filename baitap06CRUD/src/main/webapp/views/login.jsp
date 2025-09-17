<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
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
	background: #66bb6a;
	color: white;
	font-weight: bold;
	cursor: pointer;
}

button:hover {
	background: #388e3c;
}

.switch-link {
	text-align: center;
	margin-top: 15px;
}

.switch-link a {
	text-decoration: none;
	color: #42a5f5;
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
		<h2>Login</h2>
		<form method="post" action="${pageContext.request.contextPath}/login">
			<input type="text" name="username" placeholder="Username" required />
			<input type="password" name="password" placeholder="Password"
				required />
			<button type="submit">Login</button>
		</form>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>

		<div class="switch-link">
			Chưa có tài khoản? <a
				href="${pageContext.request.contextPath}/register">Đăng ký</a>
		</div>
	</div>
</body>
</html>
