package com.lethalskillzz.blockbusters.blockbusters.presentation.Splash;

import android.os.Handler;

import com.lethalskillzz.blockbusters.mvp.BasePresenter;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public class SplashPresenter extends BasePresenter<SplashMvpContract.View> implements SplashMvpContract.Presenter {

    private static final String TAG = "SplashPresenter";
    // Splash screen timeout
    private static int SPLASH_TIME_OUT = 3000;

    public SplashPresenter() {
    }


    @Override
    public void startSplash() {

        new Handler().postDelayed(new Runnable() {

            //Showing splash screen with a timer
            @Override
            public void run() {
              getView().endSplash();

            }

        }, SPLASH_TIME_OUT);
    }



}
