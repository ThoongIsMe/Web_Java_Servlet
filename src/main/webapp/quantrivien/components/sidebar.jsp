<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="col-md-2 bg-light sidebar" id="side-bar">
	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>/thongbao">Trang chủ</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>/quanlysinhvien">Quản lý sinh
				viên</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>/quanlychuyenvien">Quản lý
				chuyên viên</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>/quanlydichvu">Quản lý dịch vụ</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>/quanlyyeucau">Quản lý yêu cầu</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>/quantrivien">Tình trạng web</a></li>
	</ul>
</nav>