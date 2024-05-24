<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>QuanLySinhVien</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
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

/* Optional: Add some styles to center the submit button */
.login-container button {
	margin-top: 10px;
}

#message, #message1 {
	display: none;
	color: #000;
	position: relative;
}

#message p {
	padding: 10px 35px;
	font-size: 18px;
}

.valid {
	display: none;
}

.invalid {
	color: red;
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
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="navbarNav">
			</div>
		</div>
	</nav>


	<div class="container login-container col-md-5 col-md-offset-3"
		style="overflow: auto">
		<div>
			<h3
				class="mb-3 login-container col-md-5 col-md-offset-3 mt-0 text-center">Reset
				Mật khẩu</h3>
		</div>

		<form action="<%=request.getContextPath()%>/LayLaiMatKhauSV"
			method="post">
			
			<c:if test="${not empty errorMessageAc}">
				<div
					class="alert ${errorMessageSuccessAc ? 'alert-success' : 'alert-danger'}"
role="alert">${errorMessageAc}</div>	
			</c:if>
			<div class="container" style="padding: 0;">
				<div class="form-group">
					<label for="uname">Email sinh viên:</label> <input name="emailSV"
						placeholder="example@domain.com" type="email" class="form-control"
						id="exam2pleInputEmail1"
						pattern="/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/"
						required>


				</div>

				<!-- Other form elements... -->

				<div class="d-flex justify-content-end mt-2">
					<button type="button" id="homeButton" class="btn btn-secondary">Hủy</button>
					<button type="submit" id="a" class="btn btn-primary ml-2">Lấy
						Lại Mật Khẩu</button>
				</div>
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




<script>
	document.getElementById("homeButton").addEventListener("click", function() {
		window.location.href = "login.jsp";
	});

	var myInputEmail = document.getElementById("exam2pleInputEmail1");
	var email = document.getElementById("email")
	var ionIcon41 = document.querySelector(".icon11");

	myInputEmail.onfocus = function() {
		document.getElementById("message1").style.display = "block";
		document.getElementById("message").style.display = "none";
	}

	myInputEmail.onkeyup = function() {
		const isValid = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
				.test(myInputEmail.value);
		if (isValid) {
			email.classList.remove("invalid");
			email.classList.add("valid");
			ionIcon41.setAttribute("name", "checkmark-outline");
		} else {
			email.classList.remove("valid");
			email.classList.add("invalid");
			ionIcon41.setAttribute("name", "close-outline");
		}
	}
</script>
</html>