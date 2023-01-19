package melo_beat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import melo_beat.activity.TopSongsOfWeek;
import melo_beat.adapters.DailyHotSongsAdapter;
import melo_beat.adapters.TrendingArtistsAdapter;


public class OnlinePlayListFragment extends Fragment {


    private RecyclerView hotSongsDaily;
    private RecyclerView trendingArtists;
    private TextView topSongsOfWeek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_online_play_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        hotSongsDaily = view.findViewById(R.id.hot_songs_playlist);
        trendingArtists = view.findViewById(R.id.trending_artists_recycler);
        topSongsOfWeek = view.findViewById(R.id.textView11);

        hotSongsDaily.setAdapter(new DailyHotSongsAdapter());
        hotSongsDaily.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));


        trendingArtists.setAdapter(new TrendingArtistsAdapter());
        trendingArtists.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));


        topSongsOfWeek.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopSongsOfWeek.class );
            this.startActivity(intent);
        });


    }
}