package melo_beat.api;

import melo_beat.models.hotDay.HotDailySongs;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    public String TOKEN = "?token=800757:63b9cfac0e5de5.12700535";
    public String BASE_URL = "https://one-api.ir/melobit/" ;

    @GET(TOKEN+ "&action=hot_day")
    Call<HotDailySongs> getHotDailySongs();


}
