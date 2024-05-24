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
import chuyenvien.models.TaiKhoan;

/**
 * Servlet implementation class DeleteDichVuSV
 */
@WebServlet("/DeleteDVSV")
public class DeleteDichVuSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pidString = request.getParameter("pid");

		System.out.print(pidString);
		dao dao = new dao();
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		int check = dao.countGiayXacNhanByMaLoaiGiay(pidString);

		System.out.print(check);
		if (check == 0) {
			dao.deleteDichVuSV(pidString);
			request.setAttribute("errorMessage", "Hành động xóa thành công!!!!");
			request.setAttribute("errorMessageSuccess", true);
		} else {
			request.setAttribute("errorMessage",
					"Không thể thực hiện lệnh xóa khi đang sử dụng dịch vụ");
			request.setAttribute("errorMessageSuccess", false);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("DichVuSV");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
