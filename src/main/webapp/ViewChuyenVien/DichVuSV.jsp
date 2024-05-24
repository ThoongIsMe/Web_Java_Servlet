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

<script type="text/javascript">
    <%@include file="/js/noti.js" %>
</script>
    <style type="text/css">
        <%@include file="/css/noti.css" %>
        
    </style>

<style>
.edit-link {
	background-color: #8F9BE3;
	color: white;
	padding: 5px;
	border-radius: 10px;
}

.edit-link:hover {
	background-color: #6072E1;
	color: white !important;
	text-decoration: none;
}

.delete-link:hover {
	background-color: #FC3B0E;
	color: white !important;
	text-decoration: none;
}

.delete-link {
	background-color: #AD481B;
	color: white;
	padding: 5px;
	border-radius: 10px;
}
</style>

</head>

<body>
	<c:set var="selectedId" scope="request" />

	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="leftNavQTSV.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Dịch Vụ Sinh Viên</h1>

			<div class="notifications toasts"></div>

			<div class="notifications  toasts1"></div>
			<button id="success"></button>
			<button id="error"></button>
			<!-- Data table -->
			<div class="container">
				<hr />
				<br />
				<c:if test="${not empty errorMessage}">
					<div
						class="alert ${errorMessageSuccess ? 'alert-success' : 'alert-danger'}"
						role="alert">${errorMessage}</div>
				</c:if>

				<table class="table table-bordered text-center">
					<thead class="table-primary ">
						<tr>

							<th>Tên dịch vụ</th>
							<th>Dịa điểm nhận</th>
							<th>Trạng thái</th>
							<th>Thực Hiện</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${listDVSV}">
							<c:if test="${i.tinhtrang == 1 || i.tinhtrang == 2}">
								<tr onclick="setGiayId('${i.maloaigiayString}')">
									<td><c:out value="${i.tenloaigiayString}" /></td>
									<td>A1-203</td>
									<c:choose>
										<c:when test="${i.tinhtrang == 1}">
											<td>Đang hoạt động</td>
										</c:when>
										<c:otherwise>
											<td>Đang bảo trì</td>
										</c:otherwise>
									</c:choose>


									<td><a  href="LoadDV?pid=${i.maloaigiayString}"
										class="edit-link">Chỉnh Sửa</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
										href="" class="delete-link" data-toggle="modal"
										data-target="#XacNhan">Xóa</a> &nbsp;&nbsp;&nbsp;&nbsp;
								</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>
				<div class="modal fade" id="addProductModal"
					style="margin-top: 100px;">
					<div class="modal-dialog">
						<div class="modal-content">
							<form action="AddDichVu" method="get" id="primaryKeyForm">
								<div class="modal-header">
									<h4 class="modal-title">Thêm Dịch Vụ</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label for="inputValue">Mã Dịch Vụ</label> <input
											id="inputValue" oninput="checkInput()" name="madv"
											type="text" class="form-control" value="${madvmoi}" readonly required>
										<p id="result"></p>
									</div>
									<div class="form-group">
										<label>Tên Dịch Vụ</label> <input name="tendv" type="text"
											class="form-control" required>
									</div>
									<div class="form-group">
										<label>Tình Trạng</label> <select name="tinhTrang"
											class="form-select" required>
											<option value="1">Đang hoạt động</option>
											<option value="2">Đang bảo trì</option>
										</select>
									</div>
								</div>
								<div class="modal-footer">
									<input onclick="simulateButtonClick('error')" type="button" class="btn btn-default"
										data-dismiss="modal" value="Hủy"> <input onclick="simulateButtonClick('success')" type="submit"
										class="btn btn-success" value="Thêm">

								</div>
							</form>
						</div>
					</div>
				</div>





				<div class="modal fade" id="XacNhan" style="margin-top: 100px;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Cảnh Báo</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">

								<div class="form-group">
									<label>BẠN CÓ MUỐN XÓA DỊCH VỤ NÀY KHÔNG</label>
								</div>
							</div>
							<div class="modal-footer">
								<input onclick="simulateButtonClick('error')" type="button" class="btn btn-default"
									data-dismiss="modal" value="Hủy"> <a onclick="simulateButtonClick('success')" href="#"
									id="confirmDelete" class="btn btn-success"
									style="background-color: #AD481B;"><b>XÓA</b></a>


							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="idGiayInput" />
				<!-- Action buttons -->
				<div class="container text-right">
					<form action="AddDichVu" method="get">
						<button id="addServiceButton" type="button" name="buttonNamee"
							class="btn btn-info" data-toggle="modal"
							data-target="#addProductModal">Thêm dịch vụ</button>
					</form>


				</div>



			</div>
		</div>



	</main>
	<script>
			function simulateButtonClick(buttonId) {
				// Simulate button click based on the provided buttonId
				document.getElementById(buttonId).click();
			}
		</script>
	
	<script>
		function setGiayId(idGiay) {
			// Thiết lập giá trị idGiay vào một biến JavaScript
			document.getElementById('idGiayInput').value = idGiay;
			document.getElementById('confirmDelete').href = "DeleteDVSV?pid="
					+ idGiay;

		}
	</script>
	<script>
		// Use JSTL to convert Java List to JSON array
		var listDV = $
		{
			listDVSV
		}; // Assuming "listDVSV" is the attribute name set in the servlet

		// Use JavaScript to iterate over the JSON array and display the values
		for (var i = 0; i < listDV.length; i++) {
			var service = listDV[i];
			console.log("Service ID: " + service.id + ", Service Name: "
					+ service.name);
			// Replace the above log statement with your code to display the service information in the desired format
		}
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>