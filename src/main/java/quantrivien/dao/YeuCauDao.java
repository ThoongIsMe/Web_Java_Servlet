package quantrivien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quantrivien.models.YeuCau;
import utils.EmailUtility;
import utils.JDBCUtils;

public class YeuCauDao {
	private static final String SELECT_ALL_YEUCAU = "select xn.*, dv.tengiayxn from giayxacnhan as xn inner join cacloaigiay as dv on dv.maloaigiay = xn.maloaigiay where trangthai = 1";
	private static final String SELECT_YEUCAU_BY_ID = "select xn.*, dv.tengiayxn from giayxacnhan as xn inner join cacloaigiay as dv on dv.maloaigiay = xn.maloaigiay where trangthai = 1 and id = ?";
	private static final String ACCEPT_YEUCAU = "update giayxacnhan set trangthai = 2, thoigianduyet = curdate(), macv = ? where id = ? ";
	private static final String DENY_YEUCAU = "update giayxacnhan set trangthai = 0 where id = ?";
	private static final String FIND_EMAIL = "select distinct sv.email from thongtinsv as sv inner join giayxacnhan as xn on sv.masv = xn.masv and xn.id = ?";
	private static final String ACCEPT_ALL_YEUCAU = "update giayxacnhan set trangthai = 2, thoigianduyet = curdate(), macv = ?  where trangthai = 1";
	private ThongBaoDao thongBaoDao = new ThongBaoDao();

	public List<YeuCau> selectAllYeuCau() {
		List<YeuCau> yeucaus = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_YEUCAU);) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String maSV = rs.getString("MaSV");
				LocalDate thoiGianXin = rs.getDate("ThoiGianXin").toLocalDate();
				String lyDo = rs.getString("LyDo");
				int trangThai = rs.getInt("TrangThai");
				String trangThaiString = "Chưa duyệt";
				if (trangThai == 2) {
					trangThaiString = "Đã duyệt";
				}
				String tenDV = rs.getString("TenGiayXN");
				int sl = rs.getInt("SL");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				String thoiGianXinString = thoiGianXin.format(formatter);

				yeucaus.add(new YeuCau(id, maSV, thoiGianXinString, lyDo, trangThaiString, tenDV, sl));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return yeucaus;
	}

	public int duyetYeuCau(int id, HttpServletRequest request, HttpServletResponse response, String host, String port,
			String user, String pass) throws SQLException {
		HttpSession session = request.getSession();
		int result = 0;
		String emailSVString = "";
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT_YEUCAU);
				PreparedStatement preparedStatement0 = connection.prepareStatement(SELECT_YEUCAU_BY_ID);
				PreparedStatement preparedStatement1 = connection.prepareStatement(FIND_EMAIL)) {
			preparedStatement1.setInt(1, id);
			ResultSet resultSet = preparedStatement1.executeQuery();
			if (resultSet.next()) {
				emailSVString = resultSet.getString(1);
			}

			preparedStatement0.setInt(1, id);
			ResultSet rs = preparedStatement0.executeQuery();
			String maSV = "", tenDV = "", noiDung;
			while (rs.next()) {
				maSV = rs.getString("MaSV");
				tenDV = rs.getString("TenGiayXN");
			}

			preparedStatement.setString(1, (String) session.getAttribute("name_label"));
			preparedStatement.setInt(2, id);
			result = preparedStatement.executeUpdate();

			noiDung = "Thân gửi sinh viên " + maSV + " ,<br>" + "Yêu cầu của bạn đã dược duyệt:<br>"
					+ "1. Sinh viên nhận giấy " + tenDV + " kể từ ngày nhận thông báo<br>"
					+ "2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy.<br>"
					+ "3. Sinh viên cung cấp Mã sinh viên và họ tên cho nhân viên trả giấy.";

			String subject = "Phong Cong Tac Sinh Vien [DHSPKT]";
			String content = "Thân gửi sinh viên," + "<br>" + "Yêu cầu của bạn đã được duyệt:" + "<br>"
					+ "1. Sinh viên nhận giấy " + tenDV + " theo thời gian bắt đầu nhận: " + Instant.now() // Replace
																											// giayxacnhan.getThoiGianDuyet()
																											// with
																											// formatted
																											// string
					+ "<br>"
					+ "2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy."
					+ "<br>" + " 3. Sinh viên cung cấp Mã sinh viên và họ tên cho nhân viên trả giấy.";
			try {
				EmailUtility.sendEmail(host, port, user, pass, emailSVString, subject, content);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.print(host + " " + port + " " + user + " " + pass);
			thongBaoDao.GuiThongBaoSinhVienToMaSV(request, response, tenDV, noiDung, maSV);
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return result;
	}

	public int duyetAllYeuCau(HttpServletRequest request, HttpServletResponse response, String host, String port,
			String user, String pass) throws SQLException {
		HttpSession session = request.getSession();
		int result = 100;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement0 = connection.prepareStatement(SELECT_ALL_YEUCAU);
				PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT_ALL_YEUCAU);
				PreparedStatement preparedStatement1 = connection.prepareStatement(FIND_EMAIL)) {

			ResultSet rs = preparedStatement0.executeQuery();
			while (rs.next()) {
				String maSV = "", tenDV = "", noiDung, emailSVString = "";
				int id = rs.getInt("id");
				maSV = rs.getString("MaSV");
				tenDV = rs.getString("TenGiayXN");
				noiDung = "Thân gửi sinh viên " + maSV + " ,<br>" + "Yêu cầu của bạn đã dược duyệt:<br>"
						+ "1. Sinh viên nhận giấy " + tenDV + " kể từ ngày nhận thông báo<br>"
						+ "2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy.<br>"
						+ "3. Sinh viên cung cấp Mã sinh viên và họ tên cho nhân viên trả giấy.";

				preparedStatement1.setInt(1, id);
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					emailSVString = resultSet.getString(1);
				}

				String subject = "Phong Cong Tac Sinh Vien [DHSPKT]";
				String content = "Thân gửi sinh viên," + "<br>" + "Yêu cầu của bạn đã được duyệt:" + "<br>"
						+ "1. Sinh viên nhận giấy " + tenDV + " theo thời gian bắt đầu nhận: " + Instant.now() // Replace
																												// giayxacnhan.getThoiGianDuyet()
																												// with
																												// formatted
																												// string
						+ "<br>"
						+ "2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy."
						+ "<br>" + " 3. Sinh viên cung cấp Mã sinh viên và họ tên cho nhân viên trả giấy.";
				try {
					EmailUtility.sendEmail(host, port, user, pass, emailSVString, subject, content);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				thongBaoDao.GuiThongBaoSinhVienToMaSV(request, response, tenDV, noiDung, maSV);
			}
			preparedStatement.setString(1, (String) session.getAttribute("name_label"));
			result = preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return result;
	}

	public int tuChoiYeuCau(int id) throws SQLException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DENY_YEUCAU)) {
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return result;
	}
}