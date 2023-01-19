package melo_beat.api;

import melo_beat.models.ArtistSongs.ArtistsSongs;
import melo_beat.models.HotSongsOfweek.HotSongsOfWeek;
import melo_beat.models.SongInfo.SongInfo;
import melo_beat.models.TrendingArtists.HotArtists;
import melo_beat.models.hotDay.HotDailySongs;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    public String TOKEN = "?token=800757:63b9cfac0e5de5.12700535";
    public String BASE_URL = "https://one-api.ir/melobit/";

    @GET(TOKEN + "&action=hot_day")
    Call<HotDailySongs> getHotDailySongs();

    @GET(TOKEN + "&action=hot_artists")
    Call<HotArtists> getHotArtists();

    @GET(TOKEN + "&action=artists_tracks&")
    Call<ArtistsSongs> getArtistSongs(
            @Query("id") String id);


    @GET(TOKEN + "&action=get_song&")
    Call<SongInfo> getSongInfo(
            @Query("id") String id);

  @GET(TOKEN + "&action=hot_week")
    Call<HotSongsOfWeek> getTopSongsOfWeek();


}
