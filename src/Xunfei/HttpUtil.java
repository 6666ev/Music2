package Xunfei;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.naming.ldap.Rdn;


/**
 * Http Client 工具类
 */
public class HttpUtil {
	
	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @param header
	 * @param body
	 * @return
	 */
	public static String doPost(String url, Map<String, String> header, byte[] bytearray) {
		String result = "";
		BufferedReader in = null;
		//PrintWriter out = null;
		try {
			// 设置 url
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			// 设置 header
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			connection.setRequestProperty("Connection", "Keep-Alive");
			// 设置请求 body
			connection.setDoOutput(true);
			connection.setDoInput(true);
			//设置连接超时和读取超时时间
			connection.setConnectTimeout(20000);
			connection.setReadTimeout(20000);

			BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
			
			outputStream.write(bytearray);
			// 发送body
			outputStream.flush();
			outputStream.close();
			int responseCode = connection.getResponseCode();
			if(HttpURLConnection.HTTP_OK == responseCode) {
				// 获取响应body
				in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
//			return null;
		}
		return result;
	}
}
