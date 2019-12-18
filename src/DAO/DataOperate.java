package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.postgresql.Driver;

import Data.SongData;
import Data.UserData;
import utils.JDBCUtils;
import utils.contain;

public class DataOperate {
	
	public boolean IsExist(String username) throws Exception, IllegalAccessException, ClassNotFoundException, SQLException
	{
//		建立连接
		Connection dbcon=JDBCUtils.getConnection();
		
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select * from users where UserName like ?");
		st.setString(1, username);
		ResultSet rs = st.executeQuery();
		if(rs.next())
		{
//			关闭连接
			JDBCUtils.closeResource(dbcon,st,rs);
			return false;
		}
		else
		{
//			关闭连接
			JDBCUtils.closeResource(dbcon,st,rs);
			return true;
		}
	}
	/**   
	 * @author:6ev
	 * @date:2019年12月9日 下午10:20:42
	 * @description:检查邮箱是否已经注册
	 * @version:
	 */
	@Test
	public void testIsreg() {
		boolean flag=true;
		try {
			flag=isRegistered("11@qq.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(flag);
		
	}

	public boolean isRegistered(String email) throws Exception, IllegalAccessException, ClassNotFoundException, SQLException
	{
//		建立连接
		Connection dbcon=JDBCUtils.getConnection();
		
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select * from users where email like ?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		if(rs.next())
		{
//			关闭连接
			JDBCUtils.closeResource(dbcon,st,rs);
			return true;
		}
		else
		{
//			关闭连接
			JDBCUtils.closeResource(dbcon,st,rs);
			return false;
		}
	}
	
	public boolean IsListened(int songid,String email) throws Exception, IllegalAccessException, ClassNotFoundException, SQLException
	{
//		建立连接
		Connection dbcon=JDBCUtils.getConnection();
		
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select * from listened where email=? and song_id = ?");
		st.setString(1, email);
		st.setInt(2, songid);
		ResultSet rs = st.executeQuery();
		if(rs.next())
		{
//			关闭连接
			JDBCUtils.closeResource(dbcon,st,rs);
			return true;
		}
		else
		{
//			关闭连接
			JDBCUtils.closeResource(dbcon,st,rs);
			return false;
		}
	}
	
	
	public void add(UserData User) throws Exception, IllegalAccessException, ClassNotFoundException, SQLException
	{
		
		Connection dbcon=JDBCUtils.getConnection();
		
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("insert into users values(?,?,?)");
		st.setString(1, User.getUserName());
		st.setString(2, User.getPassword());
		st.setString(3, User.getEmail());
		st.executeUpdate();
//		关闭连接
		JDBCUtils.closeResource(dbcon,st);
	}
	public boolean login(String email,String password) throws Exception, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection dbcon=JDBCUtils.getConnection();
		
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select password from users where email like ?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
				if(rs.getString("password").equals(password))
			{
//					关闭连接
					JDBCUtils.closeResource(dbcon,st,rs);
					return true;
			}
				else return false;
		}
		return false;
	}
	public String getUsername(String email) throws SQLException, Exception, IllegalAccessException, ClassNotFoundException
	{

		Connection dbcon=JDBCUtils.getConnection();

		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select username from users where email like ?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			String temp =  rs.getString("username");
//			关闭连接
			JDBCUtils.closeResource(dbcon,st,rs);
			return temp;

		}
		return null;
	}
	public ArrayList<SongData> getMainsong() throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,singers,song_singers,song_albums "
				+ "where songs.song_id = song_albums.song_id and songs.song_id = song_singers.song_id and "
				+ "song_singers.singer_id = singers.singer_id and song_albums.album_id = albums.album_id");
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
		}
		JDBCUtils.closeResource(dbcon,st,rs);
		return res;
	}
	public ArrayList<SongData> getListenedsong(String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		System.out.println(email);
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,singers,song_singers,song_albums,listened "
				+ "where songs.song_id = song_albums.song_id and songs.song_id = song_singers.song_id and "
				+ " song_singers.singer_id = singers.singer_id and song_albums.album_id = albums.album_id and "
				+ "listened.song_id = songs.song_id and listened.email = ?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
		}
		JDBCUtils.closeResource(dbcon,st,rs);
		return res;
	}
	public ArrayList<SongData> getLikedsong(String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		System.out.println(email);
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,singers,song_singers,song_albums,liked "
				+ "where songs.song_id = song_albums.song_id and songs.song_id = song_singers.song_id and"
				+ " song_singers.singer_id = singers.singer_id and song_albums.album_id = albums.album_id and "
				+ "liked.song_id = songs.song_id and liked.email = ?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
			System.out.println(songname);
		}
		JDBCUtils.closeResource(dbcon,st,rs);
		System.out.println(res.size());
		return res;
	}
	@Test
	public void testsi() {
		try {
			SongData temp =getSongInfo(108418);
			System.out.println(temp.SongName);
			System.out.println(temp.SingerName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public SongData getSongInfo(int songid) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		System.out.println(songid);
		PreparedStatement st = dbcon.prepareStatement("select song_name,singer_name "
				+ "from songs,singers,song_singers "
				+ "where songs.song_id=song_singers.song_id "
				+ "and singers.singer_id=song_singers.singer_id "
				+ "and songs.song_id= ?");
		st.setInt(1, songid);
		ResultSet rs = st.executeQuery();
		SongData temp = new SongData();
		if(rs.next())
		{
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			System.out.println(songname);
		}
		JDBCUtils.closeResource(dbcon,st,rs);
		return temp;
	}
	
	public ArrayList<SongData> getDownload(String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		System.out.println(email);
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,singers,song_singers,song_albums,download "
				+ "where songs.song_id = song_albums.song_id and songs.song_id = song_singers.song_id and"
				+ " song_singers.singer_id = singers.singer_id and song_albums.album_id = albums.album_id and "
				+ "download.song_id = songs.song_id and download.email = ?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
			System.out.println(songname);
		}
		JDBCUtils.closeResource(dbcon,st,rs);
		System.out.println(res.size());
		return res;
	}
	
	public void AddLiked(int songid,String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		
		PreparedStatement st = dbcon.prepareStatement("insert into liked values (?,?)");
		st.setString(1, email);
		st.setInt(2, songid);
		st.executeUpdate();
		
		JDBCUtils.closeResource(dbcon,st);
	}
	
	public void AddListened(int songid,String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		
		PreparedStatement st = dbcon.prepareStatement("insert into listened values (?,?)");
		st.setString(1, email);
		st.setInt(2, songid);
		st.executeUpdate();
		
		JDBCUtils.closeResource(dbcon,st);
	}
	
	public void AddDownload(int songid,String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		
		PreparedStatement st = dbcon.prepareStatement("insert into download values (?,?)");
		st.setString(1, email);
		st.setInt(2, songid);
		st.executeUpdate();
		
		JDBCUtils.closeResource(dbcon,st);
	}
	
	public void DeleteListened(int songid,String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		
		PreparedStatement st = dbcon.prepareStatement("delete from listened where email=? and song_id = ?");
		st.setString(1, email);
		st.setInt(2, songid);
		st.executeUpdate();
		
		JDBCUtils.closeResource(dbcon,st);
	}
	
	public void DeleteLiked(int songid,String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		
		PreparedStatement st = dbcon.prepareStatement("delete from liked where email=? and song_id = ?");
		st.setString(1, email);
		st.setInt(2, songid);
		st.executeUpdate();
		
		JDBCUtils.closeResource(dbcon,st);
	}
	
	public void DeleteDownload(int songid,String email) throws Exception
	{
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		
		PreparedStatement st = dbcon.prepareStatement("delete from download where email=? and song_id = ?");
		st.setString(1, email);
		st.setInt(2, songid);
		st.executeUpdate();
		
		JDBCUtils.closeResource(dbcon,st);
	}
	
	public ArrayList<SongData> quaryname(String searchname) throws Exception
	{
		System.out.println(searchname);
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,singers,song_singers,song_albums "
				+ "where songs.song_id = song_albums.song_id and songs.song_id = song_singers.song_id and"
				+ " song_singers.singer_id = singers.singer_id and song_albums.album_id = albums.album_id and "
				+"song_name like ?");
		st.setString(1, searchname);
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
		}
		
		JDBCUtils.closeResource(dbcon,st,rs);
		
		return res;
	}
	
	public ArrayList<SongData> quaryalbum(String searchname) throws Exception
	{
		System.out.println(searchname);
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,singers,song_singers,song_albums "
				+ "where songs.song_id = song_albums.song_id and songs.song_id = song_singers.song_id and"
				+ " song_singers.singer_id = singers.singer_id and song_albums.album_id = albums.album_id and "
				+"album_name like ?");
		st.setString(1, searchname);
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
		}
		
		JDBCUtils.closeResource(dbcon,st,rs);
		
		return res;
	}
	
	public ArrayList<SongData> quarytag(String searchname) throws Exception
	{
		System.out.println(searchname);
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,tags,singers,song_singers,song_tags,song_albums "
				+ "where songs.song_id = song_tags.song_id "
				+ "and songs.song_id = song_albums.song_id "
				+ "and songs.song_id = song_singers.song_id "
				+ "and song_singers.singer_id = singers.singer_id "
				+ "and song_tags.tag_id = tags.tag_id "
				+ "and song_albums.album_id = albums.album_id "
				+ "and tag_name like ?");
		st.setString(1, searchname);
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
		}
		
		JDBCUtils.closeResource(dbcon,st,rs);
		
		return res;
	}
	
	public ArrayList<SongData> quarysinger(String searchname) throws Exception
	{
		System.out.println(searchname);
		Connection dbcon=JDBCUtils.getConnection();
		System.out.println("连接数据库成功");
		PreparedStatement st = dbcon.prepareStatement("select songs.song_id,song_name,singer_name,album_name,song_time "
				+ "from songs,albums,singers,song_singers,song_albums "
				+ "where songs.song_id = song_albums.song_id and songs.song_id = song_singers.song_id and"
				+ " song_singers.singer_id = singers.singer_id and song_albums.album_id = albums.album_id and "
				+"singer_name like ?");
		st.setString(1, searchname);
		ResultSet rs = st.executeQuery();
		ArrayList<SongData> res = new ArrayList<SongData>();
		while(rs.next())
		{
			int songid = rs.getInt("song_id");
			String songname = rs.getString("song_name");
			String singername = rs.getString("singer_name");
			String albumname = rs.getString("album_name");
			String songtime = rs.getString("song_time");
			SongData temp = new SongData();
			temp.setSongid(songid);
			temp.setSongName(songname);
			temp.setSingerName(singername);
			temp.setAlbumName(albumname);
			temp.setSongTime(songtime);
			res.add(temp);
		}
		
		JDBCUtils.closeResource(dbcon,st,rs);
		
		return res;
	}
	public ArrayList<SongData> quary(String searchname) throws Exception
	{
		ArrayList<SongData>res1 = quaryname(searchname);
		ArrayList<SongData>res2 = quaryalbum(searchname);
		ArrayList<SongData>res3 = quarysinger(searchname);
		ArrayList<SongData>res4 = quarytag(searchname);
		
		for(int i=0;i<res2.size();i++)
		{
			if(contain.isexist(res2.get(i).getSongid(),res1))
			{
				continue;
			}
			else
			{
				res1.add(res2.get(i));
			}
		}
		for(int i=0;i<res3.size();i++)
		{
			if(contain.isexist(res3.get(i).getSongid(),res1))
			{
				continue;
			}
			else
			{
				res1.add(res3.get(i));
			}
		}
		for(int i=0;i<res4.size();i++)
		{
			if(contain.isexist(res4.get(i).getSongid(),res1))
			{
				continue;
			}
			else
			{
				res1.add(res4.get(i));
			}
		}
		
		return res1;
	}
}
