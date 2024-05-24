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



/**
 * Servlet implementation class DoiMatKhauSV
 */
@WebServlet("/DoiMatKhauSV")
public class DoiMatKhauSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoiMatKhauSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		dao dao = new dao();
		String pass=request.getParameter("password");
		String npass=request.getParameter("newpassword");
		String rpass=request.getParameter("repassword");
		
		System.out.println("role: " + pass + npass + rpass);
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		if(!pass.equals(account.getPasString()))
		{
			String ms="Mật khẩu cũ không đúng!!!";
			request.setAttribute("ms", ms);
			request.getRequestDispatcher("DoiMatKhauSV.jsp").forward(request, response);
		}
		else if(!npass.equals(rpass))
		{
			String ms="Mật khẩu mới và mật khẩu mới nhập lại không trùng";
			request.setAttribute("ms", ms);
			request.getRequestDispatcher("DoiMatKhauSV.jsp").forward(request, response);
		}
		else
		{
			dao.UpdatePassword(account.getUsernameString(),npass);
			String ms1="Đổi mật khẩu thành công!!!";
			request.setAttribute("ms1", ms1);
			account.setPasString(npass);
            session.setAttribute("acc", account);
			request.getRequestDispatcher("DoiMatKhauSV.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
