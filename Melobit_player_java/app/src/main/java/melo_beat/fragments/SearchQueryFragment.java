package melo_beat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import melo_beat.adapters.query.QueryArtistsAdapter;
import melo_beat.adapters.query.QuerySongsAdapter;
import melo_beat.models.Query.ResultsItem;
import melo_beat.models.Query.SearchAndQuery;
import melo_beat.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchQueryFragment extends Fragment {


    EditText editText;
    ImageView search;
    RecyclerView searchArtistsRecycler;
    RecyclerView searchSongsRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_query, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.imageView5);
        editText = view.findViewById(R.id.search_edittext);
        searchArtistsRecycler = view.findViewById(R.id.search_artists_recycler);
        searchSongsRecycler = view.findViewById(R.id.search_songs_recycler);

        search.setOnClickListener(it -> {
            String query = editText.getText().toString();

            RetrofitClient.getInstance().getMyApi().findQuery(query).enqueue(new Callback<SearchAndQuery>() {
                @Override
                public void onResponse(Call<SearchAndQuery> call, Response<SearchAndQuery> response) {
                    ArrayList<ResultsItem> artists = new ArrayList<>();
                    ArrayList<ResultsItem> songs = new ArrayList<>();
                    List<ResultsItem> results = response.body().getResult().getResults();
                    for (ResultsItem resultsItem : results) {
                        String temp = resultsItem.getType().toLowerCase();
                        if (temp.equals("song")) {
                            songs.add(resultsItem);
                        }
                        if (temp.equals("artist")) {
                            artists.add(resultsItem);
                        }
                    }


                    QueryArtistsAdapter queryArtistsAdapter = new QueryArtistsAdapter();
                    searchArtistsRecycler.setAdapter(queryArtistsAdapter);
                    searchArtistsRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    queryArtistsAdapter.setArtists(artists);


                    QuerySongsAdapter querySongsAdapter = new QuerySongsAdapter();
                    searchSongsRecycler.setAdapter(querySongsAdapter);
                    searchSongsRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL
                            , false));
                    querySongsAdapter.setSongs(songs);

                }

                @Override
                public void onFailure(Call<SearchAndQuery> call, Throwable t) {

                }
            });

        });


    }
}