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
</head>
<body>
	<jsp:include page="../components/header.jsp"></jsp:include>

	<!-- Sidebar -->
	<main class="container-fluid d-flex flex-row" id="main-form">
		<jsp:include page="../components/sidebar.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Quản lý sinh viên</h1>

			<div class="container">
				<div class="row justify-content-center">
					<!-- Search bar -->
					<form class="form-inline my-2 my-lg-0 col-5" action="quanlysinhvien" method="POST">
						<input name="svsearch" class="form-control mr-sm-2" type="search" placeholder="Tìm kiếm sinh viên" aria-label="Search" name="svsearch"/>
						<button name="action" value="timsv" class="btn btn-outline-info my-2 my-sm-0 p-6" type="submit" >Tìm kiếm</button>
						<button name="action" value="" class="btn btn-outline-info my-2 my-sm-0 p-6" type="submit" >Reload</button>
					</form>
					<!-- Upload file -->
					<form action="quantrivien" method="POST" enctype="multipart/form-data" class="row"> 
						<input class="form-control col-9" name="file" type="file" accept=".xls, .xlsx" required> 
						<input name="action" value="uploadfile" type="hidden"> 
						<br>
						<input type="submit" value="Import" class="col-3 btn btn-info">
					</form>
				</div>
			</div>


			<!-- Data table -->
			<div class="container">
				<hr />
				<br />
				<div class="tbl-header">
					<table class="table table-bordered" style="width: 100%;">
						<thead class="table-primary">
							<tr>
								<th style="width: 20%;">Tên sinh viên</th>
								<th style="width: 20%;">Ngày sinh</th>
								<th style="width: 20%;">Số CCCD</th>
								<th style="width: 20%;">Niên khóa</th>
								<th style="width: 20%;">Lớp</th>
							</tr>
						</thead>
					</table>
				</div>
				<div style="width: 100%; overflow: auto; max-height: 350px;">
					<table class="table table-bordered" style="width: 100%;">
						<tbody>
							<c:forEach var="sv" items="${listSinhVienImport}">
								<tr>
									<td style="width: 20%;"><c:out value="${sv.tenSV}" /></td>
									<td style="width: 20%;"><c:out value="${sv.ngaySinhString}" /></td>																
									<td style="width: 20%;"><c:out value="${sv.cccd}" /></td>								
									<td style="width: 20%;"><c:out value="${sv.nienKhoa}" /></td>
									<td style="width: 20%;"><c:out value="${sv.lop}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br /> <label>Tổng số sinh viên trong danh sách ${tongsosinhvienimport}</label> <br /> <br />

				<!-- Action buttons -->
				<div class="container">
					<div class="row justify-content-center">
						<button type="button" class="col mx-4 btn btn-info"><a class="link-button" href="<%=request.getContextPath()%>/quanlysinhvien">Hủy</a></button>
						<button type="button" class="col mx-4 btn btn-info"><a class="link-button" href="<%=request.getContextPath()%>/quantrivien?action=importsvfromfile">Nhập danh sách</a></button>
					</div>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
