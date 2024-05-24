<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>QuanLySinhVien</title>
<link rel="icon" type="image/x-icon" href="../resource/favicon.png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
</head>


<style>
.login-container {
	border: 1px solid #ccc; /* Add a border */
	padding: 20px; /* Add some padding */
	border-radius: 10px;
	/* Optional: Add border-radius for rounded corners */
	margin: auto; /* Center the container horizontally */
	max-width: 500px; /* Optional: Set a maximum width for the container */
	margin-top: 50px; /* Optional: Adjust the top margin */
}

/* Optional: Add some styles to center the form elements */
.login-container form {
	display: flex;
	flex-direction: column;
	align-items: center;
}

/* Optional: Add some styles to center the submit button */
.login-container button {
	margin-top: 10px;
}
</style>

<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #0187c6">
			<div class="d-flex" style="margin-left: 25%;">
				<div class="flex-shrink-0">
					<img class="navbar-brand" width="80px"
						src="https://seeklogo.com/images/H/hcmute-logo-7553D4CCA1-seeklogo.com.png"
						alt="logo_spkt">
				</div>
				<h3 class="flex-grow-1 ms-3 d-flex align-items-center">Trường
					đại học Sư Phạm Kỹ Thuật TP. Hồ Chí Minh</h3>
			</div>
		</nav>
	</header>


	<div class="container login-container col-md-5 col-md-offset-3"
		style="overflow: auto">
		<h3
			class="mb-3 login-container col-md-5 col-md-offset-3 mt-0 text-center ">Đăng
			Nhập</h3>
		<form action="<%=request.getContextPath()%>/Login" method="post">
			<c:if test="${not empty errorMessageAc}">
				<div
					class="alert ${errorMessageSuccessAc ? 'alert-success' : 'alert-danger'}"
					role="alert">${errorMessageAc}</div>
			</c:if>
			<div class="container" style="padding: 0;">
				<div class="row">
					<div class="col">
						<input type="radio" name="role" id="sinhvien" value="1" checked />
						<label for="sinhvien"> Sinh viên</label>
					</div>
					<div class="col">
						<input type="radio" name="role" id="chuyenvien" value="2" /> <label
							for="chuyenvien"> Chuyên viên</label>
					</div>
					<div class="col">
						<input type="radio" name="role" id="quantrivien" value="3" /> <label
							for="quantrivien"> Quản trị viên</label>
					</div>
				</div>


				<div class="form-group">
					<label for="uname">Tên đăng nhập:</label> <input type="text"
						class="form-control" id="username" placeholder="User Name"
						name="username" required />
				</div>

				<div class="form-group">
					<label for="uname">Mật khẩu:</label> <input type="password"
						class="form-control" id="password" placeholder="Password"
						name="password" required />
				</div>
				<div class="d-flex text-md-left custom-margin">
					<a class="nav-link active" href="QuenMatKhau.jsp">Quên mật khẩu</a>
				</div>

			</div>



			<div class="d-flex flex-row-reverse">
				<button type="submit" class="btn btn-primary">Đăng nhập</button>
			</div>
		</form>
	</div>
	<style>
.footer {
	position: fixed;
	bottom: 0;
	width: 100%;
	height: 40px;
	background-color: #0187c6;
}
</style>

	<footer class="footer font-small black">
		<div class="footer-copyright text-center py-2" style="color: white">
			© 2023 Copyright: Group 10</div>
	</footer>
</body>
</html>