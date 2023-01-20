package melo_beat.models.Query;

public class Artist{
	private Image image;
	private String fullName;
	private String sumSongsDownloadsCount;
	private String id;
	private int followersCount;
	private String type;

	public Image getImage(){
		return image;
	}

	public String getFullName(){
		return fullName;
	}

	public String getSumSongsDownloadsCount(){
		return sumSongsDownloadsCount;
	}

	public String getId(){
		return id;
	}

	public int getFollowersCount(){
		return followersCount;
	}

	public String getType(){
		return type;
	}
}
