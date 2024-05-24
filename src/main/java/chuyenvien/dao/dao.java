package chuyenvien.dao;

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

	private static final String SELECT_ALL_REQUESTS = "SELECT gx.id, clg.TenGiayXN AS TenGiay, gx.MaSV AS NguoiGui, gx.ThoiGianXin, gx.LyDo, gx.TrangThai, gx.SL\r\n"
			+ "FROM giayxacnhan gx\r\n" + "JOIN cacloaigiay clg ON gx.MaLoaiGiay = clg.MaLoaiGiay;";

	private static final String SELECT_IDCTSV = "SELECT * FROM phongctsv WHERE MaCV = ?;";

	private static final String SELECT_ALLSV = "SELECT * FROM phongctsv;";

	private static final String UPDATE_GIAYXN = "UPDATE giayxacnhan\r\n"
			+ "SET MaCV = ? , TrangThai = ?, ThoiGianDuyet = CURRENT_TIMESTAMP\r\n" + "WHERE id = ?;";
	private static final String UPDATE_GIAYXNNHAN = "UPDATE giayxacnhan\r\n"
			+ "SET TrangThai = ?, ThoiGianNhan =  DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 3 DAY)\r\n" + "WHERE id = ?;\r\n"
			+ "";

	private static final String SELECT_SEARCH_REQUESTS = "SELECT gx.id, clg.TenGiayXN AS TenGiay, gx.MaSV AS NguoiGui, gx.ThoiGianXin, gx.LyDo, gx.TrangThai, gx.SL\r\n"
			+ "FROM giayxacnhan gx\r\n" + "JOIN cacloaigiay clg ON gx.MaLoaiGiay = clg.MaLoaiGiay\r\n"
			+ "WHERE gx.MaSV = ?;\r\n";

	private static final String SELECT_EmailSV = "select *from thongtinsv where masv = ?;";

	private static final String SELECT_searchIDMaYC = "select *from giayxacnhan where id = ?;";

	private static final String INSERNT_THONGBAO = "INSERT INTO thongbao (TieuDe, NoiDung, NguoiGui, ThoiGian, Nguoinhan)\r\n"
			+ "VALUES (?, ?, ?, ?, ?);";

	private static final String SELECT_ThongBao = "SELECT * FROM thongbao;";

	private static final String SELECT_COUNTTHONGKE = "SELECT COUNT(*) AS count FROM giayxacnhan WHERE trangthai = ?";

	private static final String SELECT_ALL_THONGKE = "SELECT\r\n" + "    giayxacnhan.id,\r\n"
			+ "    giayxacnhan.MaSV,\r\n" + "    giayxacnhan.ThoiGianXin,\r\n" + "    giayxacnhan.ThoiGianDuyet,\r\n"
			+ "    giayxacnhan.LyDo,\r\n" + "    giayxacnhan.TrangThai,\r\n" + "    giayxacnhan.MaCV,\r\n"
			+ "    giayxacnhan.SL,\r\n" + "    giayxacnhan.ThoiGianNhan,\r\n" + "    thongtinsv.HoTen,\r\n"
			+ "    thongtinsv.KhoaHoc,\r\n" + "    thongtinsv.NamNhapHoc,\r\n" + "    thongtinsv.TinhTrang,\r\n"
			+ "    cacloaigiay.TenGiayXN  -- Include the column for TenGiayXN from cacloaigiay\r\n" + "FROM\r\n"
			+ "    giayxacnhan\r\n" + "INNER JOIN\r\n" + "    thongtinsv ON giayxacnhan.MaSV = thongtinsv.MaSV\r\n"
			+ "INNER JOIN\r\n" + "    cacloaigiay ON giayxacnhan.MaLoaiGiay = cacloaigiay.MaLoaiGiay;";

	private static final String SELECT_DISTINCT_NAM_NHAP_HOC = "SELECT DISTINCT NamNhapHoc FROM thongtinsv ORDER BY NamNhapHoc ASC";

	private static final String SELECT_KHOAHOC = "SELECT DISTINCT KhoaHoc\r\n" + "FROM thongtinsv";
	private static final String SELECT_NAMHOC = "SELECT DISTINCT\r\n"
			+ "    CONCAT(YEAR(ThoiGianXin)) AS ThoiGianXinRange\r\n" + "FROM giayxacnhan\r\n"
			+ "ORDER BY ThoiGianXinRange ASC;";

	private static final String SELECT_SEARCHTRANGTHAI = "SELECT\r\n" + "    giayxacnhan.id,\r\n"
			+ "    giayxacnhan.MaSV,\r\n" + "    giayxacnhan.ThoiGianXin,\r\n" + "    giayxacnhan.ThoiGianDuyet,\r\n"
			+ "    giayxacnhan.LyDo,\r\n" + "    giayxacnhan.TrangThai,\r\n" + "    giayxacnhan.MaCV,\r\n"
			+ "    giayxacnhan.SL,\r\n" + "    giayxacnhan.ThoiGianNhan,\r\n" + "    thongtinsv.HoTen,\r\n"
			+ "    thongtinsv.KhoaHoc,\r\n" + "    thongtinsv.NamNhapHoc,\r\n" + "    thongtinsv.TinhTrang,\r\n"
			+ "    cacloaigiay.TenGiayXN  -- Include the column for TenGiayXN from cacloaigiay\r\n" + "FROM\r\n"
			+ "    giayxacnhan\r\n" + "INNER JOIN\r\n" + "    thongtinsv ON giayxacnhan.MaSV = thongtinsv.MaSV\r\n"
			+ "INNER JOIN\r\n" + "    cacloaigiay ON giayxacnhan.MaLoaiGiay = cacloaigiay.MaLoaiGiay\r\n"
			+ "where trangthai =?;";

	private static final String SELECT_SearchThongKe = "SELECT\r\n" + "    giayxacnhan.id,\r\n"
			+ "    giayxacnhan.MaSV,\r\n" + "    giayxacnhan.ThoiGianXin,\r\n" + "    giayxacnhan.ThoiGianDuyet,\r\n"
			+ "    giayxacnhan.LyDo,\r\n" + "    giayxacnhan.TrangThai,\r\n" + "    giayxacnhan.MaCV,\r\n"
			+ "    giayxacnhan.SL,\r\n" + "    giayxacnhan.ThoiGianNhan,\r\n" + "    thongtinsv.HoTen,\r\n"
			+ "    thongtinsv.KhoaHoc,\r\n" + "    thongtinsv.NamNhapHoc,\r\n" + "    thongtinsv.TinhTrang,\r\n"
			+ "    cacloaigiay.TenGiayXN  -- Include the column for TenGiayXN from cacloaigiay\r\n" + "FROM\r\n"
			+ "    giayxacnhan\r\n" + "INNER JOIN\r\n" + "    thongtinsv ON giayxacnhan.MaSV = thongtinsv.MaSV\r\n"
			+ "INNER JOIN\r\n" + "    cacloaigiay ON giayxacnhan.MaLoaiGiay = cacloaigiay.MaLoaiGiay\r\n"
			+ "where thongtinsv.KhoaHoc = ? and thongtinsv.NamNhapHoc = ? and  YEAR(giayxacnhan.ThoiGianXin) = ?;";

	private static final String SELECT_DICHVUSV = "SELECT * FROM cacloaigiay;";

	private static final String INSERT_DICHVUSV = "INSERT INTO cacloaigiay (MaLoaiGiay, TenGiayXN, TinhTrang) VALUES(?, ?, ?);";

	private static final String DELETE_DICHVUSV = "update cacloaigiay\r\n" + "set tinhtrang = 0\r\n"
			+ "where maloaigiay = ?;";

	private static final String UPDATE_DICHVUSV = "update cacloaigiay\r\n" + "set tenGiayXN = ?,\r\n"
			+ "tinhtrang = ?\r\n" + "where maloaigiay = ?;";

	private static final String SEARCH_DICHVUSV_BY_ID = "select * from cacloaigiay where maloaigiay = ?;";

	private static final String COUNT_GIAYXACNHAN_BY_MALOAIGIAY = "SELECT COUNT(*) FROM giayxacnhan WHERE MaLoaiGiay = ?";

	private static final String UPDATE_ThongTinCTV = "UPDATE phongctsv SET SoDienthoai = ? , Email = ?, DiaChi = ? WHERE MaCV = ?";

	private static final String UPDATE_PasswordCTV = "UPDATE taikhoan SET Password = ? WHERE Username = ?";

