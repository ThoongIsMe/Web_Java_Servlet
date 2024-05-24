package quantrivien.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.models.TaiKhoan;
import quantrivien.dao.DichVuDao;
import quantrivien.models.DichVu;

@WebServlet("/quanlydichvu")
public class DichVuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DichVuDao dichVuDao;

	public void init() {
		dichVuDao = new DichVuDao();
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
				listDichVu(request, response);
			} else {
				switch (action) {
				case "capnhatthongtindichvu":
					openCapNhatThongTinDichVu(request, response);
					break;
				case "themthongtindichvu":
					openThemThongTinDichVu(request, response);
					break;
				case "xoathongtindichvu":
					xoaThongTinDichVu(request, response);
					break;
				case "themdv":
					themThongTinDichVu(request, response);
					break;
				case "capnhatdv":
					capNhatThongTinDichVu(request, response);
					break;
				}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listDichVu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<DichVu> listDV = dichVuDao.selectAllDichVu();
		request.setAttribute("listDichVu", listDV);
		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/quanlydichvu.jsp");
		dispatcher.forward(request, response);
	}

	private void openCapNhatThongTinDichVu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maDV = request.getParameter("maDV");
		DichVu existingDichVu = dichVuDao.selectDichVuTheoMaDV(maDV);
		request.setAttribute("dichvu", existingDichVu);

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/thaydoidichvu.jsp");
		dispatcher.forward(request, response);
	}

	private void openThemThongTinDichVu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setAttribute("maDV", dichVuDao.MaDVNext());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/thaydoidichvu.jsp");
		dispatcher.forward(request, response);
	}

	private void themThongTinDichVu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maDV = request.getParameter("madv");
		String tenDV = request.getParameter("tendv");
		String tinhTrang = request.getParameter("tinhtrang");

		DichVu dv = new DichVu(maDV, tenDV, tinhTrang);
		try {
			int result = dichVuDao.themDichVu(dv);
			if (result == 1) {
				response.getWriter().println(
						"<script>alert('Thêm dịch vụ thành công'); window.location.href='quanlydichvu';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void capNhatThongTinDichVu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maDV = request.getParameter("madv");
		String tenDV = request.getParameter("tendv");
		int tinhTrang = Integer.parseInt(request.getParameter("tinhtrang"));

		DichVu dv = new DichVu(maDV, tenDV, tinhTrang);
		try {
			int result = dichVuDao.capNhatDichVu(dv);
			if (result == 1) {
				response.getWriter().println(
						"<script>alert('Cập nhật dịch vụ thành công'); window.location.href='quanlydichvu';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void xoaThongTinDichVu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String maDV = request.getParameter("maDV");
		try {
			dichVuDao.xoaDichVu(maDV);
			response.getWriter()
					.println("<script>alert('Xóa dịch vụ thành công!'); window.location.href='quanlydichvu';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
