package sinhvien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import chuyenvien.models.CacLoaiGiay;
import chuyenvien.models.Giayxacnhan;
import chuyenvien.models.PhongCTSV;
import chuyenvien.models.TaiKhoan;
import chuyenvien.models.ThongTinSV;
import chuyenvien.models.YeuCauXacNhan;
import chuyenvien.models.thongbao;
import utils.JDBCUtils;

public class dao {
	private static final String SELECT_LOGIN = "SELECT * FROM taikhoan WHERE Username = ? AND Password = ? AND Role = ?;";

	
	
	private static final String SELECT_ALLCV = "SELECT * FROM phongctsv where TinhTrang=1;";

	private static final String UPDATE_GIAYXN = "UPDATE giayxacnhan\r\n"
			+ "SET MaCV = ? , TrangThai = ?, ThoiGianDuyet = CURRENT_TIMESTAMP\r\n" + "WHERE id = ?;";
	private static final String UPDATE_GIAYXNNHAN = "UPDATE giayxacnhan\r\n"
			+ "SET TrangThai = ?, ThoiGianNhan =  DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 3 DAY)\r\n" + "WHERE id = ?;\r\n"
			+ "";
	private static final String UPDATE_Password = "UPDATE taikhoan SET Password = ? WHERE Username = ?";
	private static final String UPDATE_ThongTinSV = "UPDATE thongtinsv SET SDT = ? , Email = ?, DiaChiSV = ? WHERE MaSV = ?";

	private static final String SELECT_SEARCH_REQUESTS = "SELECT gx.id, clg.TenGiayXN AS TenGiay, gx.MaSV AS NguoiGui, gx.ThoiGianXin, gx.LyDo, gx.TrangThai, gx.SL\r\n"
			+ "FROM giayxacnhan gx\r\n" + "JOIN cacloaigiay clg ON gx.MaLoaiGiay = clg.MaLoaiGiay\r\n"
			+ "WHERE gx.MaSV = ?;\r\n";

	private static final String SELECT_SV = "select *from thongtinsv where masv = ?;";

	private static final String SELECT_searchIDMaYC = "select *from giayxacnhan where id = ?;";

	private static final String INSERNT_THONGBAO = "INSERT INTO thongbao (TieuDe, NoiDung, NguoiGui, ThoiGian, Nguoinhan)\r\n"
			+ "VALUES (?, ?, ?, ?, ?);";
	private static final String INSERT_GIAYXN="insert into giayxacnhan (MaSV,ThoiGianXin, LyDo, TrangThai, MaLoaiGiay, SL)\r\n"
			+ "values(?, CURDATE(),?, 1,?, ?)";
	private static final String SELECT_ThongBao = "SELECT * FROM thongbao;";

	private static final String searchgiayXN_byMaSV = "SELECT giayxacnhan.ThoiGianXin,  \r\n"
			+ "		giayxacnhan.LyDo,  \r\n"
			+ "		cacloaigiay.TenGiayXN, \r\n"
			+ "		giayxacnhan.SL,\r\n"
			+ "		giayxacnhan.ThoiGianNhan\r\n"
			+ "FROM giayxacnhan \r\n"
			+ "INNER JOIN cacloaigiay ON giayxacnhan.MaLoaiGiay = cacloaigiay.MaLoaiGiay \r\n"
			+ "WHERE giayxacnhan.MaSV = ?";
	
