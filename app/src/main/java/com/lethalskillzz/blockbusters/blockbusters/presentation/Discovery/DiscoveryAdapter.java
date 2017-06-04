package com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.database.dao.MovieDataSource;
import com.lethalskillzz.blockbusters.blockbusters.data.model.MovieResult;
import com.lethalskillzz.blockbusters.blockbusters.presentation.Details.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.BASE_IMG_URL;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.DISC_IMAGE_SIZE;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.FAVOURITE_KEY;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.RESULT_KEY;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieResult movieResult = movieResults.get(position);
        holder.title.setText(movieResult.getTitle());
        Picasso.with(mContext).
                load(BASE_IMG_URL+DISC_IMAGE_SIZE+ movieResult.getPosterPath())
                .placeholder(R.mipmap.no_poster).into(holder.poster);
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

        private final ImageView poster;
        private final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            poster = (ImageView) itemView.findViewById(R.id.item_movie_poster);
            title = (TextView) itemView.findViewById(R.id.item_movie_title);
        }

        @Override
        public void onClick(View view) {

            boolean isFavourite = false;

            MovieDataSource movieDataSource = new MovieDataSource(mContext);
            movieDataSource.open();
            if(movieDataSource.isFavourite(String.valueOf(movieResults.get(getAdapterPosition()).getId()))) {
                isFavourite = true;
            }
            movieDataSource.close();

            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
            intent.putExtra(RESULT_KEY, movieResults.get(getAdapterPosition()));
            intent.putExtra(FAVOURITE_KEY, isFavourite);
            view.getContext().startActivity(intent);
        }
    }
}
