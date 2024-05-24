
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>QuanLySinhVien</title>


    <style type="text/css">
        <%@include file="/css/noti.css" %>
        
    </style>
   
		<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    
 
</head>
 <script type="text/javascript">
    <%@include file="/js/noti.js" %>
</script>


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
			
			 <div class="notifications toasts">

    </div>
    
     <div class="notifications  toasts1">

    </div>

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

										<td><a id="success"
											href="Duyet?productId=${i.giayxacnhan.id}&maSV=${i.giayxacnhan.maSV}&tenGiay=${i.cacLoaiGiay.tenloaigiayString}">Duyệt</a>
											&nbsp;&nbsp;&nbsp;&nbsp; <a id="error"
											href="TuChoi?productId=${i.giayxacnhan.id}">Xóa</a></td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>
					</table>
				</div>


				<!-- Action buttons -->
				<div class="container">
					<div class="btnduyte text-right">
						<!-- Thêm class "text-right" để căn chỉnh sang phải -->
						<form action="DuyetAll" method="post">
							<button type="submit" class="btn btn-info mt-4">Duyệt
								tất cả</button>
						</form>
					</div>
				</div>
			</div>

		</div>
		
		


	</main>
	<button id="success">scccc</button>
	<button id="error">Adđ</button>
	<jsp:include page="footer.jsp"></jsp:include>


</body>

</html>