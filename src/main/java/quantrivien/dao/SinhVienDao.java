package quantrivien.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import quantrivien.models.SinhVien;
import utils.JDBCUtils;

public class SinhVienDao {
	private static final String SELECT_ALL_SINHVIEN = "select * from thongtinsv where tinhTrang = 1";
	private static final String SELECT_ALL_SINHVIEN_IMPORT = "select hoten, ngaysinh, socccd, nienkhoa, lopsv from thongtinsvtemp";
	private static final String SELECT_SINHVIEN_BY_MASV = "select * from thongtinsv where MaSV = ? and tinhTrang = 1";
	private static final String INSERT_SINHVIEN = "insert into thongtinsv (maSV, hoTen, ngaySinh, noiSinh, gioiTinh, danToc, soCCCD,"
												+ "sdt, email, diaChiSV, khoaHoc, nienKhoa, namNhapHoc, namHetHanHoc, ctDaoTao, lopSV, tinhTrang, anhTheLink) " 
												+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_SINHVIEN_BY_MASV = "update thongtinsv set tinhTrang = 0 where MaSV = ?";
	private static final String DELETE_TKSINHVIEN_BY_MASV = "update taikhoan set tinhTrang = 0 where username = ?";
	private static final String UPDATE_SINHVIEN = "update thongtinsv set hoTen = ?, ngaySinh = ?, noiSinh = ?, gioiTinh = ?, danToc = ?, soCCCD = ?, " 
												+ "sdt = ?, email = ?, diaChiSV = ?, khoaHoc = ?, nienKhoa = ?, namNhapHoc = ?, namHetHanHoc = ?, ctDaoTao = ?, "
												+ "lopSV = ?, tinhTrang = ? where maSV = ?";
	private static final String FIND_SINHVIEN = "select * from thongtinsv where masv = ? or hoten like ?";
	private static final String NEXT_MASV = "select MaSVTiepTheo()";
	private static final String CREATE_TAIKHOAN = "INSERT INTO taikhoan (select MaSVTiepTheo(), '1', '1', '1')";
		
	public List < SinhVien > selectAllSinhVien() {
        List < SinhVien > sinhviens = new ArrayList < > ();

        try (Connection connection = JDBCUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SINHVIEN);) {
        	
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String tenSV = rs.getString("HoTen");
                String maSV = rs.getString("MaSV");
                sinhviens.add(new SinhVien(tenSV, maSV));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return sinhviens;
    }
	
	public List < SinhVien > selectAllSinhVienImport() {
        List < SinhVien > sinhviens = new ArrayList < > ();

        try (Connection connection = JDBCUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SINHVIEN_IMPORT);) {     	
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String tenSV = rs.getString("HoTen");
                LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String ngaySinhString = ngaySinh.format(formatter);
                String cccd = rs.getString("SoCCCD");
                String nienKhoa = rs.getString("NienKhoa");
                String lop = rs.getString("LopSV");
                sinhviens.add(new SinhVien(tenSV, ngaySinhString, cccd, nienKhoa, lop));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return sinhviens;
    }
	
	public SinhVien selectSinhVienTheoMaSV(String maSVinput) {
        SinhVien sinhvien = null;

        try (Connection connection = JDBCUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SINHVIEN_BY_MASV);) {
        	preparedStatement.setString(1, maSVinput);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	String tenSV = rs.getString("HoTen");
                String maSV = rs.getString("MaSV");
                LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
                String noiSinh = rs.getString("NoiSinh");
                String gioiTinh = rs.getString("GioiTinh");
                String danToc = rs.getString("DanToc");
                String cccd = rs.getString("SoCCCD");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChiSV");
                String khoa = rs.getString("KhoaHoc");
                String nienKhoa = rs.getString("NienKhoa");
                int namNhapHoc = rs.getInt("NamNhapHoc");
                int namKetThuc = rs.getInt("NamHetHanHoc");
                String ctDaoTao = rs.getString("CTDaoTao");
                String lop = rs.getString("LopSV");
                String tinhTrang = rs.getString("TinhTrang");
                String anhThe = rs.getString("AnhThe");

                sinhvien = new SinhVien(maSV, tenSV, ngaySinh, noiSinh, gioiTinh, danToc, cccd, sdt, email,
                        diaChi, khoa, nienKhoa, namNhapHoc, namKetThuc, ctDaoTao, lop, tinhTrang, anhThe);
            
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return sinhvien;
    }
	
