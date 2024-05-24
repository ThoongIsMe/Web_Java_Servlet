
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

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />




</head>


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
<script type="text/javascript">
    <%@include file="/js/noti.js" %>
</script>
    <style type="text/css">
        <%@include file="/css/noti.css" %>
        
    </style>


<body>


	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="leftNavQTSV.jsp"></jsp:include>
		<div class="notifications"></div>
		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Yêu cầu sinh viên</h1>

			<div class="notifications toasts"></div>

			<div class="notifications  toasts1"></div>

			<div class="container">
				<div class="row justify-content-center">
					<form class="form-inline my-2 my-lg-0 col-8"></form>


					<form class="input-group mb-3 col-4 mt-3" action="SearchYC"
						method="post">
						<!-- Use POST or GET method based on your requirements -->
						<input name="searchMaSV" class="form-control mr-sm-2"
							type="search" placeholder="Tìm kiếm sinh viên"
							aria-label="Search" />
						<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Tìm
							kiếm</button>
					</form>


				</div>
			</div>
			<button id="success"></button>
			<button id="error"></button>
			<!-- Data table -->
			<div class="container">
				<hr />
				<br />
				<div class="table-container">
					<table class="table table-bordered text-center">
						<thead class="table-primary fixed-header ">
							<tr>
								<th>ID</th>
								<th>Tiêu đề</th>
								<th>Người gửi</th>
								<th>Thời gian</th>
								<th>Lý do</th>
								<th>Số lượng</th>
								<th>Thực hiện</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="i" items="${listYCXN}">
								<c:if test="${i.giayxacnhan.trangThai == 1}">
									<tr onclick="setGiayId('${i.giayxacnhan.id}')">
										<td><c:out value="${i.giayxacnhan.id}" /></td>
										<td><c:out value="${i.cacLoaiGiay.tenloaigiayString}" /></td>
										<td><c:out value="${i.giayxacnhan.maSV}" /></td>
										<td><fmt:parseDate var="localDate"
												value="${i.giayxacnhan.thoiGianXin}" pattern="yyyy-MM-dd" />
											<fmt:formatDate value="${localDate}" pattern="dd/MM/yyyy"
												var="formattedDate" /> <c:out value="${formattedDate}" /></td>
										<td><c:out value="${i.giayxacnhan.lyDo}" /></td>
										<td><c:out value="${i.giayxacnhan.SL}" /></td>

										<td><a class="edit-link" id="successLink"
											href="Duyet?productId=${i.giayxacnhan.id}&maSV=${i.giayxacnhan.maSV}&tenGiay=${i.cacLoaiGiay.tenloaigiayString}"
											onclick="simulateButtonClick('success')">Duyệt</a>
											&nbsp;&nbsp;&nbsp;&nbsp; <a class="delete-link"
											data-toggle="modal" data-target="#XacNhan" id="errorLink"
											href="">Xóa</a></td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>
					</table>
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
									<label>BẠN CÓ MUỐN XÓA YÊU CẦU NÀY KHÔNG?</label>
								</div>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default"
									data-dismiss="modal" value="Hủy"> <a href="#"
									onclick="simulateButtonClick('error')" id="confirmDelete"
									class="btn btn-success" style="background-color: #AD481B;"><b>XÓA</b></a>
							</div>
						</div>
					</div>
				</div>





				<div class="modal fade" id="DuyetTatCa" style="margin-top: 100px;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Cảnh Báo</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">

								<div class="form-group">
									<label>BẠN CÓ MUỐN DUYỆT TẤT CẢ YÊU CẦU NÀY KHÔNG?</label>
								</div>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default"
									data-dismiss="modal" onclick="simulateButtonClick('error')" value="Hủy"> <a
									onclick="simulateButtonClick('success')" href="DuyetAll"
									class="btn btn-success" style="background-color: #AD481B;"><b>CÓ</b></a>
							</div>
						</div>
					</div>
				</div>

				<input type="hidden" id="idGiayInput" />
				<!-- Action buttons -->
				<div class="container">
					<div class="btnduyte text-right">
						<!-- Thêm class "text-right" để căn chỉnh sang phải -->

						<button id="getValuesButton" data-toggle="modal"
							data-target="#DuyetTatCa" type="submit" class="btn btn-info mt-4">Duyệt
							tất cả</button>

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

		<script>
			function setGiayId(idGiay) {
				document.getElementById('idGiayInput').value = idGiay;

				document.getElementById('confirmDelete').href = "TuChoi?productId="
						+ idGiay;
			}
		</script>


	</main>

	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>