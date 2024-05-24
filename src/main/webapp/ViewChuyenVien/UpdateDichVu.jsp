	

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap CRUD Data Table for Database with Modal Form</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />

<style>
</style>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="leftNavQTSV.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">

			<div class="container">
				<div class="table-wrapper"></div>
				<div id="editEmployeeModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<form action="UpdateDichVu" method="post">
								<div class="modal-header">
									<h4 class="modal-title">Chỉnh Sửa Dịch Vụ</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label>Mã Dịch Vụ</label> <input
											value="${listDV.maloaigiayString}" name="madv" type="text"
											class="form-control" readonly required>

									</div>
									<div class="form-group">
										<label>Tên Dịch Vụ</label> <input
											value="${listDV.tenloaigiayString}" name="tendv" type="text"
											class="form-control" required>
									</div>
									<div class="form-group">
										<label>Tình Trạng</label> <select name="tinhTrang"
											class="form-select" required>
											<c:choose>
												<c:when test="${listDV.tinhtrang eq 1}">
													<option value="1" selected>Đang hoạt động</option>
													<option value="2">Đang bảo trì</option>
												</c:when>
												<c:when test="${listDV.tinhtrang eq 2}">
													<option value="1">Đang hoạt động</option>
													<option value="2" selected>Đang bảo trì</option>
												</c:when>
											</c:choose>
										</select>
									</div>
								</div>
								<div class="modal-footer">
									<a href="DichVuSV" class="btn btn-default">Hủy</a>
									<input type="submit" class="btn btn-success" value="Thay Đổi">
								</div>

							</form>
						</div>
					</div>
				</div>

			</div>


			<script src="js/manager.js" type="text/javascript"></script>
</body>
</html>