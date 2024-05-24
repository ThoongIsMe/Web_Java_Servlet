<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #0187c6">
			<div class="d-flex" style="margin-left: 25%;">
				<div class="flex-shrink-0">
					<img class="navbar-brand" width="80px" src="https://seeklogo.com/images/H/hcmute-logo-7553D4CCA1-seeklogo.com.png"
						alt="logo_spkt">
				</div>
				<h3 class="flex-grow-1 ms-3 d-flex align-items-center">Trường
					đại học Sư Phạm Kỹ Thuật TP. Hồ Chí Minh</h3>
			</div>
		</nav>
		<nav
			class="navbar navbar-expand-lg navbar-light bg-light container-fluid d-flex flex-row justify-content-end">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="TrangchuSV">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link" href="HuongDanSV">Hướng
							dẫn</a></li>
				</ul>
			</div>

			<c:if test="${not empty UserSV.hoteString}">
				<div class="badge text-wrap mr-3" id="name-label">${UserSV.hoteString}</div>
			</c:if>


			<a href="<%=request.getContextPath()%>/logout"><button class="btn btn-primary" type="button">Đăng
					xuất</button></a>

		</nav>
	</header>
</body>
</html>