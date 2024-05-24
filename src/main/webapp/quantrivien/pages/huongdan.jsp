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
	text-decoration: underline;
	/* Thêm gạch chân cho văn bản có class underlined */
}

.notification-container {
	border: 2px solid #000; /* Màu và kích thước border */
	padding: 20px; /* Khoảng cách giữa nội dung và border */
}
</style>


</head>

<body>
	<jsp:include page="../components/header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">
		<jsp:include page="../components/sidebar.jsp"></jsp:include>
		<!-- Main content of web -->
		<div id="main-content">
			<h1 class=" text-center">Hướng dẫn sử dụng dịch vụ</h1>
			<div class="container">
				<div class="container row ">
					<div class="col-md-1"></div>
					<div class="col-md-11 notification-container">
						<ul>
							<li>Trong <strong class="underlined  red-text">Quản
									lý sinh viên </strong>để thực hiện thêm sinh viên từ file:
							</li>
							<li><strong class="underlined  red-text">Bước 1: </strong>chọn
								file excel có đuôi xls, xlsx từ máy theo mẫu danh sách từ nhà
								trường gửi</li>
							<li><strong class="underlined  red-text">Bước 2: </strong>nhấn
								nút Import sẽ thực hiện đọc file và hiển thị thông tin sơ bộ về
								các sinh viên</li>
							<li><strong class="underlined  red-text">Bước 3: </strong>quản
								trị viên thực hiện kiểm tra sơ bộ lại để quyết định hủy để chỉnh
								sửa lại file hoặc chọn nhập danh sách sẽ chính thức thực hiện
								việc import vào cơ sở dữ liệu</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>