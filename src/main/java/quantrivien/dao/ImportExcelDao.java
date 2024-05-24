package quantrivien.dao;

import java.io.*;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import quantrivien.models.SinhVien;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.poi.ss.usermodel.Row;

public class ImportExcelDao {
	private static final String INSERT_SINHVIEN_IMPORT_TEMP = "insert into thongtinsvtemp (hoTen, ngaySinh, noiSinh, gioiTinh, danToc, soCCCD,"
			+ "sdt, email, diaChiSV, khoaHoc, nienKhoa, namNhapHoc, namHetHanHoc, ctDaoTao, lopSV) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CREATE_TAIKHOAN = "call TaoTKSV();";
	private static final String INSERT_SINHVIEN_IMPORT = "insert into thongtinsv (maSV, hoTen, ngaySinh, noiSinh, gioiTinh, danToc, soCCCD, sdt, email, diaChiSV, khoaHoc, "
			+ "nienKhoa, namNhapHoc, namHetHanHoc, ctDaoTao, lopSV, tinhTrang) "
			+ "select MaSVTiepTheo() ,hoTen, ngaySinh, noiSinh, gioiTinh, danToc, soCCCD, sdt, email, diaChiSV, khoaHoc, nienKhoa, "
			+ "namNhapHoc, namHetHanHoc, ctDaoTao, lopSV, 1 from thongtinsvtemp; ";

	private static final String DELETE_SINHVIEN_IMPORT_TEMP = "delete from thongtinsvtemp;";

	@SuppressWarnings("resource")
	public void importFile(HttpServletRequest request, InputStream inputStream) {
		try (Connection connection = JDBCUtils.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SINHVIEN_IMPORT_TEMP)) {
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			// Use XSSFWorkbook for .xlsx files and HSSFWorkbook for .xls files
			Workbook workbook;
			if (request.getPart("file").getContentType().equals("application/vnd.ms-excel")) {
				workbook = new HSSFWorkbook(inputStream);
			} else if (request.getPart("file").getContentType()
					.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				workbook = new XSSFWorkbook(inputStream);
			} else {
				throw new ServletException("Invalid file type");
			}

			Sheet sheet = workbook.getSheetAt(0);
			int i = 1;
			Row row = sheet.getRow(i);
			if (row != null)
				try {
					{
						while (row.getCell(0) != null) {

							SinhVien sv = new SinhVien();
							sv.setTenSV(row.getCell(0).getStringCellValue());

							Date ngaySinhDate = row.getCell(1).getDateCellValue();
							Instant instant = ngaySinhDate.toInstant();
							LocalDate ngaySinhLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
							sv.setNgaySinh(ngaySinhLocalDate);

							sv.setNoiSinh(row.getCell(2).getStringCellValue());
							sv.setGioiTinh(row.getCell(3).getStringCellValue());
							sv.setDanToc(row.getCell(4).getStringCellValue());

							DecimalFormat decimalFormat = new DecimalFormat("#");
							String cccdString = decimalFormat.format(row.getCell(5).getNumericCellValue());
							sv.setCccd(cccdString);
							sv.setSdt(row.getCell(6).getStringCellValue());
							sv.setEmail(row.getCell(7).getStringCellValue());
							sv.setDiaChi(row.getCell(8).getStringCellValue());
							sv.setKhoa(row.getCell(9).getStringCellValue());
							sv.setNienKhoa(row.getCell(10).getStringCellValue());
							sv.setNamNhapHoc((int) row.getCell(11).getNumericCellValue());
							sv.setNamKetThuc((int) row.getCell(12).getNumericCellValue());
							sv.setCtDaoTao(row.getCell(13).getStringCellValue());
							sv.setLop(row.getCell(14).getStringCellValue());

							InsertExcelToDBTemp(sv);

							i = i + 1;
							row = sheet.getRow(i);
						}
						workbook.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			else
				throw new ServletException("Invalid file type");

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void InsertExcelToDBTemp(SinhVien sv) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SINHVIEN_IMPORT_TEMP)) {
			preparedStatement.setString(1, sv.getTenSV());
			java.sql.Date ngaysinhDate = java.sql.Date.valueOf(sv.getNgaySinh());
			preparedStatement.setDate(2, ngaysinhDate);
			preparedStatement.setString(3, sv.getNoiSinh());
			preparedStatement.setString(4, sv.getGioiTinh());
			preparedStatement.setString(5, sv.getDanToc());
			preparedStatement.setString(6, sv.getCccd());
			preparedStatement.setString(7, sv.getSdt());
			preparedStatement.setString(8, sv.getEmail());
			preparedStatement.setString(9, sv.getDiaChi());
			preparedStatement.setString(10, sv.getKhoa());
			preparedStatement.setString(11, sv.getNienKhoa());
			preparedStatement.setInt(12, sv.getNamNhapHoc());
			preparedStatement.setInt(13, sv.getNamKetThuc());
			preparedStatement.setString(14, sv.getCtDaoTao());
			preparedStatement.setString(15, sv.getLop());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	public void InsertExcelToDB(SinhVien sv) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SINHVIEN_IMPORT)) {
			preparedStatement.setString(1, sv.getTenSV());
			java.sql.Date ngaysinhDate = java.sql.Date.valueOf(sv.getNgaySinh());
			preparedStatement.setDate(2, ngaysinhDate);
			preparedStatement.setString(3, sv.getNoiSinh());
			preparedStatement.setString(4, sv.getGioiTinh());
			preparedStatement.setString(5, sv.getDanToc());
			preparedStatement.setString(6, sv.getCccd());
			preparedStatement.setString(7, sv.getSdt());
			preparedStatement.setString(8, sv.getEmail());
			preparedStatement.setString(9, sv.getDiaChi());
			preparedStatement.setString(10, sv.getKhoa());
			preparedStatement.setString(11, sv.getNienKhoa());
			preparedStatement.setInt(12, sv.getNamNhapHoc());
			preparedStatement.setInt(13, sv.getNamKetThuc());
			preparedStatement.setString(14, sv.getCtDaoTao());
			preparedStatement.setString(15, sv.getLop());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	public int themSinhVienFromFile() throws SQLException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection()) {
			try (PreparedStatement preparedStatement0 = connection.prepareStatement(CREATE_TAIKHOAN)) {
				preparedStatement0.executeUpdate();
			}
		}
		try (Connection connection = JDBCUtils.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SINHVIEN_IMPORT)) {
				result = preparedStatement.executeUpdate();
			}

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_SINHVIEN_IMPORT_TEMP)) {
				preparedStatement1.executeUpdate();
			}
		}
		return result;
	}
}