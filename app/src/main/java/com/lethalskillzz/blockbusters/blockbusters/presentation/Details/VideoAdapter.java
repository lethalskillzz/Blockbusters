package com.lethalskillzz.blockbusters.blockbusters.presentation.Details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.VideoResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.PARAM_THUMB;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.VIDEO_THUMB_URL;

/**
 * Created by ibrahimabdulkadir on 01/06/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private static final String TAG = "VideoAdapter";
    private Context mContext;
    private List<VideoResult> videoResults;


    public VideoAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoResult videoResult = videoResults.get(position);
        holder.name.setText(videoResult.getName());
        Picasso.with(mContext).
                load(VIDEO_THUMB_URL+videoResult.getKey()+PARAM_THUMB).into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        if (videoResults == null) {
            return 0;
        }
        return videoResults.size();
    }

    public void setMovieResults(List<VideoResult> movieResults) {
        this.videoResults = movieResults;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView thumb;
        private final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            thumb = (ImageView) itemView.findViewById(R.id.item_video_thumb);
            name = (TextView) itemView.findViewById(R.id.item_video_name);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
