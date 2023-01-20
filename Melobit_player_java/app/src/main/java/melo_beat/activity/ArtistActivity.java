package melo_beat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import melo_beat.adapters.ArtistSongsAdapter;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        RecyclerView artistSongsRecycler = findViewById(R.id.artist_songs_recycler);
        String artistID = getIntent().getStringExtra("ArtistID");

        artistSongsRecycler.setAdapter(new ArtistSongsAdapter(artistID));
        artistSongsRecycler.setLayoutManager(new LinearLayoutManager(this));



    }
}