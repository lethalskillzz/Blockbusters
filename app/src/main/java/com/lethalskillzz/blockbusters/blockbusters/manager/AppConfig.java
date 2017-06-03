package com.lethalskillzz.blockbusters.blockbusters.manager;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public class AppConfig {

    // API URL
    public static String TMDB_API_BASE_URL = "http://api.themoviedb.org/3/";


    // Get popular movies
    // GET /movie/popular
    public static final String GET_POPULAR = "movie/popular";


    // Get top rated movies
    // GET /movie/top_rated
    public static final String GET_TOP_RATED = "movie/top_rated";


    // Get videos
    // GET /movie/{movie_id}/videos
    public static final String GET_VIDEOS = "movie/{movie_id}/videos";


    // Get reviews
    // GET /movie/{movie_id}/reviews
    public static final String GET_REVIEWS = "movie/{movie_id}/reviews";


    // image URL
    public static final String BASE_IMG_URL = "http://image.tmdb.org/t/p/";

    // image params
    public static final String DISC_IMAGE_SIZE = "w185/";
    public static final String DETAIL_IMAGE_SIZE = "w500/";
    public static final String BACKDROP_IMAGE_SIZE = "w500/";


    // API Key param
    public static final String PARAM_TR_API = "?api_key=";


    // Trailers Params
    public static final String PARAM_THUMB = "/default.jpg";


    // YouTube URL
    public static final String VIDEO_URL = "https://www.youtube.com/watch?v=";
    public static final String VIDEO_THUMB_URL = "http://img.youtube.com/vi/";


    // Order Type
    public static final String ORDER_TYPE_KEY = "orderTypeKey";
    public static final String RESULT_KEY = "resultTempKey";
    public static final String FAVOURITE_KEY = "isFavouriteKey";

    public static final String CLICK_GRID = "clickGrid";
}


