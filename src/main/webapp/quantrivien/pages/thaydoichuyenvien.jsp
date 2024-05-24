<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>QuanLySinhVien</title>
    <link rel="icon" type="image/x-icon" href="../resource/favicon.png" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />

    <style><%@include file="../styles.css"%></style>
<script charset="utf-8" type="text/javascript"><%@include file="../scripts.js" %></script>
  </head>
  <body>
    <jsp:include page="../components/header.jsp"></jsp:include>
    <br>
    <div class="container col-md-8">
        <div class="card">
            <div class="card-body">
                <form action="quanlychuyenvien" method="post">
                <c:if test="${chuyenvien != null}">
                    <input type="hidden" name="action" value="capnhatcv">
                </c:if>
                <c:if test="${chuyenvien == null}">
                    <input type="hidden" name="action" value="themcv">
                </c:if>

                <caption>
                    <h2>
                        <c:if test="${chuyenvien != null}">
                            Cập nhật thông tin ${chuyenvien.tenCV}
                        </c:if>
                        <c:if test="${chuyenvien == null}">
                            Thêm thông tin chuyên viên
                        </c:if>
                    </h2>
                </caption>	

                <fieldset class="row g-1">
                	<div class="col-md-12">
					    <label>Mã chuyên viên</label> <input type="text" value="<c:out value='${chuyenvien.maCV}${maCV}' />" class="form-control" name="macv" readonly>
					    <label>Họ tên</label> <input type="text" value="<c:out value='${chuyenvien.tenCV}' />" class="form-control" name="hoten">
					    <label>Email</label> <input type="email" value="<c:out value='${chuyenvien.email}' />" class="form-control" name="email"> 
					    <label>Số điện thoại</label> <input type="text" value="<c:out value='${chuyenvien.sdt}' />" class="form-control" name="sdt">
					    <label>Địa chỉ</label> <input type="text" value="<c:out value='${chuyenvien.diaChi}' />" class="form-control" name="diachi">  
					    <label>Ngày bắt đầu công tác</label> <input type="date" value="<c:out value='${chuyenvien.ngayCongTac}' />" class="form-control" name="ngaycongtac">				    
						<label>Tình trạng</label> 
						<select class="form-control" name="tinhtrang">
						    <c:choose>
						        <c:when test="${chuyenvien.tinhTrang eq 1}">
						            <option value="1" selected>Đang hoạt động</option>
						            <option value="0">Đã xóa</option>
						        </c:when>
						        <c:when test="${chuyenvien.tinhTrang eq 0}">
						            <option value="1">Đang hoạt động</option>
						            <option value="0" selected>Đã xóa</option>
						        </c:when>
						        <c:otherwise>
						            <option value="1">Đang hoạt động</option>
						            <option value="0">Đã xóa</option>
						        </c:otherwise>
						    </c:choose>
						</select> 
						<label>Quyền hạn</label> <!--<input type="text" value="<c:out value='${chuyenvien.quyenHan}' />" class="form-control" name="quyenhan"> -->    
						<select class="form-control" name="quyenhan">
						    <c:choose>
						        <c:when test="${chuyenvien.quyenHan eq 1}">
						            <option value="1" selected>Đầy đủ</option>
						            <option value="0">Hạn chế</option>
						        </c:when>
						        <c:when test="${chuyenvien.quyenHan eq 0}">
						            <option value="1">Đầy đủ</option>
						            <option value="0" selected>Hạn chế</option>
						        </c:when>
						        <c:otherwise>
						            <option value="1">Đầy đủ</option>
						            <option value="0">Hạn chế</option>
						        </c:otherwise>
						    </c:choose>
						</select>               
					</div>
                </fieldset>  
				<br>
				<div class="d-flex flex-row-reverse justify-content-around">
	                <button onclick="check()" type="submit" class="btn btn-info col-2">Lưu</button>				
				</div>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="../components/footer.jsp"></jsp:include>
    </body>
</html>