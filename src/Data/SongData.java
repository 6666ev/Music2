package Data;

public class SongData  {
	
	public int Songid;
	public String SongName;
	public String SingerName;
	public String AlbumName;
	public String SongTime;
	public String picURL;
	public String TagName;
	
	

	public SongData(int songid, String songName, String singerName, String albumName, String songTime, String picURL,
			String tagName) {
		super();
		Songid = songid;
		SongName = songName;
		SingerName = singerName;
		AlbumName = albumName;
		SongTime = songTime;
		this.picURL = picURL;
		TagName = tagName;
	}

	public SongData()
	{
		
	}
	public String getTagName() {
		return TagName;
	}

	public void setTagName(String tagName) {
		TagName = tagName;
	}

	public int getSongid()
	{
		return Songid;
	}
	public void setSongid(int songid)
	{
		Songid = songid;
	}
	public String getSongName() {
		return SongName;
	}
	public void setSongName(String songName) {
		SongName = songName;
	}
	public String getSingerName() {
		return SingerName;
	}
	public void setSingerName(String singerName) {
		SingerName = singerName;
	}
	public String getAlbumName() {
		return AlbumName;
	}
	public void setAlbumName(String albumName) {
		AlbumName = albumName;
	}
	public String getSongTime() {
		return SongTime;
	}
	public void setSongTime(String songTime) {
		SongTime = songTime;
	}
	public String getPicURL() {
		return picURL;
	}
	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	

}
