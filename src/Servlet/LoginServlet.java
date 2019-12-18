package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataOperate;
import Data.SongData;
import Data.UserData;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static DataOperate dataop;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        dataop = new DataOperate();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String email= null;
		String password = null;
		
		
		email = request.getParameter("loginemail");
		password = request.getParameter("loginpassword");

		try {
			if(dataop.login(email, password))
			{
				String username = dataop.getUsername(email);
				ArrayList<SongData> res = dataop.getMainsong();
				ArrayList<SongData> listened = dataop.getLikedsong(email);
				request.getSession().setAttribute("myStar", listened);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("mainsong", res);
				request.getSession().setAttribute("name", username);
				request.getSession().setAttribute("isLoginSuccess", true);
				System.out.println("success");
				response.sendRedirect("main.jsp");
			}
			else {
				request.getSession().setAttribute("isLoginSuccess", false);
				System.out.println("µÇÂ¼Ê§°Ü£¡");

				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
