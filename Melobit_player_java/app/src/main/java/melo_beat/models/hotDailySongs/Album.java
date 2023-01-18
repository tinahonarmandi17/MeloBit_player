package melo_beat.models.hotDailySongs;

import java.util.List;

public class Album{
	private Image image;
	private List<ArtistsItem> artists;
	private String releaseDate;
	private String name;
	private String id;

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setArtists(List<ArtistsItem> artists){
		this.artists = artists;
	}

	public List<ArtistsItem> getArtists(){
		return artists;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}