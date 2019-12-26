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

@WebServlet("/MyListened")
public class MyListened extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DataOperate dataop;   
	public static String email;
	public MyListened(){
		dataop = new DataOperate();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		email = (String)request.getSession().getAttribute("email"); 
		doPost(request,response);
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		ArrayList<SongData> res = dataop.getListenedsong(email);
		request.getSession().setAttribute("myListened", res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("myListened.jsp");
	
	}

}
