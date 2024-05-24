<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>QuanLySinhVien</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<script src="https://unpkg.com/ionicons@latest/dist/ionicons.js"></script>
<style>
<%@
include file="../styles.css"%>
</style>

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

.table-container {
	max-height: 400px;
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


</head>

<body>

	<jsp:include page="../components/header.jsp"></jsp:include>
	<!-- Sidebar -->

	<main class="container-fluid d-flex flex-row" id="main-form">

		<jsp:include page="../components/sidebar.jsp"></jsp:include>

		<!-- Main content of web -->
		<div class="col-md-10" id="main-content">
			<h1 class=" text-center">Thông báo đã gửi</h1>
			<div class="container d-flex flex-row justify-content-end">
				<div style="width: 250px !important;">
					<button type="button" class="col mx-4 btn btn-info">
						<a class="link-button" style="padding:4% 19%;"
							href="<%=request.getContextPath()%>/thongbao?action=taothongbao">Tạo
							thông báo mới</a>
					</button>
				</div>
			</div>
			<!-- Data table -->
			<div class="container ">
				<hr />
				<br />
				<div class="table-container">
					<table class="table table-bordered text-center">
						<thead class="table-primary fixed-header">
							<tr>
								<th></th>
								<th>Tiêu đề</th>
								<th>Thời gian</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${thongbaoCTSV}">
								<tr data-content="${i.noidungString}"
									onclick="showContent(this)">
									<td><ion-icon name="mail-outline"></ion-icon></td>
									<td class="title-cell"><c:out value="${i.tieudeString}" />
									</td>
									<td><fmt:parseDate var="localDate"
											value="${i.thoigianDate}" pattern="yyyy-MM-dd" /> <fmt:formatDate
											value="${localDate}" pattern="dd/MM/yyyy" var="formattedDate" />
										<c:out value="${formattedDate}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div id="content">
					<p>
						<b></b>
					</p>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="../components/footer.jsp"></jsp:include>
	<script>
		function showContent(element) {
			var id = element.getAttribute('data-id');
			var content = element.getAttribute('data-content');
			document.getElementById("content").innerHTML = " <h6><strong>NỘI DUNG</strong></h6>  <br>  <p>"
					+ content + "</p>";
		}
	</script>
</body>

</html>