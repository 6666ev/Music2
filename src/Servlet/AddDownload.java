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
 * Servlet implementation class AddDownload
 */
@WebServlet("/AddDownload")
public class AddDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static DataOperate dataop;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDownload() {
        super();
        dataop = new DataOperate();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strsongid =request.getParameter("songid");
		int songid =Integer.parseInt(strsongid);
		String email = request.getParameter("email");
		System.out.println(songid);
		System.out.println(email);
		PrintWriter out=response.getWriter();
		out.write("{\"msg\":\"true\"}");

		try {
			dataop.AddDownload(songid, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		response.sendRedirect("DownloadManager");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
