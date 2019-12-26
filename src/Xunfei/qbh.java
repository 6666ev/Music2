package Xunfei;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import Data.SongData;



public class qbh
{
		// webapi接口地址
		private static final String WEBSING_URL = "http://webqbh.xfyun.cn/v1/service/v1/qbh";
		// 应用ID
		private static final String APPID = "5de657ad";
		// 接口密钥
		private static final String API_KEY = "981c65a8f33090dee093e4c1f109b66b";
		
		private static final String ENGINE_TYPE = "afs";
		

		private static final String AUE = "raw";
		
		/*客户端传输一个audio_url参数时，Http Request Body需为空*/
		// private static final String AUDIO_URL = "http://cdn.inyuapp.com/audios/20180524120030bEAwdc.wav";
		
		private static final String AUDIO_PATH = "static/test-16k.wav";
		/**
		 * 合成 WebAPI 调用示例程序
		 * 
		 * @param args
		 * @throws IOException
		 */
		public static void main(String[] args) throws IOException {
			Map<String, String> header = buildHttpHeader();
			
			byte[] audioByteArray = FileUtil.read(AUDIO_PATH);
			String result = HttpUtil.doPost(WEBSING_URL, header, audioByteArray);
//			System.out.println("WebAPI 接口调用结果：" + result);

			JSONObject obj=JSON.parseObject(result);//将json字符串转换为json对象
			
			String data=obj.getString("data");
			List<JSONObject> info = JSON.parseArray(data,JSONObject.class); 
			
			System.out.println(info.get(0).getString("song"));
			System.out.println(info.get(0).getString("singer"));

		}
		
		@Test
		public void testFastJSON() {
			String jsonStr="{\"code\":\"0\",\"data\":[{\"song\":\"一次就好\",\"song_id\":\"虾米1774946504\",\"singer\":\"杨宗纬\",\"singer_id\":\"65210\",\"start_time\":194600,\"end_time\":210190},{\"song\":\"一次就好\",\"song_id\":\"虾米1774946504\",\"singer\":\"杨宗纬\",\"singer_id\":\"65210\",\"start_time\":217250,\"end_time\":232790},{\"song\":\"一次就好\",\"song_id\":\"nt35806385\",\"singer\":\"沈腾\",\"singer_id\":\"1482971\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"今天你要嫁给我\",\"song_id\":\"35537232\",\"singer\":\"蔡依林\",\"singer_id\":\"78383\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"春天里\",\"song_id\":\"11319375\",\"singer\":\"汪峰\",\"singer_id\":\"442919\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"值得\",\"song_id\":\"35765475\",\"singer\":\"郑秀文\",\"singer_id\":\"277925\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"素颜\",\"song_id\":\"36373430\",\"singer\":\"许嵩\",\"singer_id\":\"124910\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"庐州月\",\"song_id\":\"4247671\",\"singer\":\"许嵩\",\"singer_id\":\"124910\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"算你狠\",\"song_id\":\"bd244226855\",\"singer\":\"陈小春\",\"singer_id\":\"361822\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"避风港\",\"song_id\":\"10438675\",\"singer\":\"冯曦妤\",\"singer_id\":\"142816\",\"start_time\":-2,\"end_time\":-2},{\"song\":\"不懂浪漫的人\",\"song_id\":\"nt179060\",\"singer\":\"雨天\",\"singer_id\":\"247329\",\"start_time\":-2,\"end_time\":-2}],\"desc\":\"success\",\"sid\":\"wbh460b264d@gz2c9e114d900a460d00\"}\r\n" + 
					"";

			JSONObject obj=JSON.parseObject(jsonStr);//将json字符串转换为json对象
			
			String data=obj.getString("data");
			List<JSONObject> info = JSON.parseArray(data,JSONObject.class); 
			
			System.out.println(info.get(0).getString("song"));
			System.out.println(info.get(0).getString("singer"));

			
		}
		
		
		public static ArrayList<String> getSongInfoByXunfei(String filePath) throws Exception{
			
			ArrayList<String> ret=new ArrayList<String>();
			Map<String, String> header = buildHttpHeader();
			
			byte[] audioByteArray = FileUtil.read(filePath);
			String result = HttpUtil.doPost(WEBSING_URL, header, audioByteArray);
//			System.out.println("WebAPI 接口调用结果：" + result);

			JSONObject data=JSON.parseObject(result);//将json字符串转换为json对象
			
			String data_str=data.getString("data");
			List<JSONObject> info = JSON.parseArray(data_str,JSONObject.class); 
			
			String song=info.get(0).getString("song");
			String singer=info.get(0).getString("singer");
			
			System.out.println(song);
			System.out.println(singer);
			
			ret.add(song);
			ret.add(singer);
			return ret;
		}

		/**
		 * 组装http请求头
		 */
		private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
			String curTime = System.currentTimeMillis() / 1000L + "";
			// 如果使用audio_url传输音频，须在param中添加audio_url参数
			String param = "{\"aue\":\"" + AUE + "\",\"engine_type\":\"" + ENGINE_TYPE + "\"}";
			String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
			String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			header.put("X-Param", paramBase64);
			header.put("X-CurTime", curTime);
			header.put("X-CheckSum", checkSum);
			header.put("X-Appid", APPID);
			return header;
		}
}
