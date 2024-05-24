package chuyenvien.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.dao.dao;
import chuyenvien.models.Giayxacnhan;
import chuyenvien.models.PhongCTSV;
import chuyenvien.models.TaiKhoan;
import chuyenvien.models.ThongTinSV;
import chuyenvien.models.YeuCauXacNhan;
import utils.EmailUtility;

@WebServlet("/")
public class DuyetXoaYeuCau extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	public DuyetXoaYeuCau() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		try {
			switch (action) {
			case "/Duyet":
				updateDuyet(request, response);
				break;
			case "/TuChoi":
				xoaDuyet(request, response);
				break;
			case "/DuyetAll":
				UpdateAllDuyet(request, response);
				break;
			case "/XacNhanNhanGiay":
				updateNhanNhanGiay(request, response);
				break;

			case "/SearchYC":
				searchYCDuyet(request, response);
				break;
			case "/searchNhanGiay":
				searchNhanGiay(request, response);
				break;

			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("ViewChuyenVien/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void searchYCDuyet(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String idMasv = request.getParameter("searchMaSV");

		System.out.println(idMasv);

		dao dao = new dao();
		List<YeuCauXacNhan> listYC = dao.searchRequestsByMaSV(idMasv); // Use the new search method

		request.setAttribute("listYCXN", listYC);

		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		PhongCTSV a = dao.getPhongCTCV(account.getUsernameString());
		session.setAttribute("UserCTSV", a);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewChuyenVien/QLSV.jsp");
		dispatcher.forward(request, response);

	}

	private void searchNhanGiay(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String idMasv = request.getParameter("searchMaSV");

		System.out.println(idMasv);

		dao dao = new dao();
		List<YeuCauXacNhan> listYC = dao.searchRequestsByMaSV(idMasv); // Use the new search method

		request.setAttribute("listNXG", listYC);

		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		PhongCTSV a = dao.getPhongCTCV(account.getUsernameString());
		session.setAttribute("UserCTSV", a);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewChuyenVien/XacNhanGiay.jsp");
		dispatcher.forward(request, response);
	}

	private void updateNhanNhanGiay(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String productId = request.getParameter("productId");
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		dao dao = new dao();
		dao.UpdateGIAYXNNHAN(3, productId);
		response.sendRedirect("XacNhanLayGiay");
	}

	private void UpdateAllDuyet(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		dao dao = new dao();
		List<YeuCauXacNhan> listYCXNG = dao.searchByTrangThai(1);

		for (YeuCauXacNhan yeuCau : listYCXNG) {
			System.out.println("ID: " + yeuCau.getGiayxacnhan().getId());
			System.out.println("MaSV: " + yeuCau.getGiayxacnhan().getMaSV());
			System.out.println("ThoiGianXin: " + yeuCau.getGiayxacnhan().getThoiGianXin());
			// Print other properties as needed
			System.out.println("-----------------------------");
			
			String giayXacNhanIdAsString = String.valueOf(yeuCau.getGiayxacnhan().getId());
			String giayXacNhanMASVAsString = String.valueOf(yeuCau.getGiayxacnhan().getMaSV());
			
			dao.UpdateGiayXN(account.getUsernameString(), 2, giayXacNhanIdAsString);
			
			ThongTinSV thongTinSV = dao.getMailThongTinSV(giayXacNhanMASVAsString);
			
			Giayxacnhan giayxacnhan = dao.getGiayxacnhan(giayXacNhanIdAsString);

			LocalDate thoiGianDuyet = giayxacnhan.getThoiGianDuyet();
			LocalDate thoiGianDuyetSau3Ngay = thoiGianDuyet.plusDays(3);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedThoiGianDuyet = thoiGianDuyetSau3Ngay.format(formatter);

			String subject = "Phong Cong Tac Sinh Vien [DHSPKT]";
			String content = 
								"Thân gửi sinh viên,"+"<br>" + "Yêu cầu của bạn đã được duyệt:"+"<br>"
								+ "1. Sinh viên nhận giấy " +  yeuCau.getCacLoaiGiay().getTenloaigiayString() + " theo thời gian bắt đầu nhận: " + formattedThoiGianDuyet // Replace
																																	// giayxacnhan.getThoiGianDuyet()
																																	// with
																																	// formatted
																																	// string
								+ "<br>"
								+ "2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy."
								+ "<br>" 
								+ " 3. Sinh viên cung cấp Mã sinh viên và họ tên cho nhân viên trả giấy.";
			try {
				EmailUtility.sendEmail(host, port, user, pass, thongTinSV.getEmail(), subject, content);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// insert vao bang thong bao;
			dao.insertTHONGBAO(yeuCau.getCacLoaiGiay().getTenloaigiayString(), content, account.getUsernameString(), thoiGianDuyetSau3Ngay, giayXacNhanMASVAsString);

			
		}

		// dao.updateAllGiayXN(account.getUsernameString(), 2); /// So 2 la duyet, 1 la
		// xoa, mac dinh hien thi la 1
		response.sendRedirect("DuyetYeuCauSV");
	}

	private void xoaDuyet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		HttpSession session = request.getSession();
		String CV = request.getParameter("productId");
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		dao dao = new dao();
		dao.UpdateGiayXN(account.getUsernameString(), 0, CV);
		response.sendRedirect("DuyetYeuCauSV");
	}

	private void updateDuyet(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String pid = request.getParameter("productId");

		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");

		String TenGiay = request.getParameter("tenGiay");

		String MaSV = request.getParameter("maSV");
		dao dao = new dao();

		ThongTinSV thongTinSV = dao.getMailThongTinSV(MaSV);

		dao.UpdateGiayXN(account.getUsernameString(), 2, pid);
		System.out.println("33");

		// gui mail
		Giayxacnhan giayxacnhan = dao.getGiayxacnhan(pid);

		LocalDate thoiGianDuyet = giayxacnhan.getThoiGianDuyet();
		LocalDate thoiGianDuyetSau3Ngay = thoiGianDuyet.plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedThoiGianDuyet = thoiGianDuyetSau3Ngay.format(formatter);

		String subject = "Phong Cong Tac Sinh Vien [DHSPKT]";
		String content = "Thân gửi sinh viên,"+"<br>" + "Yêu cầu của bạn đã được duyệt:"+"<br>"
				+ "1. Sinh viên nhận giấy " + TenGiay + " theo thời gian bắt đầu nhận: " + formattedThoiGianDuyet // Replace
																													// giayxacnhan.getThoiGianDuyet()
																													// with
																													// formatted
																													// string
				+ "<br>"
				+ "2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy."
				+ "<br>" 
				+ " 3. Sinh viên cung cấp Mã sinh viên và họ tên cho nhân viên trả giấy.";

		try {
			EmailUtility.sendEmail(host, port, user, pass, thongTinSV.getEmail(), subject, content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// insert vao bang thong bao;
		dao.insertTHONGBAO(TenGiay, content, account.getUsernameString(), thoiGianDuyetSau3Ngay, MaSV);

		response.sendRedirect("DuyetYeuCauSV");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
