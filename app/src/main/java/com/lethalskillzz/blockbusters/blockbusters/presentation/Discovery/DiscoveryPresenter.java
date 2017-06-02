package com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery;

import android.content.Context;
import android.util.Log;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Movie;
import com.lethalskillzz.blockbusters.blockbusters.data.model.MovieResult;
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



public class DiscoveryPresenter extends BasePresenter<DiscoveryMvpContract.View> implements DiscoveryMvpContract.Presenter {

    private static final String TAG = "DiscoveryPresenter";
    private List<MovieResult> movieResults;
    private Context context;

    public DiscoveryPresenter(Context context) {
        this.context = context;
        movieResults = new ArrayList<>();
    }

    @Override
    public void getPage(String mOrderType) {

        checkViewAttached();
        getView().showLoading();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Observable<Movie> call;
        if(mOrderType.equals("popularity")) {
            call = apiService.getPopular(context.getString(R.string.tmdb_api_key));
        } else {
            call = apiService.getTopRated(context.getString(R.string.tmdb_api_key));
        }
        addSubscription(call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {

                        getView().hideLoading();

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        getView().hideLoading();

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
                    public void onNext(Movie movie) {
                        movieResults = movie.getMovieResults();
                        getView().hideLoading();
                        getView().showResults(movieResults);

                    }

                })
        );

    }

}



