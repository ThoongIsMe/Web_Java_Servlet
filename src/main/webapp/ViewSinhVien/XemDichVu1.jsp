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

.table-container td {
	max-width: 200px;
	word-wrap: break-word;
}

.table-container td ion-icon {
	position: relative;
	/* Reset the position property */
	z-index: 1;
	/* Ensure the ion-icon is behind other elements */
}

.title-cell {
	color: #6699FF;
	font-weight: bold;
	padding: 12px;
}

.title-cell:hover {
	color: #0066FF;
	cursor: pointer;
}

textarea {
	min-width: 960px;
	min-height: 100px;
	vertical-align: top;
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
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">
		<jsp:include page="leftNavQTSV.jsp"></jsp:include>
		<!-- Main content of web -->
		<form action="XinGiay" method="post" class="col-md-10"
			onsubmit="return showAlert()">
			<div id="main-content">
				<h1 class=" text-center">Đăng ký dịch vụ</h1>
				<div class="container">
					<div class="container row ">
						<div class="col-md-1"></div>
						<div class="col-md-11 notification-container">
							<h2>Thông báo</h2>

							Quy trình nhận Giấy xác nhận đã đăng ký:
							<ol>
								<li>Sinh viên nhận Giấy kể từ <strong
									class="underlined  red-text">ngày nhận thông báo</strong>.
								</li>
								<li>Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh
									viên <strong class="red-text"> A1-203 </strong>để nhận giấy.
									Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các
									ngày từ thứ hai đến thứ sáu và sáng thứ bảy.
								</li>
								<li>Sinh viên cung cấp <strong class="red-text">
										Mã sinh viên và họ tên </strong> cho nhân viên trả giấy.
								</li>
							</ol>

						</div>
					</div>

				</div>
				<!-- Data table -->
				<div class="container">
					<div class="container row ">
						<div class="col-md-1"></div>
						<div class="col-md-6">
							<br /> <label><strong>Loại giấy:</strong></label> <select
								id="loaiGiayXN" name="loaiGiayXN">
								<c:forEach var="loaiGiay" items="${CacLoaiGiay_U}">
									<option value="${loaiGiay.maloaigiayString}">${loaiGiay.tenloaigiayString}</option>
								</c:forEach>
							</select>

						</div>
						<div class="col-md-2">
							<br /> <label><strong>Số lượng:</strong></label> <select
								id="quantity" name="soluong">
								<% for (int i = 1; i <= 5; i++) { %>
								<option value="<%= i %>"><%= i %></option>
								<% } %>
							</select>
						</div>
						<div class="col-md-3"></div>
					</div>


				</div>
				<div class="container">
					<div class="container row">
						<div class="col-md-1"></div>
						<div class="col-md-11">
							<br /> <label><strong>Lý do:</strong></label> <br>
							<textarea name="Lydo" required></textarea>

						</div>
					</div>
					<br />
				</div>
				<div class="container">
					<div class="container row">
						<div class="col-md-10"></div>
						<div class="col-md-2">
							<button type="submit" class="btn btn-primary">Đăng ký</button>

						</div>

					</div>
				</div>
				<br />
				<div class="container">
					<div class="container row">
						<div class="col-md-1"></div>
						<div class="col-md-11">
							<h2>
								<strong>Lịch sử:</strong>
							</h2>
						</div>

					</div>
				</div>
				<div class="container">
					<div class="container row">
						<div class="col-md-1"></div>
						<div class="col-md-11 table-container">

							<table class="table table-bordered text-center">
								<thead class="table-primary fixed-header ">
									<tr>
										<th>Thời gian xin</th>
										<th>Lý do</th>
										<th>Tên loại giấy</th>
										<th>Số lượng</th>
										<th>Thời gian nhận</th>
									</tr>
								</thead>
								<tbody>
								<tbody>

									<c:forEach var="i" items="${YeuCauSV}">

										<tr>

											<td><fmt:parseDate var="localDate"
													value="${i.giayxacnhan.getThoiGianXin()}"
													pattern="yyyy-MM-dd" /> <fmt:formatDate
													value="${localDate}" pattern="dd/MM/yyyy"
													var="formattedDate" /> <c:out value="${formattedDate}" /></td>
											<td><c:out value="${i.giayxacnhan.getLyDo()}" /></td>
											<td><c:out
													value="${i.cacLoaiGiay.getTenloaigiayString()}" /></td>
											<td><c:out value="${i.giayxacnhan.getSL()}" /></td>
											<td><fmt:parseDate var="localDate"
													value="${i.giayxacnhan.getThoiGianNhan()}"
													pattern="yyyy-MM-dd" /> <fmt:formatDate
													value="${localDate}" pattern="dd/MM/yyyy"
													var="formattedDate" /> <c:out value="${formattedDate}" /></td>
										</tr>
									</c:forEach>
								</tbody>
								</tbody>

							</table>
						</div>

					</div>
				</div>
				<br> 
				<br>
			</div>
		</form>
		<script>
			        function showAlert() {
			            // Hiển thị cảnh báo
			            
			            alert("Đăng ký thành công!");
						window.location.href="/qlsv/XemDichVu"
			            // Trả về true để tiếp tục submit form, hoặc false để ngăn chặn submit
			            //	return true;
			        }
   		 </script>
	</main>

	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>