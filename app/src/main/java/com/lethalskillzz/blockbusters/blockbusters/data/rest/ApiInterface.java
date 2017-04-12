package com.lethalskillzz.blockbusters.blockbusters.data.rest;

import com.lethalskillzz.blockbusters.blockbusters.data.model.Page;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.GET_POPULAR;
import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.GET_TOP_RATED;

/**
 * Created by ibrahimabdulkadir on 9/14/2016.
 */
public interface ApiInterface {

    @GET(GET_POPULAR)
    Observable<Page> getPopular(@Query("api_key") String apiKey);

    @GET(GET_TOP_RATED)
    Observable<Page> getTopRated(@Query("api_key") String apiKey);
}
