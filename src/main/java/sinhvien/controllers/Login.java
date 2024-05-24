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
 * Servlet implementation class Login
 */
@WebServlet("/LoginSV")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao dao = new dao();
        String userr = request.getParameter("username");
        String passs = request.getParameter("password");
        String role = request.getParameter("role");
        System.out.println("role: " + userr + passs + role);
        TaiKhoan account = dao.login(userr, passs, role);
        if (account == null) {
           request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
           System.out.println("r");
           if (account.getTinhTrang() == 0) {
              System.out.println("TinhTrang: " + account.getTinhTrang());
              request.getRequestDispatcher("login.jsp").forward(request, response);
           } else {
              HttpSession session = request.getSession();
              session.setAttribute("acc", account);
              response.sendRedirect("TrangchuSV");
           }
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
