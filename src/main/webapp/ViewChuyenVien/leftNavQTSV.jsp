<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="col-md-2 bg-light sidebar" id="side-bar">
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link active"
				href="TrangchuCTSV">Trang chủ</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="ThongTinCTV">Thông Tin </a></li>
			<li class="nav-item"><a class="nav-link" href="DuyetYeuCauSV">Yêu
					cầu dịch vụ</a></li>
			<li class="nav-item"><a class="nav-link" href="XacNhanLayGiay">Xác
					nhận</a></li>
			<li class="nav-item"><c:choose>
					<c:when test="${UserCTSV.quyenhan == 1}">
						<a class="nav-link" href="DichVuSV">Dịch vụ sinh viên</a>
					</c:when>
					<c:otherwise>
						
						<a class="nav-link disabled" href="#">Dịch vụ sinh viên</a>
					</c:otherwise>
				</c:choose></li>
			<li class="nav-item"><a class="nav-link" href="Thongke">Thống
					kê</a></li>

		</ul>
	</nav>
</body>
</html>