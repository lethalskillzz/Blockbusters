package com.lethalskillzz.blockbusters.blockbusters.presentation.Details;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.database.dao.MovieDataSource;
import com.lethalskillzz.blockbusters.blockbusters.data.model.MovieResult;
import com.lethalskillzz.blockbusters.blockbusters.data.model.ReviewResult;
import com.lethalskillzz.blockbusters.blockbusters.data.model.VideoResult;
import com.lethalskillzz.blockbusters.blockbusters.util.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.BACKDROP_IMAGE_SIZE;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.BASE_IMG_URL;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.CLICK_GRID;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.DETAIL_IMAGE_SIZE;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.FAVOURITE_KEY;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.RESULT_KEY;

public class DetailsActivity extends AppCompatActivity implements DetailsMvpContract.View{

    private static final String TAG = "DetailsActivity";
    private DetailsMvpContract.Presenter presenter;

    private ConnectionDetector cd;
    private MovieDataSource movieDataSource;
    private MovieResult movieResult;
    private VideoAdapter videoAdapter;
    private ReviewAdapter reviewAdapter;

    private List<VideoResult> videoResults;
    private List<ReviewResult> reviewResults;

    private boolean isFavourite;

    @BindView(R.id.details_coordinator_layout)
    private CoordinatorLayout coordinatorLayout;
    @BindView(R.id.details_toolbar)
    private Toolbar mToolbar;
    @BindView(R.id.details_date)
    private TextView mDate;
    @BindView(R.id.details_vote)
    private TextView mRating;
    @BindView(R.id.details_plot)
    private TextView mPlot;
    @BindView(R.id.details_image)
    private ImageView mImage;
    @BindView(R.id.details_backdrop)
    private ImageView mBackdrop;
    @BindView(R.id.details_videos_recycler_view)
    private RecyclerView videosRecyclerView;
    @BindView(R.id.details_reviews_recycler_view)
    private RecyclerView reviewRecyclerView;
    @BindView(R.id.details_favourite)
    private ImageView favouriteClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter = new DetailsPresenter(this);
        presenter.attachView(this);

        cd = new ConnectionDetector(this);
        movieDataSource = new MovieDataSource(this);
        videoResults = new ArrayList<>();
        reviewResults = new ArrayList<>();

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(FAVOURITE_KEY)) {
                isFavourite = savedInstanceState.getBoolean(FAVOURITE_KEY);
            }
            if (savedInstanceState.containsKey(RESULT_KEY)) {
                movieResult = savedInstanceState.getParcelable(RESULT_KEY);
            }
        } else {
            isFavourite = getIntent().getBooleanExtra(CLICK_GRID, false);
            movieResult = getIntent().getParcelableExtra(CLICK_GRID);
        }


        videoAdapter = new VideoAdapter(this);
        reviewAdapter = new ReviewAdapter(this);

        setupViews();

        if (savedInstanceState != null) {

        } else {
            if (cd.isConnectingToInternet()) {

                presenter.getVideos(String.valueOf(movieResult.getId()));
                presenter.getReviews(String.valueOf(movieResult.getId()));

            } else {
                showError(getString(R.string.error_no_internet));
            }
        }

        getSupportActionBar().setTitle(movieResult.getTitle());


        mRating.setText(String.valueOf(movieResult.getVoteAverage())+getString(R.string.full_rating));
        mDate.setText(movieResult.getReleaseDate());
        mPlot.setText(movieResult.getOverview());

        Picasso.with(this).
                load(BASE_IMG_URL+DETAIL_IMAGE_SIZE+ movieResult.getPosterPath())
                .into(mImage);

        Picasso.with(this).
                load(BASE_IMG_URL+BACKDROP_IMAGE_SIZE+ movieResult.getBackdropPath())
                .placeholder(R.mipmap.no_poster).into(mBackdrop);
    }


    @OnClick(R.id.details_favourite)
    public void favourite(View view) {

        movieDataSource.open();
        movieDataSource.deleteMovie(movieResult);
        movieDataSource.createMovies(movieResult);
        movieDataSource.close();

    }


    private  void setupViews() {

        LinearLayoutManager videosLinearLayoutManager  = new LinearLayoutManager(this);
        videosRecyclerView.setLayoutManager(videosLinearLayoutManager);
        videosRecyclerView.setHasFixedSize(true);
        videosRecyclerView.setAdapter(videoAdapter);

        LinearLayoutManager reviewsLinearLayoutManager  = new LinearLayoutManager(this);
        reviewRecyclerView.setLayoutManager(reviewsLinearLayoutManager);
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.setAdapter(reviewAdapter);

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (movieResult != null) {
            outState.putBoolean(FAVOURITE_KEY, isFavourite);
            outState.putParcelable(RESULT_KEY, movieResult);
        }
    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }



    @Override
    public void showVideos(List<VideoResult> videoResults) {
        this.videoResults = videoResults;
        videoAdapter.setMovieResults(videoResults);
    }

    @Override
    public void showReviews(List<ReviewResult> reviewResults) {
        this.reviewResults = reviewResults;
        reviewAdapter.setMovieResults(reviewResults);
    }


    @Override
    public void showError(String msg) {

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);

        // Changing message text color
        snackbar.setActionTextColor(Color.RED);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

}
