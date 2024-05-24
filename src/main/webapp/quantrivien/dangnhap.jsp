<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>QuanLySinhVien</title>
    <link rel="icon" type="image/x-icon" href="../resources/favicon.png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous"/>
    <style><%@include file="styles.css"%></style>
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #0187c6">
        <div class="d-flex" style="margin-left: 25%;">
            <div class="flex-shrink-0">
                <img class="navbar-brand" width="80px"
                     src="https://seeklogo.com/images/H/hcmute-logo-7553D4CCA1-seeklogo.com.png" alt="logo_spkt">
            </div>
            <h3 class="flex-grow-1 ms-3 d-flex align-items-center">Trường đại học Sư Phạm Kỹ Thuật TP. Hồ Chí Minh
            </h3>
        </div>
    </nav>
</header>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Dịch vụ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Hướng dẫn</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container col-md-5 col-md-offset-3" style="overflow: auto">
    <form action="quanlysinhvien" method="POST">
        <div class="form-group">
            <label for="uname">Tên đăng nhập:</label>
            <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required/>
        </div>

        <div class="form-group">
            <label for="uname">Mật khẩu:</label>
            <input type="password" class="form-control" id="password" placeholder="Password" name="password" required/>
        </div>
        <div class="container" style="padding: 0;">
            <div class="row">
                <div class="col">
                    <input type="radio" name="role" value="sinhvien" id="sinhvien"/>
                    <label for="sinhvien">Sinh viên</label>
                </div>
                <div class="col">
                    <input type="radio" name="role" value="chuyenvien" id="chuyenvien"/>
                    <label for="chuyenvien">Chuyên viên</label>
                </div>
                <div class="col">
                    <input type="radio" name="role" value="quantrivien" id="quantrivien"/>
                    <label for="quantrivien">Quản trị viên</label>
                </div>
            </div>
        </div>
        <p class="alert alert-danger center" role="alert" style="margin-top: 15px"
           ${empty NOTIFICATION_LOGIN ? 'hidden' : ''}>
            ${NOTIFICATION_LOGIN}
        </p>
        <div class="d-flex flex-row-reverse">
            <input type="hidden" name="action" value="login">
            <button type="submit" class="btn btn-primary">Đăng nhập</button>
        </div>
    </form>
</div>
<jsp:include page="components/footer.jsp"></jsp:include>
<style>
    .footer {
        position: fixed;
    }
</style>

</body>
</html>
