package melo_beat.adapters.query;

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

import melo_beat.activity.SongActivity;
import melo_beat.models.Query.ResultsItem;
import melo_beat.models.Query.Song;

public class QuerySongsAdapter extends RecyclerView.Adapter<QuerySongsAdapter.ViewHolder> {


    ArrayList<ResultsItem> songs = new ArrayList();

    @NonNull
    @Override
    public QuerySongsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songs.get(position).getSong();
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


    public void setSongs(ArrayList<ResultsItem> songs) {
        this.songs = songs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return   songs.size();
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
