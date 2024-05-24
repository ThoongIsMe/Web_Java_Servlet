package quantrivien.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.models.TaiKhoan;
import quantrivien.dao.SinhVienDao;
import quantrivien.dao.TinhTrangDao;
import quantrivien.models.SinhVien;

@WebServlet("/quanlysinhvien")
public class SinhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SinhVienDao sinhVienDao;
	private TinhTrangDao tinhTrangDao;

	public void init() {
		sinhVienDao = new SinhVienDao();
		tinhTrangDao = new TinhTrangDao();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		String action = request.getParameter("action");

		try {
			if (action == null) {
				listSinhVien(request, response);
			} else {
				switch (action) {
//				case "login":
//					authenticate(request, response);
//					break;
				case "capnhatthongtinsinhvien":
					openCapNhatThongTinSinhVien(request, response);
					break;
				case "themthongtinsinhvien":
					openThemThongTinSinhVien(request, response);
					break;
				case "xoathongtinsinhvien":
					xoaThongTinSinhVien(request, response);
					break;
				case "themsv":
					themThongTinSinhVien(request, response);
					break;
				case "capnhatsv":
					capNhatThongTinSinhVien(request, response);
					break;
				case "timsv":
					timSinhVien(request, response);
					break;
				default:
					listSinhVien(request, response);
					break;
				}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

//	private void authenticate(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String role = request.getParameter("role");
//
//		Login login = new Login();
//		login.setUsername(username);
//		login.setPassword(password);
//		if ("quantrivien".equals(role)) {
//			login.setRole("3");
//		}
//
//		try {
//			if (loginDao.validate(login)) {
//				HttpSession session = request.getSession();
//				session.setAttribute("name_label", username);
//				listSinhVien(request, response);
//				tinhTrangDao.setLuotTruyCap(request,response);
//			} else {
//				request.setAttribute("NOTIFICATION_LOGIN", "Sai thÃ´ng tin Ä‘Äƒng nháº­p !");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/dangnhap.jsp");
//				dispatcher.forward(request, response);
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	private void listSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<SinhVien> listSV = sinhVienDao.selectAllSinhVien();
		request.setAttribute("listSinhVien", listSV);
		request.setAttribute("tongsosinhvien", tinhTrangDao.countSinhVien());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/quanlysinhvien.jsp");	
		dispatcher.forward(request, response);
	}

	private void openCapNhatThongTinSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maSV = request.getParameter("maSV");
		SinhVien existingSinhVien = sinhVienDao.selectSinhVienTheoMaSV(maSV);
		request.setAttribute("sinhvien", existingSinhVien);

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/thaydoisinhvien.jsp");
		dispatcher.forward(request, response);
	}

	private void openThemThongTinSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setAttribute("maSV", sinhVienDao.MaSVNext());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/thaydoisinhvien.jsp");
		dispatcher.forward(request, response);
	}

	private void themThongTinSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maSV = request.getParameter("masv");
		String tenSV = request.getParameter("hoten");
		String ngaySinh = request.getParameter("ngaysinh");
		LocalDate ngaySinhDate = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String noiSinh = request.getParameter("noisinh");
		String gioiTinh = request.getParameter("gioitinh");
		String danToc = request.getParameter("dantoc");
		String cccd = request.getParameter("cccd");
		String sdt = request.getParameter("sdt");
		String email = request.getParameter("email");
		String diaChi = request.getParameter("diachi");
		String khoa = request.getParameter("khoa");
		String nienKhoa = request.getParameter("nienkhoa");
		int namNhapHoc = Integer.parseInt(request.getParameter("namnhaphoc"));
		int namKetThuc = Integer.parseInt(request.getParameter("namketthuc"));
		String ctDaoTao = request.getParameter("ctdaotao");
		String lop = request.getParameter("lop");
		String tinhTrang = request.getParameter("tinhtrang");
		String anhThe = request.getParameter("anhthe");

		SinhVien sv = new SinhVien(maSV, tenSV, ngaySinhDate, noiSinh, gioiTinh, danToc, cccd, sdt, email, diaChi, khoa,
				nienKhoa, namNhapHoc, namKetThuc, ctDaoTao, lop, tinhTrang, anhThe);
		try {
			int result = sinhVienDao.themSinhVien(sv);
			if (result == 1) {
				response.getWriter().println(
						"<script>alert('Thêm sinh viên thành công!'); window.location.href='quanlysinhvien';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void capNhatThongTinSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maSV = request.getParameter("masv");
		String tenSV = request.getParameter("hoten");
		String ngaySinh = request.getParameter("ngaysinh");
		LocalDate ngaySinhDate = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String noiSinh = request.getParameter("noisinh");
		String gioiTinh = request.getParameter("gioitinh");
		String danToc = request.getParameter("dantoc");
		String cccd = request.getParameter("cccd");
		String sdt = request.getParameter("sdt");
		String email = request.getParameter("email");
		String diaChi = request.getParameter("diachi");
		String khoa = request.getParameter("khoa");
		String nienKhoa = request.getParameter("nienkhoa");
		int namNhapHoc = Integer.parseInt(request.getParameter("namnhaphoc"));
		int namKetThuc = Integer.parseInt(request.getParameter("namketthuc"));
		String ctDaoTao = request.getParameter("ctdaotao");
		String lop = request.getParameter("lop");
		String tinhTrang = request.getParameter("tinhtrang");
		String anhThe = request.getParameter("anhthe");

		SinhVien sv = new SinhVien(maSV, tenSV, ngaySinhDate, noiSinh, gioiTinh, danToc, cccd, sdt, email, diaChi, khoa,
				nienKhoa, namNhapHoc, namKetThuc, ctDaoTao, lop, tinhTrang, anhThe);

		try {
			int result = sinhVienDao.capNhatSinhVien(sv);
			if (result == 1) {
				response.getWriter().println(
						"<script>alert('Cập nhật sinh viên thành công!'); window.location.href='quanlysinhvien';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void xoaThongTinSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maSV = request.getParameter("maSV");
		try {		
			sinhVienDao.xoaSinhVien(maSV);
			response.getWriter().println(
					"<script>alert('Xóa sinh viên thành công!'); window.location.href='quanlysinhvien';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void timSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<SinhVien> listSV = sinhVienDao.timSinhVien(request.getParameter("svsearch"));
		request.setAttribute("listSinhVien", listSV);
		request.setAttribute("tongsosinhvien", tinhTrangDao.countSinhVien());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/quanlysinhvien.jsp");
		dispatcher.forward(request, response);
	}
}
