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

import org.postgresql.util.PSQLException;

import com.alibaba.fastjson.JSON;

import DAO.DataOperate;
import Data.SongData;

/**
 * Servlet implementation class AddListened
 */
@WebServlet("/AddListened")
public class AddListened extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static DataOperate dataop;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddListened() {
        super();
        dataop = new DataOperate();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String strsongid =request.getParameter("songid");
		int songid =Integer.parseInt(strsongid);
		String email = request.getParameter("email");
		System.out.println(songid);
		System.out.println(email);
		
		try {
			if(!dataop.IsListened(songid, email)) {
				dataop.AddListened(songid, email);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//					ArrayList<SongData> res = dataop.getSongInfo(songid);

			SongData song_data;
			try {
				song_data = dataop.getSongInfo(songid);
				String text = JSON.toJSONString(song_data);

				PrintWriter out=response.getWriter();
				out.print(text);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
