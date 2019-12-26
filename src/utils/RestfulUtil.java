package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Data.SongData;

public class RestfulUtil {
	
	//get
	  public static String getMethod(String url) throws IOException {
	    URL restURL = new URL(url);
	 
	    HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
	 
	    conn.setRequestMethod("GET"); // POST GET PUT DELETE
	    conn.setRequestProperty("Accept", "application/json");
	 
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    String res_lines="";
	    while((line = br.readLine()) != null ){
	      res_lines+=line;
	    }
	 
	    br.close();
	    return res_lines;
	  }
	  
	//post 
	  public static void postMethod(String url, String query) throws IOException {
	    URL restURL = new URL(url);
	 
	    HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setDoOutput(true);
	 
	    PrintStream ps = new PrintStream(conn.getOutputStream());
	    ps.print(query);
	    ps.close();
	 
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while((line = br.readLine()) != null ){
	      System.out.println(line);
	    }
	 
	    br.close();
	  }
	  
	  @Test 
	  public void testList() {
		  List<String> list=new ArrayList<String>();
		  list.add("Java");
		    list.add("Oracle");
		    list.add("CSS");
		    list.add("XML");
		    System.out.println(list.size());
	  }
	  
	  
	  public void testGetAllSongs() {
		  List<SongData> songs=null;
		  ArrayList<List<SongData>> res=new ArrayList<List<SongData>>();;
			  try {
				  songs=RestfulUtil.getHotSongs();
				  res.add(songs);
				  songs=RestfulUtil.getTopSoaringSongs();
				  res.add(songs);
				  songs=RestfulUtil.getTopNewSongs();
				  res.add(songs);
				  
				  songs=res.get(0);
				  for(SongData song:songs) {
					  System.out.println(song.getSongName()+song.getSingerName()+song.getAlbumName());
					  System.out.println(song.getPicURL());
				  }

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  public static ArrayList<List<SongData>> getAllSongs(){
		  List<SongData> songs=null;
		  ArrayList<List<SongData>> res=new ArrayList<List<SongData>>();
			  try {
				  songs=RestfulUtil.getHotSongs();
				  res.add(songs);
				  songs=RestfulUtil.getTopSoaringSongs();
				  res.add(songs);
				  songs=RestfulUtil.getTopNewSongs();
				  res.add(songs);
				  
				  songs=res.get(0);
				  for(SongData song:songs) {
					  System.out.println(song.getSongName()+song.getSingerName()+song.getAlbumName());
					  System.out.println(song.getPicURL());
				  }

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return res;
	  }
	  
	  
	  public static List<SongData> getHotSongs() {
		  List<SongData> songs=null;
			  try {
				String res=RestfulUtil.getMethod("http://127.0.0.1:5000/hot_songs");
				songs = JSON.parseArray(res, SongData.class); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return songs;
	  }
	  
	  public static List<SongData> getTopSoaringSongs() {
		  List<SongData> songs=null;
		  try {
				String res=RestfulUtil.getMethod("http://127.0.0.1:5000/top_soaring_songs");
				songs = JSON.parseArray(res, SongData.class); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return songs;
	  }
	  
	  public static List<SongData> getTopNewSongs() {
		  List<SongData> songs=null;
		  try {
				String res=RestfulUtil.getMethod("http://127.0.0.1:5000/top_new_songs");
				songs = JSON.parseArray(res, SongData.class); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return songs;
	  }
	  
	  public static void main(String[] args) {
		    try {
		      String res=RestfulUtil.getMethod("http://127.0.0.1:5000/");
//		      System.out.println(res);
		      
			  JSONObject object = JSONObject.parseObject(res);
			  String s1=object.getString("hello");
			  String s2=object.getString("hei");
			  String s3=object.getString("233");
			  
			  System.out.println(s1);
			  System.out.println(s2);
			  System.out.println(s3);
			  
		    }catch (Exception e){
		      System.out.println(e.getMessage());
		    }
		  }
		 

}
