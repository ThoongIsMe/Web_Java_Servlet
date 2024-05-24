package quantrivien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quantrivien.models.DichVu;
import utils.JDBCUtils;

public class DichVuDao {
	private static final String SELECT_ALL_DICHVU = "select * from cacloaigiay";
	private static final String SELECT_DICHVU_BY_MADV = "select * from cacloaigiay where maloaigiay = ? ";
	private static final String INSERT_DICHVU = "insert into cacloaigiay (maloaigiay, tengiayxn, tinhtrang) values (?, ?, ?)";
	private static final String DELETE_DICHVU_BY_MADV = "update cacloaigiay set tinhtrang = 0 where maloaigiay = ?";
	private static final String UPDATE_DICHVU = "update cacloaigiay set tengiayxn = ?, tinhtrang = ? where maloaigiay = ?";
	private static final String NEXT_MADV = "select NextMaDV()";
	public List < DichVu > selectAllDichVu() {
        List < DichVu > dichvus = new ArrayList < > ();

        try (Connection connection = JDBCUtils.getConnection();
        		
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DICHVU);) {
        	
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	String maDichVu = rs.getString("MaLoaiGiay");
                String tenDichVu = rs.getString("TenGiayXN");
                int tinhTrang = rs.getInt("TinhTrang");
                String tinhTrangString = "Đang hoạt động";
                
                if (tinhTrang == 0) {
                	tinhTrangString = "Đã xóa";
				}
                else if (tinhTrang == 1) {
                	tinhTrangString = "Đang hoạt động";
                }
                else if (tinhTrang == 2) {
                	tinhTrangString = "Đang bảo trì";
				}
        
                dichvus.add(new DichVu(maDichVu, tenDichVu, tinhTrangString));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return dichvus;
    }
	
	public DichVu selectDichVuTheoMaDV(String maDVinput) {
        DichVu dichvu = null;

        try (Connection connection = JDBCUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DICHVU_BY_MADV);) {
        	preparedStatement.setString(1, maDVinput);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String maDV = rs.getString("MaLoaiGiay");
            	String tenDV = rs.getString("TenGiayXN");
                int tinhTrang = rs.getInt("TinhTrang");

                dichvu = new DichVu(maDV, tenDV, tinhTrang);          
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return dichvu;
    }
	
	public String MaDVNext() {
	    String nextMaDV = "";

	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(NEXT_MADV);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	            nextMaDV = resultSet.getString(1);
	        }
	    } catch (SQLException exception) {
	        JDBCUtils.printSQLException(exception);
	    }

	    return nextMaDV;
	}
	
	public int themDichVu(DichVu dichvu) throws SQLException {
		int result = 0;
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DICHVU)) {
            preparedStatement.setString(1, MaDVNext());
            preparedStatement.setString(2, dichvu.getTenDV());
            preparedStatement.setInt(3, 1);
            
            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return result;
    }
	
	public int capNhatDichVu(DichVu dichvu) throws SQLException {
		int result = 0;
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DICHVU)) {
            preparedStatement.setString(1, dichvu.getTenDV());
            preparedStatement.setInt(2, dichvu.getTinhTrang());
        	preparedStatement.setString(3, dichvu.getMaDV());
            
            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return result;
    }
	
	public void xoaDichVu(String maDV) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DICHVU_BY_MADV)) {
            preparedStatement.setString(1, maDV);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
}