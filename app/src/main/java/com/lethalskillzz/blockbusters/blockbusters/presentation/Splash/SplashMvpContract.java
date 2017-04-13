package com.lethalskillzz.blockbusters.blockbusters.presentation.Splash;

import com.lethalskillzz.blockbusters.mvp.Mvp;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public class SplashMvpContract {

    public interface View extends Mvp.View {

        void endSplash();
    }

    public interface Presenter extends Mvp.Presenter<SplashMvpContract.View> {
        void startSplash();
    }
}
