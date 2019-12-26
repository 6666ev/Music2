package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataOperate;
import Data.SongData;
import utils.RestfulUtil;

@WebServlet("/HotSongs")
public class HotSongs extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String email;
	private static DataOperate dataop;

	public HotSongs(){
		dataop = new DataOperate();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		email = (String)request.getSession().getAttribute("email"); 
		
		try {
			ArrayList<List<SongData>> res = RestfulUtil.getAllSongs();
			request.getSession().setAttribute("hotSongs", res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("hotSongs.jsp");
	
	}

}
