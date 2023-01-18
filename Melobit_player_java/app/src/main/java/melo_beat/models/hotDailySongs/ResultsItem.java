package melo_beat.models.hotDailySongs;

import java.util.List;

public class ResultsItem{
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

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getDuration(){
		return duration;
	}

	public void setHasVideo(boolean hasVideo){
		this.hasVideo = hasVideo;
	}

	public boolean isHasVideo(){
		return hasVideo;
	}

	public void setCopyrighted(boolean copyrighted){
		this.copyrighted = copyrighted;
	}

	public boolean isCopyrighted(){
		return copyrighted;
	}

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

	public void setAlbum(Album album){
		this.album = album;
	}

	public Album getAlbum(){
		return album;
	}

	public void setLocalized(boolean localized){
		this.localized = localized;
	}

	public boolean isLocalized(){
		return localized;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAudio(Audio audio){
		this.audio = audio;
	}

	public Audio getAudio(){
		return audio;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setDownloadCount(String downloadCount){
		this.downloadCount = downloadCount;
	}

	public String getDownloadCount(){
		return downloadCount;
	}
}