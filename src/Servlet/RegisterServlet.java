package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataOperate;
import Data.UserData;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataOperate dataop;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        dataop = new DataOperate();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String email = request.getParameter("register_email");
		PrintWriter out=response.getWriter();
		try {
			if(dataop.isRegistered(email)) {
				out.write("isRegistered");
			}else {
				out.write("notRegistered");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= null;
		String email= null;
		String password= null;
		
		username = request.getParameter("username");
		email = request.getParameter("email");
		password = request.getParameter("password");
		
		UserData User = new UserData(username,email,password);
		try
		{
			dataop.add(User);
			response.sendRedirect("login.jsp");
		}
		// TODO Auto-generated method stub
		catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("login.jsp");
			e.printStackTrace();
		}
	}

}
