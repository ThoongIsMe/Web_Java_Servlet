package chuyenvien.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.dao.dao;
import chuyenvien.models.PhongCTSV;
import chuyenvien.models.TaiKhoan;
import chuyenvien.models.ThongTinSV;

/**
 * Servlet implementation class CapNhatThongTinCTV
 */
@WebServlet("/CapNhatThongTinCTV")
public class CapNhatThongTinCTV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CapNhatThongTinCTV() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao dao = new dao();
		request.setCharacterEncoding("UTF-8");
		String sdt = request.getParameter("sdt");
		String email = request.getParameter("email");
		String diaChi = request.getParameter("diaChi");
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		PhongCTSV a = dao.getPhongCTCV(account.getUsernameString());
		dao.UpdateThongTinCTV(sdt, email, diaChi, a.getMaCV());
		a.setSoDienThoai(sdt);
		a.setEmail(email);
		a.setDiaChi(diaChi);
		session.setAttribute("UserCTSV", a);
		request.getRequestDispatcher("ThongTinCTV").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
