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
                <form action="quanlysinhvien" method="post">
                <c:if test="${sinhvien != null}">
                    <input type="hidden" name="action" value="capnhatsv">
                </c:if>
                <c:if test="${sinhvien == null}">
                    <input type="hidden" name="action" value="themsv">
                </c:if>

                <caption>
                    <h2>
                        <c:if test="${sinhvien != null}">
                            Cập nhật thông tin ${sinhvien.tenSV}
                        </c:if>
                        <c:if test="${sinhvien == null}">
                            Thêm thông tin sinh viên
                        </c:if>
                    </h2>
                </caption>	

                <fieldset class="row g-3">
                	<div class="col-md-6">
					    <label>Mã sinh viên</label><input type="text" value="<c:out value='${sinhvien.maSV}${maSV}' />" class="form-control" name="masv" readonly>
					    <label>Họ tên</label> <input type="text" value="<c:out value='${sinhvien.tenSV}' />" class="form-control" name="hoten">
					    <label>Ngày sinh</label> <input type="date" value="<c:out value='${sinhvien.ngaySinh}' />" class="form-control" name="ngaysinh">
					    <label>Nơi sinh</label> <input type="text" value="<c:out value='${sinhvien.noiSinh}' />" class="form-control" name="noisinh">
					    <label>Giới tính</label> <input type="text" value="<c:out value='${sinhvien.gioiTinh}' />" class="form-control" name="gioitinh">
					    <label>Dân tộc</label> <input type="text" value="<c:out value='${sinhvien.danToc}' />" class="form-control" name="dantoc">
					    <label>CCCD</label> <input type="text" value="<c:out value='${sinhvien.cccd}' />" class="form-control" name="cccd">
					    <label>Số điện thoại</label> <input type="text" value="<c:out value='${sinhvien.sdt}' />" class="form-control" name="sdt">
					    <label>Email</label> <input type="email" value="<c:out value='${sinhvien.email}' />" class="form-control" name="email">                    
					</div>
					
					<div class="col-md-6">
					    <label>Địa chỉ</label> <input type="text" value="<c:out value='${sinhvien.diaChi}' />" class="form-control" name="diachi">
					    <label>Khoa</label> <input type="text" value="<c:out value='${sinhvien.khoa}' />" class="form-control" name="khoa">
					    <label>Niên khóa</label> <input type="text" value="<c:out value='${sinhvien.nienKhoa}' />" class="form-control" name="nienkhoa">
					    <label>Năm nhập học</label> <input type="number" value="<c:out value='${sinhvien.namNhapHoc}' />" class="form-control" name="namnhaphoc">
					    <label>Năm kết thúc</label> <input type="number" value="<c:out value='${sinhvien.namKetThuc}' />" class="form-control" name="namketthuc">
					    <label>Chương trình đào tạo</label> <input type="text" value="<c:out value='${sinhvien.ctDaoTao}' />" class="form-control" name="ctdaotao">
					    <label>Lớp</label> <input type="text" value="<c:out value='${sinhvien.lop}' />" class="form-control" name="lop">				    
						<label>Tình trạng</label> 
						<select class="form-control" name="tinhtrang">
						    <c:choose>
						        <c:when test="${sinhvien.tinhTrang eq 1}">
						            <option value="1" selected>Đang hoạt động</option>
						            <option value="0">Đã xóa</option>
						        </c:when>
						        <c:when test="${sinhvien.tinhTrang eq 0}">
						            <option value="1">Đang hoạt động</option>
						            <option value="0" selected>Đã xóa</option>
						        </c:when>
						        <c:otherwise>
						            <option value="1">Đang hoạt động</option>
						            <option value="0">Đã xóa</option>
						        </c:otherwise>
						    </c:choose>
						</select>
						<label>Ảnh thẻ</label> <input type="text" value="<c:out value='${sinhvien.anhThe}' />" class="form-control" name="anhthe">                               	
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