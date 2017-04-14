package com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery;

import com.lethalskillzz.blockbusters.blockbusters.data.model.Result;
import com.lethalskillzz.blockbusters.mvp.Mvp;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public interface DiscoveryMvpContract {

    public interface View extends Mvp.View {

        void showResults(List<Result> results);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    public interface Presenter extends Mvp.Presenter<DiscoveryMvpContract.View> {
        void getPage(String mOrderType);
    }

}
