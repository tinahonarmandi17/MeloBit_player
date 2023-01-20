package melo_beat.models.TrendingArtists;

public class Image{
	private ThumbnailSmall thumbnailSmall;
	private Cover cover;
	private Thumbnail thumbnail;
	private CoverSmall coverSmall;

	public ThumbnailSmall getThumbnailSmall(){
		return thumbnailSmall;
	}

	public Cover getCover(){
		return cover;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public CoverSmall getCoverSmall(){
		return coverSmall;
	}
}
