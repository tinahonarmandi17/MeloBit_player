package melo_beat.models.Query;

import java.util.List;

public class Song{
	private int duration;
	private boolean hasVideo;
	private boolean copyrighted;
	private Image image;
	private List<ArtistsItem> artists;
	private String releaseDate;
	private Album album;
	private boolean localized;
	private String id;
	private Audio audio;
	private String title;
	private String downloadCount;

	public int getDuration(){
		return duration;
	}

	public boolean isHasVideo(){
		return hasVideo;
	}

	public boolean isCopyrighted(){
		return copyrighted;
	}

	public Image getImage(){
		return image;
	}

	public List<ArtistsItem> getArtists(){
		return artists;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public Album getAlbum(){
		return album;
	}

	public boolean isLocalized(){
		return localized;
	}

	public String getId(){
		return id;
	}

	public Audio getAudio(){
		return audio;
	}

	public String getTitle(){
		return title;
	}

	public String getDownloadCount(){
		return downloadCount;
	}
}