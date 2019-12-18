package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataOperate;

/**
 * Servlet implementation class DeleteDownload
 */
@WebServlet("/DeleteDownload")
public class DeleteDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataOperate dataop;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDownload() {
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
		String strsongid =request.getParameter("songid");
		int songid =Integer.parseInt(strsongid);
		String email = request.getParameter("email");
		System.out.println(songid);
		System.out.println(email);
		
		PrintWriter out=response.getWriter();
		out.write("{\"msg\":\"true\"}");
		try {
			dataop.DeleteDownload(songid, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
