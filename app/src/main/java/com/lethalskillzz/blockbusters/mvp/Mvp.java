package com.lethalskillzz.blockbusters.mvp;

/**
 * Created by ibrahimabdulkadir on 11/04/2017.
 */

public class Mvp {

    public interface View {
    }

    public interface Presenter<V extends Mvp.View> {
        void attachView(V view);

        void detachView();
    }
}
