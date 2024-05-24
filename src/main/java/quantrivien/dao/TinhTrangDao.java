package quantrivien.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JDBCUtils;

public class TinhTrangDao {
	private static final String COUNT_SOTAIKHOAN = "select count(distinct username) as total_taikhoan from taikhoan where tinhtrang = 1";
	private static final String COUNT_SINHVIEN = "select count(distinct masv) as total_sinhvien from thongtinsv where tinhtrang = 1";
	private static final String COUNT_SINHVIEN_IMPORT = "select count(hoten) as total_sinhvien from thongtinsvtemp";
	private static final String COUNT_CHUYENVIEN = "select count(distinct macv) as total_chuyenvien from phongctsv where tinhtrang = 1";
	private static final String COUNT_LUOTDUNGDICHVU = "select count(distinct id) as total_dichvu from giayxacnhan where month(thoigianxin) = ?";
	private static final String COUNT_LUOTTRUYCAP = "select tengiayxn from cacloaigiay where maloaigiay = 'Visitor';";
	private static final String SET_LUOTTRUYCAP = "update cacloaigiay set tengiayxn = cast(tengiayxn as signed) + 1 where maloaigiay = 'Visitor';";
	////

	private static final String COUNT_SUM_ACCESS = "SELECT COUNT(*) \r\n" + "FROM timelogin\r\n"
			+ "WHERE MONTH(timestamp) = MONTH(NOW()) AND YEAR(timestamp) = YEAR(NOW());";

	public int getCountSumAccess() {
		int count = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SUM_ACCESS);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// Log the exception or throw a custom exception
			System.err.println("Error while fetching count: " + e.getMessage());
		}

		return count;
	}

	/////
	public int countSoTaiKhoan() {
		int soTaiKhoan = 0;

		try (Connection connection = JDBCUtils.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SOTAIKHOAN);) {
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				soTaiKhoan = rs.getInt(1);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return soTaiKhoan;
	}

	public int countSinhVien() {
		int soSinhVien = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SINHVIEN);) {

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				soSinhVien = rs.getInt(1);
			}

		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return soSinhVien;
	}

	public int countSinhVienImport() {
		int soSinhVien = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SINHVIEN_IMPORT);) {

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				soSinhVien = rs.getInt(1);
			}

		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return soSinhVien;
	}

	public int countChuyenVien() {
		int soChuyenVien = 0;

		try (Connection connection = JDBCUtils.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_CHUYENVIEN);) {
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				soChuyenVien = rs.getInt(1);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return soChuyenVien;
	}

	public int countDichVu() {
		int soDichVu = 0;

		try (Connection connection = JDBCUtils.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_LUOTDUNGDICHVU);) {
			LocalDate currentDate = LocalDate.now();

			int month = currentDate.getMonthValue();

			preparedStatement.setInt(1, month);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				soDichVu = rs.getInt(1);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return soDichVu;
	}

	public void setLuotTruyCap(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (Connection connection = JDBCUtils.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SET_LUOTTRUYCAP);) {
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	public String countLuotTruyCap() {
		String luotTruyCap = "";

		try (Connection connection = JDBCUtils.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_LUOTTRUYCAP);) {

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				luotTruyCap = rs.getString(1);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return luotTruyCap;
	}
}