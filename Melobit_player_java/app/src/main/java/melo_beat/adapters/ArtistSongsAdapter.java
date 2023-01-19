package melo_beat.adapters;

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
import java.util.List;

import melo_beat.models.ArtistSongs.ArtistsSongs;
import melo_beat.models.ArtistSongs.ResultsItem;
import melo_beat.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistSongsAdapter  extends RecyclerView.Adapter<ArtistSongsAdapter.ViewHolder> {




    private String artistID ;
    private List<ResultsItem> songs = new ArrayList<>();


    public ArtistSongsAdapter(String artistID) {
        this.artistID = artistID;
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RetrofitClient.getInstance().getMyApi().getArtistSongs(artistID).enqueue(new Callback<ArtistsSongs>() {
            @Override
            public void onResponse(Call<ArtistsSongs> call, Response<ArtistsSongs> response) {
                songs = response.body().getResult().getResults();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArtistsSongs> call, Throwable t) {

            }
        });
    }

    @Override
    public ArtistSongsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistSongsAdapter.ViewHolder holder, int position) {
        ResultsItem song = songs.get(position);
        Glide.with(holder.itemView)
                .load(song.getImage().getCover().getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        holder.title.setText(song.getTitle());
        holder.artists.setText(song.getArtists().get(0).getFullName());
        holder.downloads.setText(song.getDownloadCount());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView title ;
        TextView artists ;
        TextView downloads ;
        public ViewHolder( View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
            title = itemView.findViewById(R.id.textView5);
            artists = itemView.findViewById(R.id.textView7);
            downloads = itemView.findViewById(R.id.textView8);
        }
    }
}
