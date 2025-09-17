<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
vn.iotstar.entity.User user = (vn.iotstar.entity.User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<title>Manager Home</title>
<style>
body {
	font-family: 'Segoe UI', sans-serif;
	background-color: #e8f5e9; /* xanh pastel */
	margin: 0;
	padding: 0;
}

.header {
	background-color: #66bb6a;
	padding: 15px 30px;
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.header h2 {
	margin: 0;
}

.header a {
	color: white;
	text-decoration: none;
	margin-left: 15px;
	font-weight: bold;
}

.container {
	margin: 30px auto;
	max-width: 800px;
	background: white;
	padding: 20px 30px;
	border-radius: 12px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 15px;
}

table th, table td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #c8e6c9;
}

table th {
	background-color: #a5d6a7;
	color: #2e7d32;
}

.btn {
	padding: 6px 12px;
	border-radius: 6px;
	text-decoration: none;
	color: white;
	font-size: 14px;
	margin-right: 5px;
}

.btn-edit {
	background-color: #42a5f5;
}

.btn-delete {
	background-color: #e53935;
}

.btn-add {
	background-color: #66bb6a;
	margin-bottom: 12px;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="header">
		<h2>Manager: ${user.fullname}</h2>
		<div>
			<a href="${pageContext.request.contextPath}/category">My
				Categories</a> <a href="${pageContext.request.contextPath}/logout">Logout</a>
		</div>
	</div>

	<div class="container">
		<h3>Danh sách Category</h3>
		<c:forEach var="entry" items="${groupedCategories}">
			<h4>${entry.key.fullname}</h4>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Category Name</th>
						<th>Image</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${entry.value}">
						<tr>
							<td>${c.categoryId}</td>
							<td>${c.categoryname}</td>
							<td><c:if test="${not empty c.images}">
									<img src="${c.images}" style="height: 40px; border-radius: 6px" />
								</c:if></td>
							<td><c:out value="${c.status ? 'Còn hàng' : 'Hết hàng'}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
	</div>

</body>
</html>
