package com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery;

import android.util.Log;

import com.lethalskillzz.blockbusters.blockbusters.data.model.Page;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Result;
import com.lethalskillzz.blockbusters.blockbusters.data.rest.ApiClient;
import com.lethalskillzz.blockbusters.blockbusters.data.rest.ApiInterface;
import com.lethalskillzz.blockbusters.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.API_KEY;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */



public class DiscoveryPresenter extends BasePresenter<DiscoveryMvpContract.View> implements DiscoveryMvpContract.Presenter {

    private static final String TAG = "DiscoveryPresenter";
    private List<Result> results;

    public DiscoveryPresenter() {
        results = new ArrayList<>();
    }


    @Override
    public void getPage() {

        checkViewAttached();
        getView().showLoading();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Observable<Page> call = apiService.getPopular(API_KEY);
        Subscription subscription = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Page>() {
                    @Override
                    public void onCompleted() {

                        getView().hideLoading();
                        Log.e(TAG, "size: " + results.size());

                    }

                    @Override
                    public void onError(Throwable e) {

                        getView().hideLoading();

                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            Log.e(TAG, "Response Code: " + response.code());
                            Log.e(TAG, "Response Message: " + response.message());

                        }
                    }

                    @Override
                    public void onNext(Page page) {
                        results = page.getResults();
                        getView().hideLoading();
                        getView().showResults(results);

                        Log.e(TAG, "size: " + results.size());

                    }

                });

                addSubscription(subscription);

    }


}