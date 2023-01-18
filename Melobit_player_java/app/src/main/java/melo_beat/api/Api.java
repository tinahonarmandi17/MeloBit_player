package melo_beat.api;

import melo_beat.models.hotDailySongs.HotDailySongs;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    public static  String TOKEN = "800757:63b9cfac0e5de5.12700535";
    public static String BASE_URL = "http://one-api.ir/melobit/?token="+TOKEN;


    @GET(BASE_URL + "&action=hot_day")
    Call<HotDailySongs> getHotDailySongs();




}
