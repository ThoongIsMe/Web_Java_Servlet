package chuyenvien.controllers;

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

import chuyenvien.dao.dao;
import chuyenvien.models.CacLoaiGiay;
import chuyenvien.models.TaiKhoan;

/**
 * Servlet implementation class AddDichVu
 */
@WebServlet("/AddDichVu")
public class AddDichVu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDichVu() {
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
	    HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	    request.setCharacterEncoding("UTF-8");
	    String madv = request.getParameter("madv");
	    String tendv = request.getParameter("tendv");
	    String tinhTrangValue = request.getParameter("tinhTrang");

	    
	    try {
			dao.insertDICHVUSV(madv, tendv, tinhTrangValue);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
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
