package com.lethalskillzz.blockbusters.blockbusters.data.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.BaseColumns;

import com.lethalskillzz.blockbusters.blockbusters.data.model.MovieResult;

/**
 * Created by ibrahimabdulkadir on 14/05/2017.
 */

public class MovieContract {

    public static final String PROVIDER_AUTHORITY = "com.lethalskillzz.blockbusters.Movies";


    private MovieContract() {}

    public static class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorites";

        public static final String DIR_BASE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PROVIDER_AUTHORITY + "/" + TABLE_NAME;
        public static final String ITEM_BASE_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PROVIDER_AUTHORITY + "/" + TABLE_NAME;


        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";


        public static ContentValues resolveMovie(MovieResult movie) {
            ContentValues values = new ContentValues();

            values.put(COLUMN_POSTER_PATH, movie.getPosterPath());
            values.put(COLUMN_OVERVIEW, movie.getOverview());
            values.put(COLUMN_RELEASE_DATE, movie.getReleaseDate());
            values.put(COLUMN_MOVIE_ID, movie.getId());
            values.put(COLUMN_TITLE, movie.getTitle());
            values.put(COLUMN_BACKDROP_PATH, movie.getBackdropPath());
            values.put(COLUMN_POPULARITY, movie.getPopularity());
            values.put(COLUMN_VOTE_AVERAGE, movie.getVoteAverage());

            return values;
        }
    }
}
