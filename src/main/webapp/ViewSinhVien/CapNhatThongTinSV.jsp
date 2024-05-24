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
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="leftNavQTSV.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="container col-md-5 col-md-offset-3" style="overflow: auto">
		<form action="<%=request.getContextPath()%>/CapNhatThongTinSV" method="post">
			<div class="form-group">
				<label for="uname">Số điện thoại:</label> <input type="text"
					class="form-control" id="sdt" 
					name="sdt" value= "${UserSV.SDT}" />
				
			</div>

			<div class="form-group">
				<label for="uname">Email:</label> <input type="text"
					class="form-control" id="email"
					name="email" value= "${UserSV.email}" />
			</div>
			<div class="form-group">
				<label for="uname">Địa chỉ:</label> <input type="text"
					class="form-control" id="diaChi" 
					name="diaChi" value= "${UserSV.diaChiSV}" />
			</div>
			<div class="d-flex flex-row-reverse">
				<button type="button" id="homeButton" class="btn btn-primary ">Hủy</button>
				<button type="submit" id="changeInfor" class="btn btn-primary mr-2">Cập nhật</button>	
			</div>
		</form>
	</div>
	</main>

	<jsp:include page="footer.jsp"></jsp:include>
	<script>
    document.getElementById("homeButton").addEventListener("click", function() {
        window.location.href = "../Thongtincanhan"; 
    });
	</script>

</body>

</html>