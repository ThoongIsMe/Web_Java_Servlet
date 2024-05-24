package quantrivien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quantrivien.models.ThongBao;
import utils.JDBCUtils;

public class ThongBaoDao {
	private static final String SELECT_THONGBAO = "select distinct tieude, noidung, thoigian from thongbao where nguoigui = ?;";
	private static final String SEND_THONGBAO_SINHVIEN = "select GuiThongBaoSinhVien(?, ?, ?)";
	private static final String SEND_THONGBAO_SINHVIENBYMASV = "INSERT INTO thongbao (tieude, noidung, nguoigui, thoigian, nguoinhan) values (?, ?, ?, curdate(), ?)";
	private static final String SEND_THONGBAO_CHUYENVIEN = "select GuiThongBaoChuyenVien(?, ?, ?)";

	public List<ThongBao> getAllThongbaos(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<ThongBao> listThongbaos = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THONGBAO)) {
			preparedStatement.setString(1, (String) session.getAttribute("name_label"));
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// int id = resultSet.getInt(1);
				String TieuDe = resultSet.getString(1);
				String NoiDung = resultSet.getString(2);
//						String NguoiGui = resultSet.getString(4);
				LocalDate thoiGian = resultSet.getDate(3).toLocalDate();
//						String Nguoinhan = resultSet.getString(6);
				listThongbaos.add(new ThongBao(TieuDe, NoiDung, thoiGian));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}

		return listThongbaos;
	}

	public void GuiThongBaoSinhVien(HttpServletRequest request, HttpServletResponse response, String tieuDe,
			String noiDung) {
		HttpSession session = request.getSession();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SEND_THONGBAO_SINHVIEN)) {
			preparedStatement.setString(1, tieuDe);
			preparedStatement.setString(2, noiDung);
			preparedStatement.setString(3, (String) session.getAttribute("name_label"));
			preparedStatement.executeQuery();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	public void GuiThongBaoSinhVienToMaSV(HttpServletRequest request, HttpServletResponse response, String tieuDe,
			String noiDung, String maSV) {
		HttpSession session = request.getSession();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SEND_THONGBAO_SINHVIENBYMASV)) {
			preparedStatement.setString(1, tieuDe);
			preparedStatement.setString(2, noiDung);
			preparedStatement.setString(3, (String) session.getAttribute("name_label"));
			preparedStatement.setString(4, maSV);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	public void GuiThongBaoChuyenVien(HttpServletRequest request, HttpServletResponse response, String tieuDe,
			String noiDung) {
		HttpSession session = request.getSession();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SEND_THONGBAO_CHUYENVIEN)) {
			preparedStatement.setString(1, tieuDe);
			preparedStatement.setString(2, noiDung);
			preparedStatement.setString(3, (String) session.getAttribute("name_label"));
			preparedStatement.executeQuery();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}
}