package melo_beat.adapters;

import android.util.Log;
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

import melo_beat.models.HotDailySongs;
import melo_beat.models.ResultsItem;
import melo_beat.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyHotSongsAdapter extends RecyclerView.Adapter<DailyHotSongsAdapter.ViewHolder> {

    private ArrayList<ResultsItem> songs = new ArrayList();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RetrofitClient.getInstance().getMyApi().getHotDailySongs().enqueue(new Callback<HotDailySongs>() {
            @Override
            public void onResponse(Call<HotDailySongs> call, Response<HotDailySongs> response) {
                songs = (ArrayList<ResultsItem>) response.body().getResult().getResults();
                notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<HotDailySongs> call, Throwable t) {
            }
        });


    }

    @Override
    public DailyHotSongsAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyHotSongsAdapter.ViewHolder holder, int position) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
