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
.table-container {
	max-height: 370px;
	overflow-y: auto;
}

.fixed-header th {
	position: sticky;
	top: 0;
	background-color: #b8daff;
	z-index: 2;
	/* Ensure the th is above ion-icon */
}

.red-text {
    color: red; /* Đặt màu đỏ cho văn bản có class red-text */
}

.underlined {
    text-decoration: underline; /* Thêm gạch chân cho văn bản có class underlined */
}
.notification-container {
  border: 2px solid #000; /* Màu và kích thước border */
  padding: 20px; /* Khoảng cách giữa nội dung và border */
}

</style>


</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">
		<jsp:include page="leftNavQTSV.jsp"></jsp:include>
		<!-- Main content of web -->
			<div id="main-content">
				<h1 class=" text-center">Hướng dẫn sử dụng dịch vụ</h1>
				<div class="container">
					<div class="container row ">
						<div class="col-md-1"></div>
						<div class="col-md-11 notification-container">
							
						   

						        <ol>
						        	<li> <strong class="underlined  red-text">Trang chủ</strong> sẽ hiển thị các thông báo từ Phòng công tác sinh viên gửi cho Sinh viên</li>
						            <li> <strong class="underlined  red-text">Thông tin cá nhân</strong> sẽ hiển thị thông tin cá nhân của sinh viên, Sinh viên có thể thay đổi <strong>Mật khẩu</strong> hoặc <strong>Thông tin liên lạc</strong> ở đây</li>
						            <li>Sinh viên nhận đăng ký giấy xác nhận ở  <strong class="underlined  red-text">Dịch vụ sinh viên</strong>. Sau khi đăng ký, sinh viên vui lòng làm theo các <strong>Chỉ dẫn</strong></li>
						            
						        </ol>

						</div>
					</div>

				</div>		
				</div>


	</main>

	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>