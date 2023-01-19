package melo_beat.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.R;

import java.util.ArrayList;

import melo_beat.models.TrendingArtists.HotArtists;
import melo_beat.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import melo_beat.models.TrendingArtists.*;

public class TrendingArtistsAdapter extends RecyclerView.Adapter<TrendingArtistsAdapter.ViewHolder> {


    private ArrayList<ResultsItem> artists = new ArrayList<>();


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RetrofitClient.getInstance().getMyApi().getHotArtists().enqueue(new Callback<HotArtists>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<HotArtists> call, @NonNull Response<HotArtists> response) {
                artists = (ArrayList<ResultsItem>) response.body().getResult().getResults();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HotArtists> call, Throwable t) {
            }
        });

    }

    @Override
    public TrendingArtistsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingArtistsAdapter.ViewHolder holder, int position) {

        ResultsItem artist = artists.get(position);
        Glide
                .with(holder.image)
                .load(artist.getImage().getCover().getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        holder.name.setText(artist.getFullName());
        try {
            holder.totalFollower.setText(artist.getSumSongsDownloadsCount() + " Downloads ");
        } catch (Exception ignored) {

        }


    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView totalFollower;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.imageView4);
            name = itemView.findViewById(R.id.textView9);
            totalFollower = itemView.findViewById(R.id.textView10);
        }
    }
}
