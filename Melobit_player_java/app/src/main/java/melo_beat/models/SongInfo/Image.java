package melo_beat.models.SongInfo;

public class Image{
	private ThumbnailSmall thumbnailSmall;
	private Cover cover;
	private Thumbnail thumbnail;
	private CoverSmall coverSmall;
	private Slider slider;

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

	public Slider getSlider(){
		return slider;
	}
}
