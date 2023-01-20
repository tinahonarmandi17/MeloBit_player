package melo_beat.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

import melo_beat.adapters.TopSongsOfWeekAdapter;

public class TopSongsOfWeek extends AppCompatActivity {

    RecyclerView topSongsOfWeekRecycler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs_of_week);
        topSongsOfWeekRecycler = findViewById(R.id.top_songs_of_week_recycler);

        topSongsOfWeekRecycler.setAdapter(new TopSongsOfWeekAdapter());
        topSongsOfWeekRecycler.setLayoutManager(new LinearLayoutManager(this));





    }


}