package melo_beat.models.SongInfo;

import java.util.List;

public class Album{
	private Image image;
	private List<ArtistsItem> artists;
	private String releaseDate;
	private String name;

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
}