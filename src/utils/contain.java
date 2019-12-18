package utils;

import java.util.ArrayList;

import Data.SongData;

public class contain {
	public static boolean  isexist(int song_id,ArrayList<SongData>LikeDatas)
	{
		for(int i=0;i<LikeDatas.size();i++)
		{
			if(LikeDatas.get(i).Songid ==song_id)
				return true;
		}
		return false;
	}
}
