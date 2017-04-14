package com.lethalskillzz.blockbusters.blockbusters.presentation.Details;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Result;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.BASE_IMG_URL;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.CLICK_GRID;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.DETAIL_IMAGE_SIZE;

public class DetailsActivity extends AppCompatActivity implements DetailsMvpContract.View{

    private static final String TAG = "DetailsActivity";
    DetailsMvpContract.Presenter presenter;

    private ActionBar mActionBar;

    @Bind(R.id.details_toolbar)
    Toolbar toolbar;
    @Bind(R.id.details_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.details_title)
    TextView mTitle;
    @Bind(R.id.details_date)
    TextView mDate;
    @Bind(R.id.details_vote)
    TextView mRating;
    @Bind(R.id.details_plot)
    TextView mPlot;
    @Bind(R.id.details_image)
    ImageView mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();

        presenter = new DetailsPresenter();
        presenter.attachView(this);


        Result result = getIntent().getParcelableExtra(CLICK_GRID);

        mTitle.setText(result.getTitle());
        mRating.setText(String.valueOf(result.getVoteAverage()));
        mDate.setText(result.getReleaseDate());
        mPlot.setText(result.getOverview());
        Glide.with(this)
                .load(BASE_IMG_URL+DETAIL_IMAGE_SIZE+result.getPosterPath())
                .into(mImage);

    }
}
