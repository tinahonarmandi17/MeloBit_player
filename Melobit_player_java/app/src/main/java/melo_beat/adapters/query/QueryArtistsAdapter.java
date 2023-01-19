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

import melo_beat.activity.ArtistActivity;
import melo_beat.models.Query.Artist;
import melo_beat.models.Query.ResultsItem;


public class QueryArtistsAdapter extends RecyclerView.Adapter<QueryArtistsAdapter.ViewHolder> {



    ArrayList<ResultsItem> artists ;

    @NonNull
    @Override
    public QueryArtistsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryArtistsAdapter.ViewHolder holder, int position) {

        Artist artist = artists.get(position).getArtist();
        Glide
                .with(holder.image)
                .load(artist.getImage().getCover().getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        holder.name.setText(artist.getFullName());
        holder.totalFollower.setText(artist.getSumSongsDownloadsCount() + " Downloads ");

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.root.getContext(), ArtistActivity.class);
                intent.putExtra("ArtistID", artist.getId());
                holder.root.getContext().startActivity(intent);
            }
        });
    }

    public  void setArtists (ArrayList<ResultsItem> artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView totalFollower;
        ViewGroup root;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.imageView4);
            name = itemView.findViewById(R.id.textView9);
            totalFollower = itemView.findViewById(R.id.textView10);
            root = itemView.findViewById(R.id.artist_root);
        }
    }
}
