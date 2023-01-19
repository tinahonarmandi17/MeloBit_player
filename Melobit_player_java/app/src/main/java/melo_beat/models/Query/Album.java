package melo_beat.models.Query;

import java.util.List;

public class Album{
	private Image image;
	private List<ArtistsItem> artists;
	private String releaseDate;
	private String name;
	private String id;

	public Image getImage(){
		return image;
	}

	public List<ArtistsItem> getArtists(){
		return artists;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}
}