package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataOperate;
import Data.SongData;

@WebServlet("/FindMusic")
public class FindMusic extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String email;
	private static DataOperate dataop;

	public FindMusic(){
		dataop = new DataOperate();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		email = (String)request.getSession().getAttribute("email"); 
		
		try {
		ArrayList<SongData> res = dataop.getLikedsong(email);
		
		request.getSession().setAttribute("myStar", res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("findMusic.jsp");
	
	}

}
