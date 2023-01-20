package melo_beat.models.SongInfo;

import java.util.List;

public class Result{
	private boolean hasVideo;
	private Image image;
	private String releaseDate;
	private Album album;
	private boolean localized;
	private String title;
	private int duration;
	private boolean copyrighted;
	private List<ArtistsItem> artists;
	private String id;
	private Audio audio;
	private String lyrics;
	private String downloadCount;

	public boolean isHasVideo(){
		return hasVideo;
	}

	public Image getImage(){
		return image;
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

	public String getTitle(){
		return title;
	}

	public int getDuration(){
		return duration;
	}

	public boolean isCopyrighted(){
		return copyrighted;
	}

	public List<ArtistsItem> getArtists(){
		return artists;
	}

	public String getId(){
		return id;
	}

	public Audio getAudio(){
		return audio;
	}

	public String getLyrics(){
		return lyrics;
	}

	public String getDownloadCount(){
		return downloadCount;
	}
}