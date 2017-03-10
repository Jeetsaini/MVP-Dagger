package myapp.jeet.com.myapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import myapp.jeet.com.myapp.R;
import myapp.jeet.com.myapp.api.model.model.Artist;

/**
 * Created by Admin on 3/10/2017.
 */

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<ArtistRecyclerViewAdapter.ArtistViewHolder>{
    public Context mContext;
    public List<Artist> mArtistList;
    public ArtistRecyclerViewAdapter(Context context, List<Artist> artistList)
    {
        this.mArtistList=artistList;
        this.mContext=context;
    }
    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_recylerview_item,parent, false);
        return new ArtistViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        updateRecylerviewItem(holder,mArtistList.get(position));

    }

    @Override
    public int getItemCount() {
        return mArtistList.size();
    }

    private void updateRecylerviewItem(ArtistViewHolder artistViewHolder,Artist artist)
    {
        artistViewHolder.artistName.setText(artist.name);
        if (artist.artistImages.size() > 0) {
            Picasso.with(mContext).load(artist.artistImages.get(0).url).into(artistViewHolder.artistImage);

        }
    }
    public class ArtistViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView artistImage;
        public TextView artistName;
        public TextView artistAlbum;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            artistImage=(ImageView)itemView.findViewById(R.id.artist_image);
            artistName=(TextView)itemView.findViewById(R.id.artist_name);
        }
    }
}
