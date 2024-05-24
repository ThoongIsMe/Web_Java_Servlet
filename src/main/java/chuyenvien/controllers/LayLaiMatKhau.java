package chuyenvien.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chuyenvien.dao.dao;
import chuyenvien.models.ThongTinSV;
import utils.EmailUtility;

/**
 * Servlet implementation class LayLaiMatKhau
 */
@WebServlet("/LayLaiMatKhauSV")
public class LayLaiMatKhau extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String host;
	private String port;
	private String user;
	private String pass;

	public LayLaiMatKhau() {
		super();

	}

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String mailString = request.getParameter("emailSV");
		System.out.print(mailString);

		dao dao = new dao();
		ThongTinSV pasString = dao.getTimMatKhauSv(mailString);

		if (pasString != null && pasString.getTaiKhoan() != null) {
			String password = pasString.getTaiKhoan().getPasString();

			if (password != null) {
				System.out.print("MK : " + password);

				String recipient = request.getParameter("recipient");
				String subject = "Phong Cong Tac Sinh Vien [DHSPKT]";
				String content = "Thân gửi sinh viên,\r\n" + "Password của bạn là: " + password;

				try {
					EmailUtility.sendEmail(host, port, user, pass, mailString, subject, content);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				request.setAttribute("errorMessageAc", "Lấy lại mật khẩu thành công thành công!!!!");
				request.setAttribute("errorMessageSuccessAc", true);
			}

		} else {
			request.setAttribute("errorMessageAc",
					"Không thể thực hiện hành động lấy lại mật khẩu, vui lòng kiểm tra lại email của bạn vừa nhập.");
			request.setAttribute("errorMessageSuccessAc", false);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("QuenMatKhau.jsp");
		dispatcher.forward(request, response);
	}

}
