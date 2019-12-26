package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.postgresql.Driver;

/**   
 * @author:6ev
 * @date:2019年11月30日 上午10:20:44
 * @description:
 * @version:
 */
public class JDBCUtils {
	
	@Test
	public void testConnection() {
		try {
			Connection dbcon=JDBCUtils.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	获取数据库连接
	public static Connection getConnection() throws Exception {

		Driver driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
		DriverManager.registerDriver(driver);
		Connection dbcon = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "24208", "123");
		
		return dbcon;
	}
	
	
//	关闭数据库连接
	public static void closeResource(Connection conn, PreparedStatement ps) {
		try {
//			7.关闭资源
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
//			7.关闭资源
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
