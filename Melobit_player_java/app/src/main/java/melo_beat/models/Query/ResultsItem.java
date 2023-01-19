package melo_beat.models.Query;

public class ResultsItem{
	private Artist artist;
	private int position;
	private String type;
	private Song song;
	private Album album;

	public Artist getArtist(){
		return artist;
	}

	public int getPosition(){
		return position;
	}

	public String getType(){
		return type;
	}

	public Song getSong(){
		return song;
	}

	public Album getAlbum(){
		return album;
	}
}
