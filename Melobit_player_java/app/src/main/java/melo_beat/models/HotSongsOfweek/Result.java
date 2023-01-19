package melo_beat.models.HotSongsOfweek;

import java.util.List;

public class Result{
	private int total;
	private List<ResultsItem> results;

	public int getTotal(){
		return total;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}