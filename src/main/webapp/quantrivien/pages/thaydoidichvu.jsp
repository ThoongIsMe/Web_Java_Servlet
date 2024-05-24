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
                <form action="quanlydichvu" method="post">
                <c:if test="${dichvu != null}">
                    <input type="hidden" name="action" value="capnhatdv">
                </c:if>
                <c:if test="${dichvu == null}">
                    <input type="hidden" name="action" value="themdv">
                </c:if>

                <caption>
                    <h2>
                        <c:if test="${dichvu != null}">
                            Cập nhật thông tin ${dichvu.tenDV}
                        </c:if>
                        <c:if test="${dichvu == null}">
                            Thêm thông tin dịch vụ
                        </c:if>
                    </h2>
                </caption>	

                <fieldset class="row g-3">
                	<div class="col-md-12">
					    <label>Mã loại dịch vụ</label> <input type="text" value="<c:out value='${dichvu.maDV}${maDV}' />" class="form-control" name="madv" readonly>
					    <label>Tên dịch vụ</label> <input type="text" value="<c:out value='${dichvu.tenDV}' />" class="form-control" name="tendv">				    
						<label>Tình trạng</label> 
						<select class="form-control" name="tinhtrang">
						    <c:choose>
						        <c:when test="${dichvu.tinhTrang eq 0}">
						            <option value="0" selected>Đã xóa</option>
						            <option value="1">Đang hoạt động</option>
						            <option value="2">Đang bảo trì</option>
						        </c:when>
						        <c:when test="${dichvu.tinhTrang eq 1}">
						            <option value="0">Đã xóa</option>
						            <option value="1" selected>Đang hoạt động</option>
						            <option value="2">Đang bảo trì</option>
						        </c:when>
						        <c:when test="${dichvu.tinhTrang eq 2}">
						            <option value="0">Đã xóa</option>
						            <option value="1">Đang hoạt động</option>
						            <option value="2" selected>Đang bảo trì</option>
						        </c:when>
						        <c:otherwise>
						            <option value="0">Đã xóa</option>
						            <option value="1">Đang hoạt động</option>
						            <option value="2">Đang bảo trì</option>
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
	<style>
		.footer {
			position: fixed;
		}
	</style>
    <jsp:include page="../components/footer.jsp"></jsp:include>
    </body>
</html>