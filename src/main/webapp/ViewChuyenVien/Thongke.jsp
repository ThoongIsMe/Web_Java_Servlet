<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Statistical Query Form</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">


<!-- Custom CSS for Animation -->

<style>
body {
	padding-top: 56px;
}

.Soluong {
	text-align: center;
	margin: 50px;
	border: none;
	font-size: 36px;
}

.sl-bg {
	background: #EEEEEE;
	border-radius: 10%;
}

#downloadPdfButton {
	float: right;
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
			<h1 class=" text-center">Thống kê yêu cầu</h1>

			<div class="container mt-5">
				<div class="row justify-content-center">
					<form class="form-inline my-2 my-lg-0 col-8"></form>


					<div class="container">
						<div class="row mt-3">
							<div class="col-3 d-flex align-items-center">
								<div class="overlap sl-bg">
									<div class="text-wrapper ml-2">Tổng yêu cầu</div>
									<div>
										<button name="buttonName" value="1" class="Soluong"
											onclick="submitForm(this)">${count}</button>
									</div>
								</div>
							</div>
							<div class="col-3 d-flex align-items-center">
								<div class="overlap sl-bg">
									<div class="text-wrapper ml-2">Đã duyệt</div>
									<div>
										<button name="buttonName" value="2" class="Soluong"
											onclick="submitForm(this)">${count1}</button>
									</div>
								</div>
							</div>
							<div class="col-3 d-flex align-items-center">
								<div class="overlap sl-bg">
									<div class="text-wrapper ml-2">Chưa duyệt</div>
									<div>
										<button name="buttonName" value="3" class="Soluong"
											onclick="submitForm(this)">${count2}</button>
									</div>
								</div>
							</div>
							<div class="col-3 d-flex align-items-center">
								<div class="overlap sl-bg">
									<div class="text-wrapper ml-2">Đã Nhận</div>
									<div>
										<button name="buttonName" value="4" class="Soluong"
											onclick="submitForm(this)">${count3}</button>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
			<div class=" dataContainer container mt-5">
				<form action="Thongke" method="post">

					<div class="row mt-4">
						<div class="col-3 d-flex align-items-center">
							<label for="department" class="mr-2">Khoa:</label> <select
								class="form-control khoaHocSelect" id="semester"
								name="department" required>
								<c:forEach var="khoaHoc" items="${khoahoclist}">

									<c:choose>
										<c:when test="${khoaHoc eq selectedDepartment}">
											<option value="${khoaHoc}" selected>${khoaHoc}</option>
										</c:when>
										<c:otherwise>
											<option value="${khoaHoc}">${khoaHoc}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
								<!--
								 Add more options as needed -->
							</select>
						</div>

						<div class="col-3 d-flex align-items-center">
							<label for="academicYear" class="mr-2"> Khoá:</label> <select
								class="form-control khoaHocSelect" id="semester"
								name="academicYear" required>
								<c:forEach var="namNhapHoc" items="${distinctNamNhapHocList}">

									<c:choose>
										<c:when test="${namNhapHoc eq selectedAcademicYear}">
											<option value="${namNhapHoc}" selected>${namNhapHoc}</option>
										</c:when>
										<c:otherwise>
											<option value="${namNhapHoc}">${namNhapHoc}</option>
										</c:otherwise>
									</c:choose>


								</c:forEach>
							</select>
						</div>

						<div class="col-3 d-flex align-items-center">
							<label for="schoolYear" class="mr-2">Năm Học:</label> <select
								class="form-control khoaHocSelect" id="semester"
								name="schoolYear" required>
								<c:forEach var="schoolYear" items="${NamHoclist}">

									<c:choose>
										<c:when test="${schoolYear eq selectedSchoolYear}">
											<option value="${schoolYear}" selected>${schoolYear}</option>
										</c:when>
										<c:otherwise>
											<option value="${schoolYear}"
												${schoolYear eq selectedSchoolYear ? 'selected' : ''}>${schoolYear}</option>

										</c:otherwise>
									</c:choose>





								</c:forEach>
							</select>
						</div>

						<div class="col-3">

							<button name="buttonName" value="5"
								class="btn btn-lg btn-primary text-uppercase"
								onclick="submitForm(this)">Thống Kê</button>
						</div>
					</div>
				</form>
			</div>

			<!-- Data table -->
			<div class="container mt-5">
				<hr />
				<br />
				<table class="table table-bordered text-center" id="tb-thongke">
					<thead class="table-primary ">
						<tr>
							<th>MSSV</th>
							<th>Họ Tên</th>
							<th>Thời Gian Xin</th>
							<th>Lý do</th>
							<th>Tên Loại Giấy</th>
							<th>Số lượng</th>
							<th>Khoa</th>
							<th>Khóa</th>
							<th>Năm Học</th>
						</tr>
					</thead>


					<tbody id="dataTableBody">

						<c:forEach var="i" items="${listtrangthai}">
							<tr>
								<td><c:out value="${i.giayxacnhan.maSV}" /></td>
								<td><c:out value="${i.thongTinSV.hoteString}" /></td>
								<td><fmt:parseDate var="localDate"
										value="${i.giayxacnhan.thoiGianXin}" pattern="yyyy-MM-dd" />
									<fmt:formatDate value="${localDate}" pattern="dd/MM/yyyy"
										var="formattedDate" /> <c:out value="${formattedDate}" /></td>
								<td><c:out value="${i.giayxacnhan.lyDo}" /></td>
								<td><c:out value="${i.cacLoaiGiay.tenloaigiayString}" /></td>
								<td><c:out value="${i.giayxacnhan.SL}" /></td>
								<td><c:out value="${i.thongTinSV.khoaHoc}" /></td>
								<td><c:out value="${i.thongTinSV.namNhapHoc}" /></td>
								<td><c:out value="${i.giayxacnhan.thoiGianXin.year}" /></td>

							</tr>
						</c:forEach>



					</tbody>



				</table>

				<input class="btn btn-primary" type="button" value="PRINT"
					id="downloadPdfButton" />
			</div>
		</div>
	</main>

	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		function submitForm(button) {
			// Get the value of the clicked button
			var buttonValue = button.value;

			// Create a hidden form element
			var form = document.createElement("form");
			form.method = "GET";
			form.action = "Thongke";

			// Create an input element to hold the button value
			var input = document.createElement("input");
			input.type = "hidden";
			input.name = "buttonName";
			input.value = buttonValue;

			// Append the input element to the form
			form.appendChild(input);

			// Append the form to the document and submit it
			document.body.appendChild(form);
			form.submit();
		}
	</script>

	<!-- Include the jsPDF library -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/jspdf-autotable@3.5.9/dist/jspdf.plugin.autotable.min.js"></script>

	<script>
		function printPage() {
			window.print();
		}
		document.getElementById('downloadPdfButton').addEventListener('click',
				function() {
					printPage();
				});
	</script>


</body>

</html>