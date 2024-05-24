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
			<h1 class=" text-center">Quản lý dịch vụ</h1>

			<!-- Data table -->
			<div class="container">
				<hr />
				<br />
				<div class="tbl-header">
					<table class="table table-bordered" style="width: 100%;">
						<thead class="table-primary">
							<tr>
								<th style="width: 20%;">Mã loại</th>
								<th style="width: 30%;">Tên dịch vụ</th>
								<th style="width: 30%;">Trạng thái</th>
									<th>Hành động</th>
							</tr>
						</thead>
					</table>
				</div>
				<div style="width: 100%; overflow: auto; max-height: 350px;">
					<table class="table table-bordered" style="width: 100%;">
						<tbody>
							<c:forEach var="dv" items="${listDichVu}">
								<tr>
									<td style="width: 20%;"><c:out value="${dv.maDV}" /></td>
									<td style="width: 30%;"><c:out value="${dv.tenDV}" /></td>
									<td style="width: 30%;"><c:out
											value="${dv.tinhTrangString}" /></td>
									<td>
					                    <form action="quanlydichvu" method="POST" >
						            		<input type="hidden" name="maDV" value='${dv.maDV}'>							
						                    <button class="btn btn-outline-info my-2 my-sm-0 p-6" type="submit" name="action" value="capnhatthongtindichvu">Cập nhật</button>
						                    &nbsp;&nbsp;&nbsp;&nbsp; 
						                    <button onclick="check()" class="btn btn-outline-info my-2 my-sm-0 p-6" type="submit" name="action" value="xoathongtindichvu">Xóa</button>
										</form>	
					                </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br /> <br />
				<!-- Action buttons -->
				<div class="container"  style="width:350px;"> 
					<div class="row justify-content-center">
						<button type="button" class="col mx-4 btn btn-info"><a class="link-button" href="<%=request.getContextPath()%>/quanlydichvu?action=themthongtindichvu">Thêm
						dịch vụ</a></button>
					</div>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
