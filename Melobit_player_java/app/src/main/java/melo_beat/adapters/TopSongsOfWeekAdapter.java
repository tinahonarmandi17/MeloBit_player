package melo_beat.adapters;

import android.content.Intent;
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

import melo_beat.activity.SongActivity;
import melo_beat.activity.TopSongsOfWeek;
import melo_beat.models.HotSongsOfweek.HotSongsOfWeek;
import melo_beat.models.HotSongsOfweek.ResultsItem;
import melo_beat.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSongsOfWeekAdapter extends RecyclerView.Adapter<TopSongsOfWeekAdapter.ViewHolder> {

    ArrayList<ResultsItem> songs =new ArrayList();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RetrofitClient.getInstance().getMyApi().getTopSongsOfWeek().enqueue(new Callback<HotSongsOfWeek>() {
            @Override
            public void onResponse(Call<HotSongsOfWeek> call, Response<HotSongsOfWeek> response) {
                songs = (ArrayList<ResultsItem>) response.body().getResult().getResults();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HotSongsOfWeek> call, Throwable t) {

            }
        });
    }

    @Override
    public TopSongsOfWeekAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSongsOfWeekAdapter.ViewHolder holder, int position) {
        ResultsItem song = songs.get(position);
        Glide.with(holder.itemView)
                .load(song.getImage().getCover().getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        holder.title.setText(song.getTitle());
        holder.artists.setText(song.getArtists().get(0).getFullName());
        holder.downloads.setText(song.getDownloadCount());


        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongActivity.class);
                intent.putExtra("SongID", song.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView artists;
        TextView downloads;
        ViewGroup root;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
            title = itemView.findViewById(R.id.textView5);
            artists = itemView.findViewById(R.id.textView7);
            downloads = itemView.findViewById(R.id.textView8);
            root = itemView.findViewById(R.id.song_root);
        }
    }
}
