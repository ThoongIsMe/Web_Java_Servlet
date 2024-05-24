package quantrivien.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.models.TaiKhoan;
import quantrivien.dao.YeuCauDao;
import quantrivien.models.YeuCau;

@WebServlet("/quanlyyeucau")
public class YeuCauController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private YeuCauDao yeuCauDao;
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		yeuCauDao = new YeuCauDao();
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
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
				listYeuCau(request, response);
			} else {
				switch (action) {
				case "duyetyc":
					duyetYeuCau(request, response);
					break;
				case "duyetallyc":
					duyetAllYeuCau(request, response);
					break;
				case "tuchoiyc":
					tuChoiYeuCau(request, response);
					break;
				default:
					listYeuCau(request, response);
					break;
				}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listYeuCau(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<YeuCau> listYC = yeuCauDao.selectAllYeuCau();
		request.setAttribute("listYeuCau", listYC);

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/quanlyyeucau.jsp");
		dispatcher.forward(request, response);
	}

	private void tuChoiYeuCau(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			yeuCauDao.tuChoiYeuCau(id);
			response.getWriter()
					.println("<script>alert('Đã từ chối yêu cầu'); window.location.href='quanlyyeucau';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void duyetAllYeuCau(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		try {
			yeuCauDao.duyetAllYeuCau(request, response, host, port, user, pass);
			response.getWriter()
					.println("<script>alert('Đã duyệt tất cả yêu cầu'); window.location.href='quanlyyeucau';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void duyetYeuCau(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			yeuCauDao.duyetYeuCau(id, request, response, host, port, user, pass);
			response.getWriter()
					.println("<script>alert('Đã duyệt yêu cầu'); window.location.href='quanlyyeucau';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
