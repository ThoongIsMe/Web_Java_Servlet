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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<script src="https://unpkg.com/ionicons@latest/dist/ionicons.js"></script>


<style>
.title-cell {
	color: #6699FF;
	font-weight: bold;
	padding: 12px;
}

.title-cell:hover {
	color: #0066FF;
	cursor: pointer;
}
</style>


</head>

<body>
	<jsp:include page="ViewSinhVien/Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="ViewSinhVien/leftNavQTSV.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="container col-md-5 col-md-offset-3" style="overflow: auto">
		<form action="<%=request.getContextPath()%>/DoiMatKhauSV" method="post">
			<p style="color:red">${requestScope.ms}</p>
			<p style="color:green">${requestScope.ms1}</p>
			<div class="form-group">
				<label for="uname">Mật khẩu cũ:</label> <input type="password"
					class="form-control" id="password" placeholder="Mật khẩu cũ"
					name="password" required />
				
			</div>

			<div class="form-group">
				<label for="uname">Mật khẩu mới:</label> <input type="password"
					class="form-control" id="newpassword" placeholder="Mật khẩu mới"
					name="newpassword" required />
			</div>
			<div class="form-group">
				<label for="uname">Nhập lại mật khẩu mới:</label> <input type="password"
					class="form-control" id="repassword" placeholder="Nhập lại mật khẩu mới"
					name="repassword" required />
			</div>
			<div class="d-flex flex-row-reverse">
				<button type="button" id="homeButton" class="btn btn-primary ">Hủy</button>
				<button type="submit" id="changePassword" class="btn btn-primary mr-2">Đổi mật khẩu</button>	
			</div>
		</form>
	</div>
	</main>

	<jsp:include page="ViewSinhVien/footer.jsp"></jsp:include>
	<script>
		function showContent(id, content) {
			document.getElementById("content").innerHTML = "<p><b>" + content
					+ "</b></p>";
		}
	</script>
	<script>
    document.getElementById("homeButton").addEventListener("click", function() {
        window.location.href = "Thongtincanhan"; 
    });
	</script>

</body>

</html>