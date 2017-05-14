package com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.MovieResult;
import com.lethalskillzz.blockbusters.blockbusters.presentation.Details.DetailsActivity;

import java.util.List;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.BASE_IMG_URL;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.CLICK_GRID;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.DISC_IMAGE_SIZE;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.ViewHolder>  {

    private static final String TAG = "DiscoveryAdapter";
    private Context mContext;
    private List<MovieResult> movieResults;


    public DiscoveryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieResult movieResult = movieResults.get(position);
        holder.title.setText(movieResult.getTitle());
        Glide.with(mContext).load(BASE_IMG_URL+DISC_IMAGE_SIZE+ movieResult.getPosterPath()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (movieResults == null) {
            return 0;
        }
        return movieResults.size();
    }

    public void setMovieResults(List<MovieResult> movieResults) {
        this.movieResults = movieResults;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView image;
        private final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.item_result_image);
            title = (TextView) itemView.findViewById(R.id.item_result_title);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
            intent.putExtra(CLICK_GRID, movieResults.get(getAdapterPosition()));
            view.getContext().startActivity(intent);
        }
    }
}
