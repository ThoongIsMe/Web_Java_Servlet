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
import quantrivien.dao.TinhTrangDao;
import quantrivien.dao.ChuyenVienDao;
import quantrivien.models.ChuyenVien;

@WebServlet("/quanlychuyenvien")
public class ChuyenVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChuyenVienDao chuyenVienDao;
	private TinhTrangDao tinhTrangDao;

	public void init() {
		chuyenVienDao = new ChuyenVienDao();
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
				listChuyenVien(request, response);
			} else {
				switch (action) {
				case "capnhatthongtinchuyenvien":
					openCapNhatThongTinChuyenVien(request, response);
					break;
				case "themthongtinchuyenvien":
					openThemThongTinChuyenVien(request, response);
					break;
				case "xoathongtinchuyenvien":
					xoaThongTinChuyenVien(request, response);
					break;
				case "themcv":
					themThongTinChuyenVien(request, response);
					break;
				case "capnhatcv":
					capNhatThongTinChuyenVien(request, response);
					break;
				case "timcv":
					timChuyenVien(request, response);
					break;
				default:
					listChuyenVien(request, response);
					break;
				}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listChuyenVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ChuyenVien> listCV = chuyenVienDao.selectAllChuyenVien();
		request.setAttribute("listChuyenVien", listCV);
		request.setAttribute("tongsochuyenvien", tinhTrangDao.countChuyenVien());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/quanlychuyenvien.jsp");
		dispatcher.forward(request, response);
	}

	private void openCapNhatThongTinChuyenVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maCV = request.getParameter("maCV");
		ChuyenVien existingChuyenVien = chuyenVienDao.selectChuyenVienTheoMaCV(maCV);
		request.setAttribute("chuyenvien", existingChuyenVien);

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/thaydoichuyenvien.jsp");
		dispatcher.forward(request, response);
	}

	private void openThemThongTinChuyenVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setAttribute("maCV", chuyenVienDao.MaCVNext());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/thaydoichuyenvien.jsp");
		dispatcher.forward(request, response);
	}

	private void themThongTinChuyenVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maCV = request.getParameter("macv");
		String tenCV = request.getParameter("hoten");
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");
		String diaChi = request.getParameter("diachi");
		String ngayCongTac = request.getParameter("ngaycongtac");
		String tinhTrang = request.getParameter("tinhtrang");
		String quyenHan = request.getParameter("quyenhan");
		LocalDate ngayCongTacDate = LocalDate.parse(ngayCongTac, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		ChuyenVien cv = new ChuyenVien(maCV, tenCV, email, sdt, diaChi, ngayCongTacDate, tinhTrang, quyenHan);
		try {
			int result = chuyenVienDao.themChuyenVien(cv);
			if (result == 1) {
				response.getWriter().println(
						"<script>alert('Thêm chuyên viên thành công!'); window.location.href='quanlychuyenvien';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void capNhatThongTinChuyenVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maCV = request.getParameter("macv");
		String tenCV = request.getParameter("hoten");
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");
		String diaChi = request.getParameter("diachi");
		String ngayCongTac = request.getParameter("ngaycongtac");
		LocalDate ngayCongTacDate = LocalDate.parse(ngayCongTac, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String tinhTrang = request.getParameter("tinhtrang");
		String quyenHan = request.getParameter("quyenhan");

		ChuyenVien cv = new ChuyenVien(maCV, tenCV, email, sdt, diaChi, ngayCongTacDate, tinhTrang, quyenHan);
		try {
			int result = chuyenVienDao.capNhatChuyenVien(cv);
			if (result == 1) {
				response.getWriter().println(
						"<script>alert('Cập nhật chuyên viên thành công!'); window.location.href='quanlychuyenvien';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void xoaThongTinChuyenVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maCV = request.getParameter("maCV");
		try {
			chuyenVienDao.xoaChuyenVien(maCV);
			response.getWriter().println(
					"<script>alert('Xóa chuyên viên thành công!'); window.location.href='quanlychuyenvien';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void timChuyenVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ChuyenVien> listCV = chuyenVienDao.timChuyenVien(request.getParameter("cvsearch"));
		request.setAttribute("listChuyenVien", listCV);
		request.setAttribute("tongsochuyenvien", tinhTrangDao.countChuyenVien());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/quanlychuyenvien.jsp");
		dispatcher.forward(request, response);
	}
}
