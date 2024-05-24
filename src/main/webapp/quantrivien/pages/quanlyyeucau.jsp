<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<style><%@include file="../styles.css"%></style>
<script charset="utf-8" type="text/javascript"><%@include file="../scripts.js" %></script>
</head>
<body>
	<jsp:include page="../components/header.jsp"></jsp:include>

	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">
		<jsp:include page="../components/sidebar.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Quản lý yêu cầu</h1>

			<!-- Data table -->
			<div class="container">
				<hr />
				<br />
				<div class="tbl-header">
					<table class="table table-bordered" style="width: 100%;">
						<thead class="table-primary">
							<tr>
								<th style="width: 15%;">Tiêu đề</th>
								<th style="width: 10%;">Người gửi</th>
								<th style="width: 15%;">Thời gian gửi</th>
								<th style="width: 20%;">Loại dịch vụ</th>
								<th style="width: 9%;">Số lượng</th>
								<th style="width: 11%;">Trạng thái</th>
								<th>Hành động</th>
							</tr>
						</thead>
					</table>
				</div>
				<div style="width: 100%; overflow: auto; max-height: 350px;">
					<table class="table table-bordered" style="width: 100%;">
						<tbody>
							<c:forEach var="yc" items="${listYeuCau}">
								<tr>
									<td style="width: 15%;"><c:out value="${yc.lyDo}" /></td>
									<td style="width: 10%;"><c:out value="${yc.maSV}" /></td>
									<td style="width: 15%;"><c:out value="${yc.thoiGianXinString}" /></td>
									<td style="width: 20%;"><c:out value="${yc.tenDV}" /></td>
									<td style="width: 9%;"><c:out value="${yc.sl}" /></td>
									<td style="width: 11%;"><c:out value="${yc.trangThai}" /></td>
									<td>
					                    <form action="quanlyyeucau" method="POST" >
						            		<input type="hidden" name="id" value='${yc.ID}'>							
						                    <button onclick="check()" class="btn btn-outline-info my-2 my-sm-0 p-6" type="submit" name="action" value="duyetyc">Duyệt</button>
						                    &nbsp;&nbsp;&nbsp;&nbsp; 
						                    <button onclick="check()" class="btn btn-outline-info my-2 my-sm-0 p-6" type="submit" name="action" value="tuchoiyc">Từ chối</button>
										</form>	
					                </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br /> <br />
				<!-- Action buttons -->
				<div class="d-flex flex-row" style="width:300px;">
					<button onclick="check()" type="button" class="col mx-4 btn btn-info"><a class="link-button" href="<%=request.getContextPath()%>/quanlyyeucau?action=duyetallyc">Duyệt tất cả</a></button>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
