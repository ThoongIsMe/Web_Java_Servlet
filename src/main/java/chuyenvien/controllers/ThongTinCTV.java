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

/**
 * Servlet implementation class ThongTinCTV
 */
@WebServlet("/ThongTinCTV")
public class ThongTinCTV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThongTinCTV() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao dao= new dao();
		HttpSession session = request.getSession();

		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		PhongCTSV listTTSV = dao.getPhongCTCV(account.getUsernameString());
		request.setAttribute("listTTCTV", listTTSV);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewChuyenVien/ThongTinCTV.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
