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
import chuyenvien.models.TaiKhoan;
import chuyenvien.models.YeuCauXacNhan;


@WebServlet("/Thongke")
public class Thongke extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Thongke() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("acc");
		if (account == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		dao dao = new dao();
		
		int count0 =  dao.getCOUNTTHONGKE(0); //da xoa
		
		int count1 =  dao.getCOUNTTHONGKE(2); //daduyet
		
		int count2 =  dao.getCOUNTTHONGKE(1); //chua duyet
		
		int count3 =  dao.getCOUNTTHONGKE(3); //da dc nhan
		
		int count = count0 + count1 + count2 + count3;
		
		
		
		
		List<String> distinctNamNhapHocList = dao.getDistinctNamNhapHocSorted();
		request.setAttribute("distinctNamNhapHocList", distinctNamNhapHocList);
		
		List<String> khoahoclist = dao.getKhoaHoc();
		request.setAttribute("khoahoclist", khoahoclist);
		
		
		List<String> NamHoclist = dao.getNamHoc();
		request.setAttribute("NamHoclist", NamHoclist);
		
		List<YeuCauXacNhan> listINT = dao.selectAllThongKe();
		request.setAttribute("listtrangthai", listINT);
		
		
		String selectedDepartment = request.getParameter("department");

        String selectedAcademicYear = request.getParameter("academicYear");

        String selectedSchoolYear = request.getParameter("schoolYear");
        
        
        
        request.setAttribute("selectedDepartment", selectedDepartment);
        request.setAttribute("selectedAcademicYear", selectedAcademicYear);
        request.setAttribute("selectedSchoolYear", selectedSchoolYear);
        
        List<YeuCauXacNhan> listThongKeCauXacNhans = dao.searchByThongKe(selectedDepartment, selectedAcademicYear, selectedSchoolYear);

        
        System.out.println("Department: " + selectedDepartment);
        System.out.println("Academic Year: " + selectedAcademicYear);
        System.out.println("School Year: " + selectedSchoolYear);


		
				
        String buttonName = request.getParameter("buttonName");
        if (buttonName != null) {
            if (buttonName.equals("3")) {
                List<YeuCauXacNhan> searchDOIDuyetLISTTT = dao.searchByTrangThai(1);
        		request.setAttribute("listtrangthai", searchDOIDuyetLISTTT);
                
            } else if (buttonName.equals("2")) {
            	List<YeuCauXacNhan> searchDADuyetLISTTT = dao.searchByTrangThai(2);
        		request.setAttribute("listtrangthai", searchDADuyetLISTTT);
            } else if (buttonName.equals("4")) {
            	List<YeuCauXacNhan> searchdaNhanLISTTT = dao.searchByTrangThai(3);
        		request.setAttribute("listtrangthai", searchdaNhanLISTTT);
            }
	         else if (buttonName.equals("1")) {
	    		request.setAttribute("listtrangthai", listINT);
	        }
	         else if (buttonName.equals("5")) {
		    		request.setAttribute("listtrangthai", listThongKeCauXacNhans);
		        }
	}
		
		
		
		request.setAttribute("count1", count1);
		
		request.setAttribute("count2", count2);
		
		request.setAttribute("count3", count3);
		
		request.setAttribute("count", count);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewChuyenVien/Thongke.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
