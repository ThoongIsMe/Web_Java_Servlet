package chuyenvien.controllers;

import java.io.IOException;
import java.net.Inet4Address;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.dao.dao;
import chuyenvien.models.TaiKhoan;
import quantrivien.dao.TinhTrangDao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao dao = new dao();
		TinhTrangDao tinhTrangDao = new TinhTrangDao();
		String userr = request.getParameter("username");
		String passs = request.getParameter("password");
		String role = request.getParameter("role");
		TaiKhoan account = dao.login(userr, passs, role);
		if (account == null) {
			
			request.setAttribute("errorMessageAc", "Thông tin đăng nhập sai!!! Vui lòng kiểm tra lại thông tin đăng nhập !!!");
			request.setAttribute("errorMessageSuccessAc", false);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			if (account.getTinhTrang() == 0) {
				request.setAttribute("errorMessageAc", "Tài khoản đã bị khóa!!! Vui lòng liên hệ quản trị viên!!!");
				request.setAttribute("errorMessageSuccessAc", false);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				tinhTrangDao.setLuotTruyCap(request, response);
				String localAddres = Inet4Address.getLocalHost().getHostAddress();
	            System.out.println(localAddres +"_ip:" + localAddres);
				
				if (account.getRoleString().equals("1")) {
					///
					String ipAddress = Inet4Address.getLocalHost().getHostAddress();
		            System.out.println("_ip:" + ipAddress);
		            try {
						dao.insertAccess(ipAddress);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					///
					session.setAttribute("acc", account);
					response.sendRedirect("TrangchuSV");
				} else if (account.getRoleString().equals("2")) {
					///
					String ipAddress = Inet4Address.getLocalHost().getHostAddress();
		            System.out.println("_ip:" + ipAddress);
		            try {
						dao.insertAccess(ipAddress);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					///
					session.setAttribute("acc", account);
					response.sendRedirect("TrangchuCTSV");
				} else if (account.getRoleString().equals("3")) {
					///
					String ipAddress = Inet4Address.getLocalHost().getHostAddress();
					
		            System.out.println("_ip:" + ipAddress);
		            try {
						dao.insertAccess(ipAddress);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					///
					session.setAttribute("acc", account);
					session.setAttribute("name_label", account.getUsernameString());
					response.sendRedirect("thongbao");
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
