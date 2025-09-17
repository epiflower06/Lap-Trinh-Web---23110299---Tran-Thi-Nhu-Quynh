<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Category Form</title>
<style>
body {
	font-family: 'Segoe UI', sans-serif;
	background-color: #e8f5e9;
	margin: 0;
	padding: 0;
}

.container {
	margin: 30px auto;
	max-width: 600px;
	background: white;
	padding: 20px 30px;
	border-radius: 12px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	color: #2e7d32;
}

label {
	display: block;
	margin-top: 10px;
	font-weight: bold;
}

input, select {
	width: 100%;
	padding: 8px;
	margin-top: 5px;
	border: 1px solid #c8e6c9;
	border-radius: 6px;
}

button {
	margin-top: 15px;
	padding: 10px 15px;
	background: #66bb6a;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

button:hover {
	background: #388e3c;
}
</style>
</head>
<body>
	<div class="container">
		<h2>${action == 'edit' ? 'Edit' : 'Create'}Category</h2>

		<form method="post"
			action="${pageContext.request.contextPath}/category/${action == 'edit' ? 'update' : 'save'}"
			enctype="multipart/form-data">

			<c:if test="${action == 'edit'}">
				<input type="hidden" name="id" value="${category.categoryId}" />
			</c:if>

			<label>Name:</label> <input type="text" name="categoryname"
				value="${category.categoryname}" required /> <label>Image:</label>
			<input type="file" name="images" accept="image/*" />
			<c:if test="${action == 'edit' && category.images != null}">
				<p>Ảnh hiện tại:</p>
				<img src="${pageContext.request.contextPath}/${category.images}"
					width="120" />
			</c:if>

			<label>Status:</label> <select name="status">
				<option value="true" ${category.status ? "selected" : ""}>Còn
					hàng</option>
				<option value="false" ${!category.status ? "selected" : ""}>Hết
					hàng</option>
			</select>

			<c:if test="${user.roleId == 3}">
				<label>Owner:</label>
				<select name="userId">
					<c:forEach var="u" items="${users}">
						<option value="${u.userId}"
							${category.user != null && category.user.userId == u.userId ? "selected" : ""}>
							${u.fullname}</option>
					</c:forEach>
				</select>
			</c:if>

			<button type="submit">Save</button>
		</form>

	</div>
</body>
</html>
