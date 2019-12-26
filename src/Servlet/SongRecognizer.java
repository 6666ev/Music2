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

/**
 * Servlet implementation class SongRecognizer
 */
@WebServlet("/SongRecognizer")
public class SongRecognizer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String email;
	private static DataOperate dataop;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongRecognizer() {
        super();
        // TODO Auto-generated constructor stub
        dataop = new DataOperate();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		email = (String)request.getSession().getAttribute("email"); 
		
		
		response.sendRedirect("songRecognizer.jsp");
	}

}
