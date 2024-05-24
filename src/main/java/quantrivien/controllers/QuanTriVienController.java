package quantrivien.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chuyenvien.models.TaiKhoan;
import quantrivien.dao.ImportExcelDao;
import quantrivien.dao.SinhVienDao;
import quantrivien.dao.TinhTrangDao;
import quantrivien.models.SinhVien;

@WebServlet("/quantrivien")
@MultipartConfig
public class QuanTriVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SinhVienDao sinhVienDao;
	private TinhTrangDao tinhTrangDao;
	private ImportExcelDao importExcelDao;

	public void init() {
		sinhVienDao = new SinhVienDao();
		tinhTrangDao = new TinhTrangDao();
		importExcelDao = new ImportExcelDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		String action = request.getParameter("action");

		try {
			if (action == null) {
				tinhTrangWeb(request, response);
			} else {
				switch (action) {
				case "tinhtrangweb":
					tinhTrangWeb(request, response);
					break;
				case "uploadfile":
					uploadFile(request, response);
					break;
				case "importsvfromfile":
					importSVFromFile(request, response);
					break;
				default:
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
					break;
				}
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void tinhTrangWeb(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setAttribute("tong_sotk", tinhTrangDao.countSoTaiKhoan());
		request.setAttribute("tong_sosv", tinhTrangDao.countSinhVien());
		request.setAttribute("tong_socv", tinhTrangDao.countChuyenVien());
		request.setAttribute("luot_dung_dv", tinhTrangDao.countDichVu());
		request.setAttribute("luot_truy_cap", tinhTrangDao.getCountSumAccess());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/tinhtrangweb.jsp");
		dispatcher.forward(request, response);
	}

	private void uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		InputStream input = request.getPart("file").getInputStream();

		importExcelDao.importFile(request, input);

		List<SinhVien> listSV = sinhVienDao.selectAllSinhVienImport();
		request.setAttribute("listSinhVienImport", listSV);
		request.setAttribute("tongsosinhvienimport", tinhTrangDao.countSinhVienImport());

		RequestDispatcher dispatcher = request.getRequestDispatcher("quantrivien/pages/importsinhvien.jsp");
		dispatcher.forward(request, response);
	}

	private void importSVFromFile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		try {
			int result = importExcelDao.themSinhVienFromFile();
			if (result != 0) {
				response.getWriter().println(
						"<script>alert('Đã import sinh viên từ file thành công!'); window.location.href='quanlysinhvien';</script>");
			}
			else
			{	
				response.getWriter().println(
						"<script>alert('Import thất bại!!! Kiểm tra lại file!!!'); window.location.href='quanlysinhvien';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}