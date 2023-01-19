package melo_beat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.R;

import java.io.IOException;

import melo_beat.models.SongInfo.Result;
import melo_beat.models.SongInfo.SongInfo;
import melo_beat.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongActivity extends AppCompatActivity {

    Result song;
    ImageView songCover;
    TextView title;
    TextView artists;
    TextView downloads;
    TextView lyric;
    Button button;
    MediaPlayer mediaPlayer = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);


        songCover = findViewById(R.id.imageView6);
        title = findViewById(R.id.textView12);
        artists = findViewById(R.id.textView13);
        downloads = findViewById(R.id.textView14);
        button = findViewById(R.id.button);
        lyric = findViewById(R.id.textView15);

        String songID = getIntent().getStringExtra("SongID");

        RetrofitClient.getInstance().getMyApi().getSongInfo(songID).enqueue(new Callback<SongInfo>() {
            @Override
            public void onResponse(Call<SongInfo> call, Response<SongInfo> response) {
                song = response.body().getResult();

                Glide
                        .with(songCover)
                        .load(song.getImage().getCover().getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(songCover);

                title.setText(song.getTitle());
                artists.setText(song.getArtists().get(0).getFullName());
                downloads.setText(song.getDownloadCount());

                button.setOnClickListener(v -> {
                    mediaPlayer = MediaPlayer.create(SongActivity.this, Uri.parse(song.getAudio().getHigh().getUrl()));
                    mediaPlayer.start();
                });

                lyric.setText(song.getLyrics());

            }

            @Override
            public void onFailure(Call<SongInfo> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}