	private static final String SELECT_TenLoaigiay ="select TenGiayXN FROM cacloaigiay where MaLoaiGiay=?;";
	private static final String SELECT_CacLoaiGiay_U = "select * FROM cacloaigiay where TinhTrang=1;";
	public CacLoaiGiay getTenGiay(String MaLoaiGiay) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TenLoaigiay)) {

			preparedStatement.setString(1, MaLoaiGiay);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String TenGiay=resultSet.getString(1);

					return new CacLoaiGiay(MaLoaiGiay,TenGiay);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}
	public List<YeuCauXacNhan> getAllGiayXacNhan_BySV(int MaSV)
	{
		List<YeuCauXacNhan> requests= new ArrayList<>();
		try(Connection connection=JDBCUtils.getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(searchgiayXN_byMaSV))
		{	
	        preparedStatement.setInt(1, MaSV);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				LocalDate ThoiGianXin=rs.getDate(1).toLocalDate();
				String LyDo=rs.getString(2);
				String TenGiay=rs.getString(3);
				int Sl=rs.getInt(4);
				LocalDate ThoiGianNhan = (rs.getDate(5) != null)
						? rs.getDate(5).toLocalDate()
						: null;	
				CacLoaiGiay cacloaigiay =new CacLoaiGiay();
				Giayxacnhan giayxacnhan=new Giayxacnhan();
				giayxacnhan.setThoiGianXin(ThoiGianXin);
				cacloaigiay.setTenloaigiayString(TenGiay);
				giayxacnhan.setSL(Sl);
				giayxacnhan.setLyDo(LyDo);
				giayxacnhan.setThoiGianNhan(ThoiGianNhan);
				requests.add(new YeuCauXacNhan(cacloaigiay,giayxacnhan));
			}
		}catch (SQLException exception) {
	        exception.printStackTrace();
	    }

		return requests;
	}
	public List<CacLoaiGiay> getAllCacLoaiGiay_U()
	{
		List<CacLoaiGiay> requests= new ArrayList<>();
		try(Connection connection=JDBCUtils.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(SELECT_CacLoaiGiay_U))
		{
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				String MaLoaiGiay=rs.getString(1);
				String TenGiayXN=rs.getString(2);
				int TrangThai=rs.getInt(3);
				CacLoaiGiay cacLoaiGiay = new CacLoaiGiay();
				cacLoaiGiay.setMaloaigiayString(MaLoaiGiay);
				cacLoaiGiay.setTenloaigiayString(TenGiayXN);
				cacLoaiGiay.setTinhtrang(TrangThai);
				requests.add(cacLoaiGiay);
				System.out.println(cacLoaiGiay);
			}
		}
		catch (SQLException exception) {
	        exception.printStackTrace();
	    }
		return requests;
	}
	


	
	

	
	
	

	

	
	public List<thongbao> getAllThongbaos() {
		List<thongbao> listThongbaos = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ThongBao);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String TieuDe = resultSet.getString(2);
				String NoiDung = resultSet.getString(3);
				String NguoiGui = resultSet.getString(4);
				LocalDate thoiGian = resultSet.getDate(5).toLocalDate();
				String Nguoinhan = resultSet.getString(6);
				listThongbaos.add(new thongbao(id, TieuDe, NoiDung, NguoiGui, thoiGian, Nguoinhan));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listThongbaos;
	}

	/// insertThongbao
	public void insertTHONGBAO(String TieuDe, String NoiDung, String NguoiGui, LocalDate ThoiGian, String NguoiNhan)
			throws SQLException {

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERNT_THONGBAO)) {

			preparedStatement.setString(1, TieuDe);
			preparedStatement.setString(2, NoiDung);
			preparedStatement.setString(3, NguoiGui);

			java.sql.Date sqlDate = java.sql.Date.valueOf(ThoiGian);
			preparedStatement.setDate(4, sqlDate);

			preparedStatement.setString(5, NguoiNhan);

			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	public void insertGiayXN(int MaSV, String LyDo, String MaLoaiGiay, int SL)
			throws SQLException {

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GIAYXN)) {
			preparedStatement.setInt(1, MaSV);
			preparedStatement.setString(2, LyDo);
			preparedStatement.setString(3, MaLoaiGiay);
			preparedStatement.setInt(4, SL);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public Giayxacnhan getGiayxacnhan(String ID) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_searchIDMaYC)) {

			preparedStatement.setString(1, ID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					int id = resultSet.getInt("id");

					int MaSV = resultSet.getInt("MaSV");

					LocalDate ThoiGianXin = (resultSet.getDate("ThoiGianXin") != null)
							? resultSet.getDate("ThoiGianXin").toLocalDate()
							: null;

					LocalDate ThoiGianDuyet = (resultSet.getDate("ThoiGianDuyet") != null)
							? resultSet.getDate("ThoiGianDuyet").toLocalDate()
							: null;
					String lyDo = resultSet.getString("LyDo");
					int TrangThai = resultSet.getInt("TrangThai");
					String MaLoaiGiay = resultSet.getString("MaLoaiGiay");
					String MaCV = resultSet.getString("MaCV");
					int SL = resultSet.getInt("SL");
					LocalDate ThoiGianNhan = (resultSet.getDate("ThoiGianNhan") != null)
							? resultSet.getDate("ThoiGianNhan").toLocalDate()
							: null;

					System.out.println("ThoiGianNhan: " + ThoiGianNhan);
					return new Giayxacnhan(id, MaSV, ThoiGianXin, ThoiGianDuyet, lyDo, TrangThai, MaLoaiGiay, MaCV,
							ThoiGianNhan, SL);

				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public ThongTinSV getThongTinSV(String masv) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SV)) {

			preparedStatement.setString(1, masv);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int MaSV = resultSet.getInt(1);

					String HoTen = resultSet.getString(2);
					LocalDate NgaySinh = resultSet.getDate(3).toLocalDate();
					String NoiSinh = resultSet.getString(4);
					String GioiTinh = resultSet.getString(5);
					String DanToc = resultSet.getString(6);

					String SoCCCD = resultSet.getString(7);
					String SDT = resultSet.getString(8);
					String Email = resultSet.getString(9);

					String DiaChiSV = resultSet.getString(10);
					String KhoaHoc = resultSet.getString(11);
					String NienKhoa = resultSet.getString(12);

					String NamNhapHoc = resultSet.getString(13);
					String NamHetHanHoc = resultSet.getString(14);
					String CTDaoTao = resultSet.getString(15);

					String LopSV = resultSet.getString(16);
					int TinhTrang = resultSet.getInt(17);
					String AnhTheLink = resultSet.getString(18);

					return new ThongTinSV(MaSV, HoTen, NgaySinh, NoiSinh, GioiTinh, DanToc, SoCCCD, SDT, Email,
							DiaChiSV, KhoaHoc, NienKhoa, NamNhapHoc, NamHetHanHoc, CTDaoTao, LopSV, TinhTrang,
							AnhTheLink);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}
	
	
	public List<YeuCauXacNhan> searchRequestsByMaSV(String maSV) {
		List<YeuCauXacNhan> requests = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SEARCH_REQUESTS)) {

			preparedStatement.setString(1, maSV); // Set the MaSV parameter

			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String tenGiay = rs.getString(2);
				int nguoiGuii = rs.getInt(3);
				LocalDate thoiGianXin = rs.getDate(4).toLocalDate();
				String lyDo = rs.getString(5);
				int trangThai = rs.getInt(6);
				int soluong = rs.getInt(7);

				CacLoaiGiay cacLoaiGiay = new CacLoaiGiay();
				cacLoaiGiay.setTenloaigiayString(tenGiay);

				Giayxacnhan giayxacnhann = new Giayxacnhan();
				giayxacnhann.setId(id);
				giayxacnhann.setMaSV(nguoiGuii);
				giayxacnhann.setThoiGianXin(thoiGianXin);
				giayxacnhann.setLyDo(lyDo);
				giayxacnhann.setTrangThai(trangThai);
				giayxacnhann.setSL(soluong);

				requests.add(new YeuCauXacNhan(cacLoaiGiay, giayxacnhann));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return requests;
	}

	public void UpdateGIAYXNNHAN(int trangthai, String id) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GIAYXNNHAN)) {
			preparedStatement.setInt(1, trangthai);
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void UpdatePassword(String Username, String Password) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_Password)) {
			preparedStatement.setString(1, Password);
			preparedStatement.setString(2, Username);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void UpdateThongTinSV(String SDT, String Email, String DiaChiSV, int MaSV) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ThongTinSV)) {
			preparedStatement.setString(1, SDT);
			preparedStatement.setString(2, Email);
			preparedStatement.setString(3, DiaChiSV);
			preparedStatement.setInt(4, MaSV);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateAllGiayXN(String maCV, int newTrangThai) throws SQLException {
		try (Connection connection = JDBCUtils.getConnection(); Statement statement = connection.createStatement()) {

			// Disable safe updates
			statement.executeUpdate("SET SQL_SAFE_UPDATES = 0");

			// Update giayxacnhan
			String updateQuery = "UPDATE giayxacnhan " + "SET MaCV = '" + maCV + "', TrangThai = " + newTrangThai
					+ ", ThoiGianDuyet  = CURRENT_TIMESTAMP " + "WHERE TrangThai = 1";
			statement.executeUpdate(updateQuery);

			// Enable safe updates
			statement.executeUpdate("SET SQL_SAFE_UPDATES = 1");
		}
	}

	public void UpdateGiayXN(String MaCV, int trangthai, String id) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GIAYXN)) {
			preparedStatement.setString(1, MaCV);
			preparedStatement.setInt(2, trangthai);
			preparedStatement.setString(3, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<PhongCTSV> getAllCV() {
		List<PhongCTSV> CVList = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALLCV);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				String MaCV = resultSet.getString(1);
				String TenCV = resultSet.getString(2);
				String Email = resultSet.getString(3);
				String Sdt = resultSet.getString(4);
				String Diachi = resultSet.getString(5);
				LocalDate NgayBDLam = resultSet.getDate(6).toLocalDate();
				int TinhTrang = resultSet.getInt(7);
				int QuyenHan = resultSet.getInt(8);

				CVList.add(new PhongCTSV(MaCV, TenCV, Email, Sdt, Diachi, NgayBDLam, TinhTrang, QuyenHan));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return CVList;
	}

	public TaiKhoan login(String userString, String passString, String roleString) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN)) {

			preparedStatement.setString(1, userString);
			preparedStatement.setString(2, passString);
			preparedStatement.setString(3, roleString);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String user = resultSet.getString("Username");
					String pass = resultSet.getString("Password");
					String role = resultSet.getString("Role");
					int tinhTrang = resultSet.getInt("TinhTrang");

					// Now you have all the data, including TinhTrang
					return new TaiKhoan(user, pass, role, tinhTrang);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

