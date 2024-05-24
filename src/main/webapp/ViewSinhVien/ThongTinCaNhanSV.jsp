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
<script src="https://unpkg.com/ionicons@latest/dist/ionicons.js"></script>


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
			<!-- Data table -->
			<div class ="container">
				<div class="container row">
						<div class="col-md-6"> 
							<hr />
							<br />
							<table class="table table-bordered">
									<thead class="table-primary  text-center ">
										<tr>
											<th colspan="2">Thông tin sinh viên</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><label>Mã số sinh viên: </label></td>
									        <td>${UserSV.maSV.toString()}</td>
										</tr>
										<tr>
											<td><label>Họ và tên: </label></td>
									        <td>${UserSV.hoteString}</td>
										</tr>
										<tr>
											<td><label>Ngày sinh: </label></td>
									        <td><fmt:parseDate var="localDate"
														value="${UserSV.ngaySinh}" pattern="yyyy-MM-dd" /> <fmt:formatDate
														value="${localDate}" pattern="dd/MM/yyyy" var="formattedDate" />
													${formattedDate}
										</tr>
										<tr>
											<td><label>Nơi sinh: </label></td>
									        <td>${UserSV.noiSinh}</td>
										</tr>
										<tr>
											<td><label>Giới tính: </label></td>
									        <td>${UserSV.gioiTinh}</td>
										</tr>
										<tr>
											<td><label>Dân tộc: </label></td>
									        <td>${UserSV.danToc}</td>
										</tr>
										<tr>
											<td><label>Số CCCD: </label></td>
									        <td>${UserSV.soCCCD}</td>
										</tr>
										
									</tbody>
			
							</table>
						</div>
						<div class="col-md-6"> 
							<hr />
							<br />
							<div class="d-flex justify-content-center align-items-center">
							<img  src="${UserSV.anhTheLink}"  style="border-color:Navy;border-width:3px;border-style:Dashed;height:256px;width:186px;"
		                    alt="...">
		                    </div>
		                    <br />
		                    
	                    </div>
					
					</div>
					
				<div class="container row">
						<div class="col-md-6"> 
							<table class="table table-bordered">
									<thead class="table-primary  text-center ">
										<tr>
											<th colspan="2">Thông tin khóa học</th>
										</tr>
									</thead>
									<tbody>
										
										<tr>
											<td><label>Khoa học: </label></td>
									        <td>${UserSV.khoaHoc}</td>
										</tr>
										<tr>
											<td><label>Niên khóa: </label></td>
									        <td>${UserSV.nienKhoa}</td>
										</tr>
										<tr>
											<td><label>Chương trình đào tạo: </label></td>
									        <td>${UserSV.CTDaoTao}</td>
										</tr>
										<tr>
											<td><label>Lớp sinh viên: </label></td>
									        <td>${UserSV.lopSV}</td>
										</tr>
									</tbody>
							</table>
						</div>
						<div class="col-md-6"> 
							<table class="table table-bordered">
									<thead class="table-primary  text-center ">
										<tr>
											<th colspan="2">Thông tin liên lạc</th>
										</tr>
									</thead>
									<tbody>
										
										<tr>
											<td><label>Số điện thoại: </label></td>
									        <td>${UserSV.SDT}</td>
										</tr>
										<tr>
											<td><label>Email: </label></td>
									        <td>${UserSV.email}</td>
										</tr>
										<tr>
											<td><label>Địa chỉ: </label></td>
									        <td>${UserSV.diaChiSV}</td>
										</tr>
									</tbody>
							</table>
							<table class="table">
							<tbody>
										
										<tr>
											<td><a href=DoiMatKhauSV.jsp>Đổi mật khẩu</a></td>
											<td><a href=ViewSinhVien/CapNhatThongTinSV.jsp>Cập nhât</a></td>
										</tr>
									</tbody>
							</table>
						</div>
					
					</div>
			</div>
		</div>

		
	</main>

	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		function showContent(id, content) {
			document.getElementById("content").innerHTML = "<p><b>" + content
					+ "</b></p>";
		}
	</script>

</body>

</html>