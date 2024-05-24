package chuyenvien.controllers;

import java.io.IOException;
import java.util.List;

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
import chuyenvien.models.YeuCauXacNhan;

/**
 * Servlet implementation class DuyetYeuCauSV
 */
@WebServlet("/DuyetYeuCauSV")
public class DuyetYeuCauSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DuyetYeuCauSV() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao dao = new dao();
		List<YeuCauXacNhan> listYC = dao.selectAllRequests();
		request.setAttribute("listYCXN", listYC);
		
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		PhongCTSV a = dao.getPhongCTCV(account.getUsernameString());
		session.setAttribute("UserCTSV", a);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewChuyenVien/QLSV.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
