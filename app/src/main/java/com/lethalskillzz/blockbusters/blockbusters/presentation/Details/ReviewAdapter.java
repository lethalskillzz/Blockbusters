package com.lethalskillzz.blockbusters.blockbusters.presentation.Details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.ReviewResult;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 01/06/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private static final String TAG = "ReviewAdapter";
    private Context mContext;
    private List<ReviewResult> reviewResults;


    public ReviewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReviewResult reviewResult = reviewResults.get(position);
        holder.author.setText(reviewResult.getAuthor());
        holder.content.setText(reviewResult.getContent());

    }

    @Override
    public int getItemCount() {
        if (reviewResults == null) {
            return 0;
        }
        return reviewResults.size();
    }

    public void setMovieResults(List<ReviewResult> movieResults) {
        this.reviewResults = movieResults;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView author, content;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            author = (TextView) itemView.findViewById(R.id.item_review_author);
            content = (TextView) itemView.findViewById(R.id.item_review_content);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
