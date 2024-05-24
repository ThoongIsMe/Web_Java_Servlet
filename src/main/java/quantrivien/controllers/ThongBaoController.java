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
import quantrivien.dao.ThongBaoDao;
import quantrivien.models.ThongBao;

@WebServlet("/thongbao")
public class ThongBaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ThongBaoDao thongBaoDao;

	public void init() {
		thongBaoDao = new ThongBaoDao ();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
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
				listThongBao(request, response);
			} else {
				switch (action) {
				case "taothongbao":
					openTaoThongBao(request, response);
				case "guithongbao":
					guiThongBao(request, response);
				default:
					listThongBao(request, response);
					break;
				}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listThongBao(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		List<ThongBao> listTB = thongBaoDao.getAllThongbaos(request, response);
		session.setAttribute("thongbaoCTSV", listTB);
		
		request.getRequestDispatcher("quantrivien/pages/thongbao.jsp").forward(request, response);
	}
	
	private void openTaoThongBao(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/taothongbao.jsp");
		dispatcher.forward(request, response);
	}

	private void guiThongBao(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String tieude = request.getParameter("tieude");
		String noidung = request.getParameter("noidung");
		String nguoinhan = request.getParameter("nguoinhan");
		System.out.print(nguoinhan);
		try {
			if (tieude != null && noidung != null && nguoinhan != null) {
				if ("sinhvien".equals(nguoinhan)) {
					thongBaoDao.GuiThongBaoSinhVien(request, response, tieude, noidung);
				} else if ("chuyenvien".equals(nguoinhan)) {
					thongBaoDao.GuiThongBaoChuyenVien(request, response, tieude, noidung);
				} else if ("tatca".equals(nguoinhan)) {
					thongBaoDao.GuiThongBaoSinhVien(request, response, tieude, noidung);
					thongBaoDao.GuiThongBaoChuyenVien(request, response, tieude, noidung);
				}	
			}

			response.setContentType("text/html; charset=UTF-8");
			response.getWriter()
					.println("<script>alert('Đã gửi thông báo!!!'); window.location.href='thongbao';</script>");
			response.setContentType("text/html; charset=UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
