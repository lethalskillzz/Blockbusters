package com.lethalskillzz.blockbusters.blockbusters.presentation.Details;

import com.lethalskillzz.blockbusters.blockbusters.data.model.ReviewResult;
import com.lethalskillzz.blockbusters.blockbusters.data.model.VideoResult;
import com.lethalskillzz.blockbusters.mvp.Mvp;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public interface DetailsMvpContract {

    public interface View extends Mvp.View {

        void showVideos(List<VideoResult> videoResults);

        void showReviews(List<ReviewResult> reviewResults);

        void showError(String error);
    }

    public interface Presenter extends Mvp.Presenter<View> {

        void getVideos(String movieId);

        void getReviews(String movieId);
    }
}
