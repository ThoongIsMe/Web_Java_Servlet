<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>QuanLySinhVien</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<script src="https://unpkg.com/ionicons@latest/dist/ionicons.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />

<script type="text/javascript">
    <%@include file="/js/noti.js" %>
</script>
    <style type="text/css">
        <%@include file="/css/noti.css" %>
        
    </style>

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

.center-form {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}
</style>


</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">
		<jsp:include page="leftNavQTSV.jsp"></jsp:include>
		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Thông tin cá nhân</h1>

			<div class="notifications toasts"></div>

			<div class="notifications  toasts1"></div>
			<button id="success"></button>
			<button id="error"></button>
			<div class="container ">
				<!-- Data table -->
				<div class="row ml-5">
					<div class="col-md-8">
						<hr />
						<br />
						<table class="table table-bordered">
							<thead class="table-primary  text-center ">
								<tr>
									<th colspan="2">Thông tin CTV</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><label>Mã số CTV: </label></td>
									<td>${UserCTSV.maCV}</td>
								</tr>
								<tr>
									<td><label>Họ và tên: </label></td>
									<td>${UserCTSV.tenCV}</td>
								</tr>

								<tr>
									<td><label>Email: </label></td>
									<td>${UserCTSV.email}</td>
								</tr>
								<tr>
									<td><label>Số điện thoại: </label></td>
									<td>${UserCTSV.soDienThoai}</td>
								</tr>
								<tr>
									<td><label>Địa chỉ </label></td>
									<td>${UserCTSV.diaChi}</td>
								</tr>
								<tr>
									<td><label>Ngày bắt đầu làm: </label></td>
									<td><fmt:parseDate var="localDate"
											value="${UserCTSV.ngayBatDauLam}" pattern="yyyy-MM-dd" /> <fmt:formatDate
											value="${localDate}" pattern="dd/MM/yyyy" var="formattedDate" />
										<c:out value="${formattedDate}" /></td>
								</tr>

							</tbody>

						</table>

					</div>
					<div class="col-md-3">
						<hr />
						<br />
						<div class="d-flex justify-content-center align-items-center">
							<img src="${UserSV.anhTheLink}"
								style="border-color: Navy; border-width: 3px; border-style: Dashed; height: 256px; width: 186px;"
								alt="...">
						</div>
						<br />

						<table class="table">
							<tbody>

								<tr>
									<td><a href="" data-toggle="modal"
										data-target="#DoiMatKhau">Đổi mật khẩu</a></td>
									<td><a href="" data-toggle="modal" data-target="#CapNhat">Cập
											nhât</a></td>
								</tr>
							</tbody>
						</table>
					</div>



					<div class="modal fade" id="DoiMatKhau" style="margin-top: 100px;">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="<%=request.getContextPath()%>/DoiMatKhauCTV"
									method="POST" id="primaryKeyForm">
									<div class="modal-header">
										<h4 class="modal-title">ĐỔI MẬT KHẨU</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label for="uname">Mật khẩu cũ:</label> <input
												type="password" class="form-control" id="password"
												placeholder="Mật khẩu cũ" name="password" required />
											<p id="result"></p>
										</div>
										<div class="form-group">
											<label for="uname">Mật khẩu mới:</label> <input
												type="password" class="form-control" id="newpassword"
												placeholder="Mật khẩu mới" name="newpassword" required />
										</div>
										<div class="form-group">
											<label for="uname">Nhập lại mật khẩu mới:</label> <input
												type="password" class="form-control" id="repassword"
												placeholder="Nhập lại mật khẩu mới" name="repassword"
												required />
										</div>
									</div>
									<div class="modal-footer">
										<input onclick="simulateButtonClick('error')" type="button" class="btn btn-default"
											data-dismiss="modal" value="Hủy"> <input onclick="simulateButtonClick('success')"
											type="submit" class="btn btn-success" value="Đổi mật khẩu">

									</div>
								</form>
							</div>
						</div>
					</div>




					<div class="modal fade" id="CapNhat" style="margin-top: 100px;">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="<%=request.getContextPath()%>/CapNhatThongTinCTV"
									method="POST" id="primaryKeyForm">
									<div class="modal-header">
										<h4 class="modal-title">CẬP NHẬT THÔNG TIN</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label for="uname">Số điện thoại:</label> <input type="text"
												class="form-control" id="sdt" name="sdt"
												value="${UserCTSV.soDienThoai}" />
											<p id="result"></p>
										</div>
										<div class="form-group">
											<label for="uname">Email:</label> <input type="text"
												class="form-control" id="email" name="email"
												value="${UserCTSV.email}" />
										</div>
										<div class="form-group">
											<label for="uname">Địa chỉ:</label> <input type="text"
												class="form-control" id="diaChi" name="diaChi"
												value="${UserCTSV.diaChi}" />
										</div>
									</div>
									<div class="modal-footer">
										<input onclick="simulateButtonClick('error')" type="button" class="btn btn-default"
											data-dismiss="modal" value="Hủy"> <input
											type="submit" onclick="simulateButtonClick('success')" class="btn btn-success" value="Cập Nhật">

									</div>
								</form>
							</div>
						</div>
					</div>


				</div>


			</div>
		</div>

		<script>
			function simulateButtonClick(buttonId) {
				// Simulate button click based on the provided buttonId
				document.getElementById(buttonId).click();
			}
		</script>

	</main>

	<jsp:include page="footer.jsp"></jsp:include>



</body>

</html>