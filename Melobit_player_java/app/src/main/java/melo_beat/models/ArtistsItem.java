package melo_beat.models;

public class ArtistsItem{
	private Image image;
	private String fullName;
	private String sumSongsDownloadsCount;
	private String id;
	private int followersCount;
	private String type;

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setSumSongsDownloadsCount(String sumSongsDownloadsCount){
		this.sumSongsDownloadsCount = sumSongsDownloadsCount;
	}

	public String getSumSongsDownloadsCount(){
		return sumSongsDownloadsCount;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFollowersCount(int followersCount){
		this.followersCount = followersCount;
	}

	public int getFollowersCount(){
		return followersCount;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}
