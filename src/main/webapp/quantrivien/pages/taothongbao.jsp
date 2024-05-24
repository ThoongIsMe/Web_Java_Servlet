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
                <form action="thongbao" method="post">
                    <input type="hidden" name="action" value="guithongbao">
	                <caption>
	                    <h2>Tạo thông báo mới</h2>
	                </caption>	
	
	                <fieldset class="row g-3">
	                	<div class="col-md-12">
						    <label>Tiêu đề</label><input type="text" class="form-control" name="tieude" required/>
						    <label>Nội dung</label><textarea class="form-control" name="noidung" required></textarea>
							<label>Người nhận</label> 
							<select class="form-control" name="nguoinhan">
					            <option value="sinhvien">Sinh viên</option>
					            <option value="chuyenvien">Chuyên viên</option>
					            <option value="tatca">Tất cả</option>
							</select>      	
						</div>
	                </fieldset>  
					<br>
					<div class="d-flex flex-row-reverse justify-content-around">
		                <button onclick="check()" type="submit" class="btn btn-info col-2">Gửi</button>				
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