///////
	private static final String SEARCH_PASS = "SELECT tk.Username, ts.Email, tk.Password\r\n" + "FROM taikhoan tk\r\n"
			+ "JOIN thongtinsv ts ON tk.Username = ts.MaSV\r\n"
			+ "WHERE ts.Email = ? AND tk.Role = '1' AND tk.TinhTrang = '1'\r\n" + "LIMIT 0, 1000;";

////dao check truycap;

	private static final String CHECK_ACCESS = "INSERT INTO timelogin (ip_address, timestamp) VALUES (?, CURRENT_TIMESTAMP)";

	public void insertAccess(String ip) throws SQLException {

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCESS)) {
			preparedStatement.setString(1, ip);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	private static final String NEXT_MADV = "select NextMaDV()";

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

	//////

	public ThongTinSV getTimMatKhauSv(String email) {
		ThongTinSV thongtSv = null;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PASS)) {
			preparedStatement.setString(1, email);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {

					String username = resultSet.getString(1);
					String emailSV = resultSet.getString(2);
					String password = resultSet.getString(3);

					System.out.print("username" + username + password);

					TaiKhoan taiKhoan = new TaiKhoan();
					taiKhoan.setUsernameString(username);
					taiKhoan.setPasString(password);

					thongtSv = new ThongTinSV(emailSV, taiKhoan);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return thongtSv;
	}

	////
	public void UpdatePasswordCTV(String Username, String Password) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PasswordCTV)) {
			preparedStatement.setString(1, Password);
			preparedStatement.setString(2, Username);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdateThongTinCTV(String SDT, String Email, String DiaChi, String MaCV) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ThongTinCTV)) {
			preparedStatement.setString(1, SDT);
			preparedStatement.setString(2, Email);
			preparedStatement.setString(3, DiaChi);
			preparedStatement.setString(4, MaCV);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdateDichVuSV(String tenGiay, String tinhtrang, String maloaigiay) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DICHVUSV)) {

			preparedStatement.setString(1, tenGiay);
			preparedStatement.setString(2, tinhtrang);
			preparedStatement.setString(3, maloaigiay);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int countGiayXacNhanByMaLoaiGiay(String maLoaiGiay) {
		int count = 0;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_GIAYXACNHAN_BY_MALOAIGIAY)) {
			preparedStatement.setString(1, maLoaiGiay);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public CacLoaiGiay getDichVuSVById(String madv) {
		CacLoaiGiay dichVuSV = null;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_DICHVUSV_BY_ID)) {
			preparedStatement.setString(1, madv);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					String maloaigiay = resultSet.getString("maloaigiay");
					String tenGiayXN = resultSet.getString("tenGiayXN");
					int tinhtrang = resultSet.getInt("tinhtrang");

					dichVuSV = new CacLoaiGiay(maloaigiay, tenGiayXN, tinhtrang);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dichVuSV;
	}

	public void deleteDichVuSV(String maloaigiay) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DICHVUSV)) {
			preparedStatement.setString(1, maloaigiay);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertDICHVUSV(String MaLoaiGiay, String TenGiayXN, String TinhTrang) throws SQLException {

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DICHVUSV)) {
			preparedStatement.setString(1, MaLoaiGiay);
			preparedStatement.setString(2, TenGiayXN);
			preparedStatement.setString(3, TinhTrang);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public List<CacLoaiGiay> getAllDichVu() {
		List<CacLoaiGiay> listCacLoaiGiay = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DICHVUSV);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String MaDV = resultSet.getString(1);
				String TenDV = resultSet.getString(2);
				int TinhTrang = resultSet.getInt(3);

				listCacLoaiGiay.add(new CacLoaiGiay(MaDV, TenDV, TinhTrang));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listCacLoaiGiay;
	}

	public List<YeuCauXacNhan> searchByThongKe(String Khoahoc, String NhamNhapHoc, String ThoiGianXin) {
		List<YeuCauXacNhan> requests = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SearchThongKe)) {

			preparedStatement.setString(1, Khoahoc);
			preparedStatement.setString(2, NhamNhapHoc);

			preparedStatement.setString(3, ThoiGianXin);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				int MaSV = rs.getInt(2);
				LocalDate thoiGianXin = rs.getDate(3).toLocalDate();

				LocalDate ThoiGianDuyet = (rs.getDate("ThoiGianDuyet") != null)
						? rs.getDate("ThoiGianDuyet").toLocalDate()
						: null;

				String lyDo = rs.getString(5);
				int trangThai = rs.getInt(6);
				String MaCV = rs.getString(7);

				int SL = rs.getInt(8);

				LocalDate ThoiGianNhan = (rs.getDate("ThoiGianNhan") != null) ? rs.getDate("ThoiGianNhan").toLocalDate()
						: null;
				String HoTen = rs.getString(10);
				String KhoaHoc = rs.getString(11);
				String NamNhaphoc = rs.getString(12);
				int tinhtrang = rs.getInt(13);
				String TenGiay = rs.getString(14);

				CacLoaiGiay cacLoaiGiay = new CacLoaiGiay();
				cacLoaiGiay.setTenloaigiayString(TenGiay);

				Giayxacnhan giayxacnhann = new Giayxacnhan();
				giayxacnhann.setId(id);
				giayxacnhann.setMaSV(MaSV);
				giayxacnhann.setThoiGianXin(thoiGianXin);
				giayxacnhann.setThoiGianDuyet(ThoiGianDuyet);
				giayxacnhann.setLyDo(lyDo);
				giayxacnhann.setTrangThai(trangThai);
				giayxacnhann.setMaCV(MaCV);
				giayxacnhann.setSL(SL);
				giayxacnhann.setThoiGianNhan(ThoiGianNhan);

				ThongTinSV thongTinSV = new ThongTinSV();
				thongTinSV.setHoteString(HoTen);
				thongTinSV.setKhoaHoc(KhoaHoc);
				thongTinSV.setNamNhapHoc(NamNhaphoc);
				thongTinSV.setTinhTrang(tinhtrang);

				requests.add(new YeuCauXacNhan(cacLoaiGiay, giayxacnhann, thongTinSV));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return requests;
	}

	public List<YeuCauXacNhan> searchByTrangThai(int TRANGTHAI) {
		List<YeuCauXacNhan> requests = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SEARCHTRANGTHAI)) {

			preparedStatement.setInt(1, TRANGTHAI);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				int MaSV = rs.getInt(2);
				LocalDate thoiGianXin = rs.getDate(3).toLocalDate();

				LocalDate ThoiGianDuyet = (rs.getDate("ThoiGianDuyet") != null)
						? rs.getDate("ThoiGianDuyet").toLocalDate()
						: null;

				String lyDo = rs.getString(5);
				int trangThai = rs.getInt(6);
				String MaCV = rs.getString(7);

				int SL = rs.getInt(8);

				LocalDate ThoiGianNhan = (rs.getDate("ThoiGianNhan") != null) ? rs.getDate("ThoiGianNhan").toLocalDate()
						: null;
				String HoTen = rs.getString(10);
				String KhoaHoc = rs.getString(11);
				String NamNhaphoc = rs.getString(12);
				int tinhtrang = rs.getInt(13);
				String TenGiay = rs.getString(14);

				CacLoaiGiay cacLoaiGiay = new CacLoaiGiay();
				cacLoaiGiay.setTenloaigiayString(TenGiay);

				Giayxacnhan giayxacnhann = new Giayxacnhan();
				giayxacnhann.setId(id);
				giayxacnhann.setMaSV(MaSV);
				giayxacnhann.setThoiGianXin(thoiGianXin);
				giayxacnhann.setThoiGianDuyet(ThoiGianDuyet);
				giayxacnhann.setLyDo(lyDo);
				giayxacnhann.setTrangThai(trangThai);
				giayxacnhann.setMaCV(MaCV);
				giayxacnhann.setSL(SL);
				giayxacnhann.setThoiGianNhan(ThoiGianNhan);

				ThongTinSV thongTinSV = new ThongTinSV();
				thongTinSV.setHoteString(HoTen);
				thongTinSV.setKhoaHoc(KhoaHoc);
				thongTinSV.setNamNhapHoc(NamNhaphoc);
				thongTinSV.setTinhTrang(tinhtrang);

				requests.add(new YeuCauXacNhan(cacLoaiGiay, giayxacnhann, thongTinSV));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return requests;
	}

	// lay ra nam hco
	public List<String> getNamHoc() {
		List<String> NamHocList = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAMHOC);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				NamHocList.add(resultSet.getString("ThoiGianXinRange"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return NamHocList;
	}

	// lay ra khoa hoc
	public List<String> getKhoaHoc() {
		List<String> khoahocList = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KHOAHOC);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				khoahocList.add(resultSet.getString("KhoaHoc"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return khoahocList;
	}

	// lay ra nam nhap hoc
	public List<String> getDistinctNamNhapHocSorted() {
		List<String> distinctNamNhapHocList = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTINCT_NAM_NHAP_HOC);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				distinctNamNhapHocList.add(resultSet.getString("NamNhapHoc"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return distinctNamNhapHocList;
	}

	// list in thongke

	public List<YeuCauXacNhan> selectAllThongKe() {
		List<YeuCauXacNhan> requests = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_THONGKE)) {

			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				int MaSV = rs.getInt(2);
				LocalDate thoiGianXin = rs.getDate(3).toLocalDate();

				LocalDate ThoiGianDuyet = (rs.getDate("ThoiGianDuyet") != null)
						? rs.getDate("ThoiGianDuyet").toLocalDate()
						: null;

				String lyDo = rs.getString(5);
				int trangThai = rs.getInt(6);
				String MaCV = rs.getString(7);

				int SL = rs.getInt(8);

				LocalDate ThoiGianNhan = (rs.getDate("ThoiGianNhan") != null) ? rs.getDate("ThoiGianNhan").toLocalDate()
						: null;
				String HoTen = rs.getString(10);
				String KhoaHoc = rs.getString(11);
				String NamNhaphoc = rs.getString(12);
				int tinhtrang = rs.getInt(13);
				String TenGiay = rs.getString(14);

				CacLoaiGiay cacLoaiGiay = new CacLoaiGiay();
				cacLoaiGiay.setTenloaigiayString(TenGiay);

				Giayxacnhan giayxacnhann = new Giayxacnhan();
				giayxacnhann.setId(id);
				giayxacnhann.setMaSV(MaSV);
				giayxacnhann.setThoiGianXin(thoiGianXin);
				giayxacnhann.setThoiGianDuyet(ThoiGianDuyet);
				giayxacnhann.setLyDo(lyDo);
				giayxacnhann.setTrangThai(trangThai);
				giayxacnhann.setMaCV(MaCV);
				giayxacnhann.setSL(SL);
				giayxacnhann.setThoiGianNhan(ThoiGianNhan);

				ThongTinSV thongTinSV = new ThongTinSV();
				thongTinSV.setHoteString(HoTen);
				thongTinSV.setKhoaHoc(KhoaHoc);
				thongTinSV.setNamNhapHoc(NamNhaphoc);
				thongTinSV.setTinhTrang(tinhtrang);

				requests.add(new YeuCauXacNhan(cacLoaiGiay, giayxacnhann, thongTinSV));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return requests;
	}

	// thongke
	public int getCOUNTTHONGKE(int trangthai) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTTHONGKE)) {

			preparedStatement.setInt(1, trangthai);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt("count");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 0;
	}

	///

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

	public ThongTinSV getMailThongTinSV(String masv) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EmailSV)) {

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
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALLSV);
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

	// seach ID PhongCTCV
	public PhongCTSV getPhongCTCV(String id) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IDCTSV)) {

			preparedStatement.setString(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String MaCV = resultSet.getString(1);
					String TenCV = resultSet.getString(2);
					String Email = resultSet.getString(3);
					String Sdt = resultSet.getString(4);
					String Diachi = resultSet.getString(5);
					LocalDate NgayBDLam = resultSet.getDate(6).toLocalDate();
					int TinhTrang = resultSet.getInt(7);
					int QuyenHan = resultSet.getInt(8);
					return new PhongCTSV(MaCV, TenCV, Email, Sdt, Diachi, NgayBDLam, TinhTrang, QuyenHan);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	// select requests
	public List<YeuCauXacNhan> selectAllRequests() {
		List<YeuCauXacNhan> requests = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS)) {

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

	// Login
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

					return new TaiKhoan(user, pass, role, tinhTrang);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
