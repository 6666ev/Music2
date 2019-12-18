package Data;


/**   
 * @author:6ev
 * @date:2019年12月11日 下午1:18:56
 * @description:用于RestfulUtil里 fastjson解析jsonarray
 * @version:
 */
public class SongInfo {
	private int songid;
	private String songname;
	private String singername;
	private String picURL;
	
	public SongInfo() {
		super();
	}
	public SongInfo(int songid, String songname, String singername, String picURL) {
		super();
		this.songid = songid;
		this.songname = songname;
		this.singername = singername;
		this.picURL = picURL;
	}
	public int getSongid() {
		return songid;
	}
	public void setSongid(int songid) {
		this.songid = songid;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public String getSingername() {
		return singername;
	}
	public void setSingername(String singername) {
		this.singername = singername;
	}
	public String getPicURL() {
		return picURL;
	}
	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}
	
	
}
