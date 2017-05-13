package com.lethalskillzz.blockbusters.blockbusters.presentation.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.BASE_IMG_URL;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.CLICK_GRID;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.DETAIL_IMAGE_SIZE;

public class DetailsActivity extends AppCompatActivity implements DetailsMvpContract.View{

    private static final String TAG = "DetailsActivity";
    DetailsMvpContract.Presenter presenter;


    @BindView(R.id.details_title)
    TextView mTitle;
    @BindView(R.id.details_date)
    TextView mDate;
    @BindView(R.id.details_vote)
    TextView mRating;
    @BindView(R.id.details_plot)
    TextView mPlot;
    @BindView(R.id.details_image)
    ImageView mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        presenter = new DetailsPresenter();
        presenter.attachView(this);


        Result result = getIntent().getParcelableExtra(CLICK_GRID);

        mTitle.setText(result.getTitle());
        mRating.setText(String.valueOf(result.getVoteAverage())+getString(R.string.full_rating));
        mDate.setText(result.getReleaseDate());
        mPlot.setText(result.getOverview());
        Glide.with(this)
                .load(BASE_IMG_URL+DETAIL_IMAGE_SIZE+result.getPosterPath())
                .into(mImage);

    }
}