	public int themSinhVien(SinhVien sinhvien) throws SQLException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection()) {
			try (PreparedStatement preparedStatement0 = connection.prepareStatement(CREATE_TAIKHOAN)) {
		    	preparedStatement0.executeUpdate();
		    }
    	}
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SINHVIEN)) {
            preparedStatement.setInt(1, MaSVNext());
            preparedStatement.setString(2, sinhvien.getTenSV());
            Date ngaySinhDate = Date.valueOf(sinhvien.getNgaySinh());
            preparedStatement.setDate(3, ngaySinhDate);
            preparedStatement.setString(4, sinhvien.getNoiSinh());
            preparedStatement.setString(5, sinhvien.getGioiTinh());
            preparedStatement.setString(6, sinhvien.getDanToc());
            preparedStatement.setString(7, sinhvien.getSdt());
            preparedStatement.setString(8, sinhvien.getCccd());
            preparedStatement.setString(9, sinhvien.getEmail());
            preparedStatement.setString(10, sinhvien.getDiaChi());
            preparedStatement.setString(11, sinhvien.getKhoa());
            preparedStatement.setString(12, sinhvien.getNienKhoa());
            preparedStatement.setInt(13, sinhvien.getNamNhapHoc());
            preparedStatement.setInt(14, sinhvien.getNamKetThuc());
            preparedStatement.setString(15, sinhvien.getCtDaoTao());
            preparedStatement.setString(16, sinhvien.getLop());
            preparedStatement.setInt(17, 1);
            preparedStatement.setString(18, sinhvien.getAnhThe());
            
            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return result;
    }
	
	public int capNhatSinhVien(SinhVien sinhvien) throws SQLException {
		int result = 0;
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SINHVIEN)) {
            preparedStatement.setString(1, sinhvien.getTenSV());
            Date ngaySinhDate = Date.valueOf(sinhvien.getNgaySinh());
            preparedStatement.setDate(2, ngaySinhDate);
            preparedStatement.setString(3, sinhvien.getNoiSinh());
            preparedStatement.setString(4, sinhvien.getGioiTinh());
            preparedStatement.setString(5, sinhvien.getDanToc());
            preparedStatement.setString(6, sinhvien.getCccd());
            preparedStatement.setString(7, sinhvien.getSdt());
            preparedStatement.setString(8, sinhvien.getEmail());
            preparedStatement.setString(9, sinhvien.getDiaChi());
            preparedStatement.setString(10, sinhvien.getKhoa());
            preparedStatement.setString(11, sinhvien.getNienKhoa());
            preparedStatement.setInt(12, sinhvien.getNamNhapHoc());
            preparedStatement.setInt(13, sinhvien.getNamKetThuc());
            preparedStatement.setString(14, sinhvien.getCtDaoTao());
            preparedStatement.setString(15, sinhvien.getLop());
            preparedStatement.setString(16, sinhvien.getTinhTrang());
            preparedStatement.setString(17, sinhvien.getMaSV());
            //preparedStatement.setString(18, sinhvien.getAnhThe());
            

            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return result;
    }
	
	public int MaSVNext() {
	    int nextMaSV = 0;

	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(NEXT_MASV);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	            nextMaSV = resultSet.getInt(1);
	        }
	    } catch (SQLException exception) {
	        JDBCUtils.printSQLException(exception);
	    }

	    return nextMaSV;
	}
	
	public void xoaSinhVien(String maSV) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SINHVIEN_BY_MASV);
        PreparedStatement preparedStatement0 = connection.prepareStatement(DELETE_TKSINHVIEN_BY_MASV)) {
            preparedStatement.setString(1, maSV);
            preparedStatement0.setString(1, maSV);
            preparedStatement.executeUpdate();
            preparedStatement0.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
	
	public List <SinhVien> timSinhVien(String input) {
        List < SinhVien > sinhviens = new ArrayList < > ();

        try (Connection connection = JDBCUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(FIND_SINHVIEN);) {
        	preparedStatement.setString(1, input);
        	String likeParameter = "%" + input + "%";
        	preparedStatement.setString(2, likeParameter);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	SinhVien sinhVien = null;
                String maCV = rs.getString("MaSV");
                String tenCV = rs.getString("HoTen");

                sinhVien = new SinhVien(tenCV, maCV);
                sinhviens.add(sinhVien);        
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return sinhviens;
    }
}