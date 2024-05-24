package sinhvien.controllers;

import java.awt.im.InputMethodRequests;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sinhvien.dao.dao;
import chuyenvien.models.CacLoaiGiay;
import chuyenvien.models.TaiKhoan;
import chuyenvien.models.ThongTinSV;
import chuyenvien.models.YeuCauXacNhan;



/**
 * Servlet implementation class XinGiay
 */
@WebServlet("/XemDichVu")
public class XemDichVu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XemDichVu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		dao dao = new dao();
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		ThongTinSV a = dao.getThongTinSV(account.getUsernameString());
		session.setAttribute("UserSV", a);
		List<CacLoaiGiay> listCLG = dao.getAllCacLoaiGiay_U();
		session.setAttribute("CacLoaiGiay_U", listCLG);
		int MaSV=Integer.parseInt(account.getUsernameString());
		List<YeuCauXacNhan> listYC=dao.getAllGiayXacNhan_BySV(MaSV);
		request.setAttribute("YeuCauSV", listYC);
		request.getRequestDispatcher("ViewSinhVien/XemDichVu1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
