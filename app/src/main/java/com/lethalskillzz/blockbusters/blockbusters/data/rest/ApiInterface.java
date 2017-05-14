package com.lethalskillzz.blockbusters.blockbusters.data.rest;

import com.lethalskillzz.blockbusters.blockbusters.data.model.Movie;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Review;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Video;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.GET_POPULAR;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.GET_REVIEWS;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.GET_TOP_RATED;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.GET_VIDEOS;

/**
 * Created by ibrahimabdulkadir on 9/14/2016.
 */
public interface ApiInterface {

    @GET(GET_POPULAR)
    Observable<Movie> getPopular(@Query("api_key") String apiKey);

    @GET(GET_TOP_RATED)
    Observable<Movie> getTopRated(@Query("api_key") String apiKey);

    @GET(GET_VIDEOS)
    Observable<Video> getVideos(@Path("movie_id") String movie_id,
                                 @Query("api_key") String api_key);

    @GET(GET_REVIEWS)
    Observable<Review> getReviews(@Path("movie_id") String movie_id,
                                  @Query("api_key") String api_key);
}
