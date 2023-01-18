package melo_beat.models.hotDailySongs;

public class Image{
	private ThumbnailSmall thumbnailSmall;
	private Cover cover;
	private Thumbnail thumbnail;
	private CoverSmall coverSmall;
	private Slider slider;

	public void setThumbnailSmall(ThumbnailSmall thumbnailSmall){
		this.thumbnailSmall = thumbnailSmall;
	}

	public ThumbnailSmall getThumbnailSmall(){
		return thumbnailSmall;
	}

	public void setCover(Cover cover){
		this.cover = cover;
	}

	public Cover getCover(){
		return cover;
	}

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public void setCoverSmall(CoverSmall coverSmall){
		this.coverSmall = coverSmall;
	}

	public CoverSmall getCoverSmall(){
		return coverSmall;
	}

	public void setSlider(Slider slider){
		this.slider = slider;
	}

	public Slider getSlider(){
		return slider;
	}
}
