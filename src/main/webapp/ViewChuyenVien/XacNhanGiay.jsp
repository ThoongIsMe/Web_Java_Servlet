
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


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />



<script type="text/javascript">
    <%@include file="/js/noti.js" %>
</script>
 <style type="text/css">
      <%@include file="/css/noti.css" %>
        
</style>

</head>

<style>
.table-container {
	max-height: 39 0px;
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
</style>

<body>


	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="leftNavQTSV.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Xác Nhận yêu cầu duyệt</h1>
			<div class="notifications toasts"></div>

			<div class="notifications  toasts1"></div>

			<div class="container">
				<div class="row justify-content-center">
					<!-- Search bar -->
					<form class="form-inline my-2 my-lg-0 col-8"></form>

					<form class="input-group mb-3 col-4 mt-3" action="searchNhanGiay">
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
						<thead class="table-primary fixed-header">
							<tr>
								<th>ID</th>
								<th>Tiêu đề</th>
								<th>Người gửi</th>
								<th>Thời gian</th>
								<th>Thực hiện</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${listNXG}">
								<c:if test="${i.giayxacnhan.trangThai == 2}">
									<tr>
										<td><c:out value="${i.giayxacnhan.id}" /></td>
										<td><c:out value="${i.cacLoaiGiay.tenloaigiayString}" /></td>
										<td><c:out value="${i.giayxacnhan.maSV}" /></td>
										<td><fmt:parseDate var="localDate"
												value="${i.giayxacnhan.thoiGianXin}" pattern="yyyy-MM-dd" />
											<fmt:formatDate value="${localDate}" pattern="dd/MM/yyyy"
												var="formattedDate" /> <c:out value="${formattedDate}" /></td>

										<td><a
											href="XacNhanNhanGiay?productId=${i.giayxacnhan.id}" onclick="simulateButtonClick('success')">Xác
												Nhận</a> &nbsp;&nbsp;&nbsp;&nbsp;
									</tr>
								</c:if>
							</c:forEach>

						</tbody>
					</table>
				</div>




			</div>
		</div>
	</main>

	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		function simulateButtonClick(buttonId) {
			// Simulate button click based on the provided buttonId
			document.getElementById(buttonId).click();
		}
	</script>

</body>

</html>