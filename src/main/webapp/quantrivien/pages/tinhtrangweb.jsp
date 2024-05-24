<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>QuanLySinhVien</title>
<link rel="icon" type="image/x-icon" href="../resource/favicon.png" />
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
			<h1 class="text-center">Tình trạng web</h1>

			<!-- Data table -->
			<div class="container">
				<hr />
				<br />
				<div class="row justify-content-center">
					<table class="table table-bordered" style="width: 70%">
						<tbody class="table-primary">
							<tr>
								<td>Tổng số tài khoản hiện hành</td>
							</tr>
							<tr>
								<td>Tổng số sinh viên</td>
							</tr>
							<tr>
								<td>Tổng số chuyên viên</td>
							</tr>
							<tr>
								<td>Lượt sử dụng dịch vụ tháng này</td>
							</tr>
							<tr>
								<td>Lượt truy cập theo tháng</td>
							</tr>
						</tbody>
					</table>
					<table class="table table-bordered" style="width: 30%">
						<tbody><tr>
								<td>${tong_sotk}</td>
							</tr>
							<tr>
								<td>${tong_sosv}</td>
							</tr>
							<tr>
								<td>${tong_socv}</td>
							</tr>
							<tr>
								<td>${luot_dung_dv}</td>
							</tr>
							<tr>
								<td>${luot_truy_cap}</td>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
