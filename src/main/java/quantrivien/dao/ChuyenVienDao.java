package quantrivien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import quantrivien.models.ChuyenVien;
import utils.JDBCUtils;

public class ChuyenVienDao {
	private static final String SELECT_ALL_CHUYENVIEN = "select * from phongctsv where tinhtrang = 1";
	private static final String SELECT_CHUYENVIEN_BY_MACV = "select * from phongctsv where MaCV = ? and tinhtrang = 1";
	private static final String INSERT_CHUYENVIEN = "insert into phongctsv (macv, tencv, email, sodienthoai, diachi, ngaybatdaulam, tinhtrang, quyenhan) " 
												+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_CHUYENVIEN_BY_MACV = "update phongctsv set tinhTrang = 0 where MaCV = ?";
	private static final String DELETE_TKCHUYENVIEN_BY_MACV = "update taikhoan set tinhTrang = 0 where username = ?";
	private static final String UPDATE_CHUYENVIEN = "update phongctsv set tencv = ?,  email = ?, sodienthoai = ?, diachi = ?, ngaybatdaulam = ?, "
													+ "tinhTrang = ?, quyenhan = ? where maCV = ?";
	private static final String FIND_CHUYENVIEN = "select * from phongctsv where macv = ? or tencv like ?";
	private static final String NEXT_MACV = "select NextMaCV()";
	private static final String CREATE_TAIKHOAN = "INSERT INTO taikhoan (select NextMaCV(), '1', '2', '1')";
	public List < ChuyenVien > selectAllChuyenVien() {
        List < ChuyenVien > chuyenviens = new ArrayList < > ();

        try (Connection connection = JDBCUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CHUYENVIEN);) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String tenCV = rs.getString("TenCV");
                String maCV = rs.getString("MaCV");
                String quyenHan = rs.getString("QuyenHan");
                String quyenHanString = "";
                if ("1".equals(quyenHan)) {
					quyenHanString = "Đầy đủ";
				} else {
					quyenHanString =  "Hạn chế";
				}
                chuyenviens.add(new ChuyenVien(tenCV, maCV, quyenHanString));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return chuyenviens;
    }
	
	public ChuyenVien selectChuyenVienTheoMaCV(String maCVinput) {
        ChuyenVien chuyenvien = null;

        try (Connection connection = JDBCUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CHUYENVIEN_BY_MACV);) {
        	preparedStatement.setString(1, maCVinput);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String maCV = rs.getString("MaCV");
            	String tenCV = rs.getString("TenCV");
                String email = rs.getString("Email");
                String sdt = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                LocalDate ngayCongTac = rs.getDate("NgayBatDauLam").toLocalDate();
                String tinhTrang = rs.getString("TinhTrang");
                String quyenHan= rs.getString("QuyenHan");
                

                chuyenvien = new ChuyenVien(maCV, tenCV, email, sdt, diaChi, ngayCongTac, tinhTrang, quyenHan);          
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return chuyenvien;
    }
	
	public int themChuyenVien(ChuyenVien chuyenvien) throws SQLException {
		int result = 0;
        try (Connection connection = JDBCUtils.getConnection(); 
    		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHUYENVIEN);
    		PreparedStatement preparedStatement0 = connection.prepareStatement(CREATE_TAIKHOAN)	) {
            preparedStatement.setString(1, MaCVNext());
            preparedStatement.setString(2, chuyenvien.getTenCV());
            preparedStatement.setString(3, chuyenvien.getEmail());
            preparedStatement.setString(4, chuyenvien.getSdt());
            preparedStatement.setString(5, chuyenvien.getDiaChi());
            Date ngayCongTac = Date.valueOf(chuyenvien.getNgayCongTac());
            preparedStatement.setDate(6, ngayCongTac);
            preparedStatement.setInt(7, 1);
            preparedStatement.setString(8, chuyenvien.getQuyenHan());
            
            preparedStatement0.executeUpdate();
            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return result;
    }
	
	public int capNhatChuyenVien(ChuyenVien chuyenvien) throws SQLException {
		int result = 0;
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CHUYENVIEN)) {
            preparedStatement.setString(1, chuyenvien.getTenCV());
            preparedStatement.setString(2, chuyenvien.getEmail());
            preparedStatement.setString(3, chuyenvien.getSdt());
            preparedStatement.setString(4, chuyenvien.getDiaChi());
            Date ngayCongTac = Date.valueOf(chuyenvien.getNgayCongTac());
            preparedStatement.setDate(5, ngayCongTac);
            preparedStatement.setString(6, chuyenvien.getTinhTrang());
            preparedStatement.setString(7, chuyenvien.getQuyenHan());
            preparedStatement.setString(8, chuyenvien.getMaCV());
            
            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return result;
    }
	
	public String MaCVNext() {
	    String nextMaCV = "";

	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(NEXT_MACV);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	            nextMaCV = resultSet.getString(1);
	        }
	    } catch (SQLException exception) {
	        JDBCUtils.printSQLException(exception);
	    }

	    return nextMaCV;
	}
	
	public void xoaChuyenVien(String maCV) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHUYENVIEN_BY_MACV);
        PreparedStatement preparedStatement0 = connection.prepareStatement(DELETE_TKCHUYENVIEN_BY_MACV)) {
            preparedStatement.setString(1, maCV);
            preparedStatement0.setString(1, maCV);
            preparedStatement.executeUpdate();
            preparedStatement0.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
	
	public List <ChuyenVien> timChuyenVien(String input) {
        List < ChuyenVien > chuyenviens = new ArrayList < > ();

        try (Connection connection = JDBCUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(FIND_CHUYENVIEN);) {
        	preparedStatement.setString(1, input);
        	String likeParameter = "%" + input + "%";
        	preparedStatement.setString(2, likeParameter);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	ChuyenVien chuyenVien = null;
                String maCV = rs.getString("MaCV");
                String tenCV = rs.getString("TenCV");
                String email = rs.getString("Email");
                String sdt = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                LocalDate ngayBatDauLam = rs.getDate("NgayBatDauLam").toLocalDate();
                String tinhTrang = rs.getString("TinhTrang");
                String quyenHan = rs.getString("QuyenHan");

                chuyenVien = new ChuyenVien(maCV, tenCV, email, sdt, diaChi, ngayBatDauLam, tinhTrang, quyenHan);
                chuyenviens.add(chuyenVien);        
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return chuyenviens;
    }
}