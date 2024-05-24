package sinhvien.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sinhvien.dao.dao;
import chuyenvien.models.TaiKhoan;
import chuyenvien.models.ThongTinSV;


/**
 * Servlet implementation class CapNhatThongTinSV
 */
@WebServlet("/CapNhatThongTinSV")
public class CapNhatThongTinSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatThongTinSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao dao = new dao();
		String sdt=request.getParameter("sdt");
		String email= request.getParameter("email");
		String diaChi=request.getParameter("diaChi");
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		ThongTinSV a = dao.getThongTinSV(account.getUsernameString());
		dao.UpdateThongTinSV(sdt, email, diaChi, a.getMaSV());
		a.setSDT(sdt);
		a.setEmail(email);
		a.setDiaChiSV(diaChi);
		session.setAttribute("UserSV", a);
		request.getRequestDispatcher("Thongtincanhan").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
