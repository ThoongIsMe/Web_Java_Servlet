package chuyenvien.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.dao.dao;
import chuyenvien.models.CacLoaiGiay;
import chuyenvien.models.TaiKhoan;

/**
 * Servlet implementation class UpdateDichVu
 */
@WebServlet("/UpdateDichVu")
public class UpdateDichVu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		String pidString = request.getParameter("madv");
		String tendvString = request.getParameter("tendv");

		String tinhtrang = request.getParameter("tinhTrang");

		dao dao = new dao();


		dao.UpdateDichVuSV(tendvString, tinhtrang, pidString);
		response.sendRedirect("DichVuSV");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
