<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: #0187c6">
		<div class="d-flex" style="margin-left: 25%;">
			<div class="flex-shrink-0">
				<img class="navbar-brand" width="80px"
					src="https://seeklogo.com/images/H/hcmute-logo-7553D4CCA1-seeklogo.com.png"
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
					href="/qlsv/thongbao">Trang chủ</a></li>
				<li class="nav-item"><a class="nav-link" href="quantrivien/pages/huongdan.jsp">Hướng dẫn</a></li>
			</ul>
		</div>

		<div class="badge text-wrap" id="name-label">${name_label}</div>

		<form action="<%=request.getContextPath()%>/logout" method="post">
			<button class="btn btn-primary" type="submit">Đăng xuất</button>
		</form>
	</nav>
</header>