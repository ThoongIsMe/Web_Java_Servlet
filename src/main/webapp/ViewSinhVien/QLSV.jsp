
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

<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>

<body>


	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="leftNavQTSV.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Yêu cầu sinh viên</h1>

			<div class="container">
				<div class="row justify-content-center">
					<form class="form-inline my-2 my-lg-0 col-8"></form>


					<form class="input-group mb-3 col-4 mt-3" action="SearchYC">
						<!-- Use POST or GET method based on your requirements -->
						<input name="searchMaSV" class="form-control mr-sm-2"
							type="search" placeholder="Tìm kiếm sinh viên"
							aria-label="Search" />
						<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Tìm
							kiếm</button>
					</form>


				</div>
			</div>


			<!-- Data table -->
			<div class="container">
				<hr />
				<br />
				<table class="table table-bordered text-center">
					<thead class="table-primary ">
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
								<tr>
									<td><c:out value="${i.giayxacnhan.id}" /></td>
									<td><c:out value="${i.cacLoaiGiay.tenloaigiayString}" /></td>
									<td><c:out value="${i.giayxacnhan.maSV}" /></td>
									<td><fmt:parseDate var="localDate"
											value="${i.giayxacnhan.thoiGianXin}" pattern="yyyy-MM-dd" />
										<fmt:formatDate value="${localDate}" pattern="dd/MM/yyyy"
											var="formattedDate" /> <c:out value="${formattedDate}" /></td>
									<td><c:out value="${i.giayxacnhan.lyDo}" /></td>
									<td><c:out value="${i.giayxacnhan.SL}" /></td>

									<td><a href="Duyet?productId=${i.giayxacnhan.id}&maSV=${i.giayxacnhan.maSV}&tenGiay=${i.cacLoaiGiay.tenloaigiayString}">Duyệt</a>
										&nbsp;&nbsp;&nbsp;&nbsp; <a
										href="TuChoi?productId=${i.giayxacnhan.id}">Xóa</a></td>
								</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>


				<!-- Action buttons -->
				<div class="container">
					<div class="btnduyte text-right">
						<!-- Thêm class "text-right" để căn chỉnh sang phải -->
						<form action="DuyetAll" method="post">
							<button type="submit" class="btn btn-info">Duyệt tất cả</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="footer.jsp"></jsp:include>


</body>

</html>