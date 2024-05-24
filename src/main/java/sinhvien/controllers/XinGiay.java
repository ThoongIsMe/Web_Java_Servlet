package sinhvien.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sinhvien.dao.dao;
import chuyenvien.models.CacLoaiGiay;
import chuyenvien.models.PhongCTSV;
import chuyenvien.models.TaiKhoan;
import chuyenvien.models.ThongTinSV;
import utils.EmailUtility;


/**
 * Servlet implementation class XinGiay
 */
@WebServlet("/XinGiay")
public class XinGiay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
    private String user;
    private String pass;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XinGiay() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			response.sendRedirect("XemDichVu");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		dao dao = new dao();
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		request.setCharacterEncoding("UTF-8");
		String LyDo = request.getParameter("Lydo");
        String MaGiayXN = request.getParameter("loaiGiayXN");
        CacLoaiGiay loaigiay=dao.getTenGiay(MaGiayXN);
        int SL = Integer.parseInt(request.getParameter("soluong"));
		ThongTinSV a = dao.getThongTinSV(account.getUsernameString());
		int MaSV=Integer.parseInt(account.getUsernameString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String recipient="21110421@student.hcmute.edu.vn";
		String subject = "Sinh viên " +a.getHoteString()+" đăng ký giấy xác nhận";
		String content = "Kính gửi phòng CTSV,<br>"
				+"Em tên là : "+a.getHoteString()
				+"<br>"
				+"Mã số sinh viên: "+a.getMaSV()
				+"<br>"
				+"Em đã đăng ký xin: "+ loaigiay.getTenloaigiayString()+"<br>"
				+"Số lượng: "+SL+"<br>"
				+"Vào ngày: " +(LocalDate.now().format(formatter)).toString()+"<br>"
				+"Với lý do: "+LyDo+"<br>"
				+"Em mong yêu cầu của em sẽ được xác nhận sớm. Em xin cảm ơn.<br>"
				+"Trân trọng,<br>"
				+a.getHoteString();
		List<PhongCTSV> listCV = dao.getAllCV();
		System.out.println(listCV);
		PhongCTSV RandomCV=new PhongCTSV();
		if (listCV != null ) 
		{
        Random random = new Random();
        int randomIndex = random.nextInt(listCV.size());
        	RandomCV=listCV.get(randomIndex);
        }
		try {
			dao.insertGiayXN(MaSV, LyDo, MaGiayXN, SL);
			dao.insertTHONGBAO(subject, content, account.getUsernameString(),LocalDate.now() , RandomCV.getMaCV());		
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		//mail
		
		
		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		response.sendRedirect("XemDichVu");
	}

}
