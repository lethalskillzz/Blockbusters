package com.lethalskillzz.blockbusters.blockbusters.presentation.Details;

import android.content.Context;
import android.util.Log;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Review;
import com.lethalskillzz.blockbusters.blockbusters.data.model.ReviewResult;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Video;
import com.lethalskillzz.blockbusters.blockbusters.data.model.VideoResult;
import com.lethalskillzz.blockbusters.blockbusters.data.rest.ApiClient;
import com.lethalskillzz.blockbusters.blockbusters.data.rest.ApiInterface;
import com.lethalskillzz.blockbusters.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public class DetailsPresenter extends BasePresenter<DetailsMvpContract.View> implements DetailsMvpContract.Presenter{

    private static final String TAG = "DetailsPresenter";

    private List<VideoResult> videoResults;
    private List<ReviewResult> reviewResults;
    private Context context;

    public DetailsPresenter(Context context) {
        this.context = context;
        videoResults = new ArrayList<>();
    }


    @Override
    public void getVideos(String movieId) {

        checkViewAttached();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Observable<Video> call;
        call = apiService.getVideos(movieId, context.getString(R.string.tmdb_api_key));

        addSubscription(call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Video>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {

                            HttpException response = (HttpException) e;
                            Log.e(TAG, "Response Code: " + response.code());
                            Log.e(TAG, "Response Message: " + response.message());

                            getView().showError(response.code()+" "+response.message());

                        }else {
                            getView().showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Video video) {
                        videoResults = video.getVideoResults();
                        getView().showVideos(videoResults);

                    }

                })
        );
    }


    @Override
    public void getReviews(String movieId) {

        checkViewAttached();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Observable<Review> call;
        call = apiService.getReviews(movieId, context.getString(R.string.tmdb_api_key));

        addSubscription(call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Review>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {

                            HttpException response = (HttpException) e;
                            Log.e(TAG, "Response Code: " + response.code());
                            Log.e(TAG, "Response Message: " + response.message());

                            getView().showError(response.code()+" "+response.message());

                        }else {
                            getView().showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Review review) {
                        reviewResults = review.getReviewResults();
                        getView().showReviews(reviewResults);

                    }

                })
        );

    }

}
