package com.lethalskillzz.blockbusters.blockbusters.data.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lethalskillzz.blockbusters.blockbusters.data.database.DatabaseHelper;
import com.lethalskillzz.blockbusters.blockbusters.data.database.MovieContract.FavoriteEntry;
import com.lethalskillzz.blockbusters.blockbusters.data.model.MovieResult;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ibrahimabdulkadir on 13/05/2017.
 */

public class MovieDataSource {


    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {FavoriteEntry.COLUMN_POSTER_PATH,
            FavoriteEntry.COLUMN_OVERVIEW, FavoriteEntry.COLUMN_RELEASE_DATE,
            FavoriteEntry.COLUMN_MOVIE_ID, FavoriteEntry.COLUMN_TITLE,
            FavoriteEntry.COLUMN_POSTER_PATH, FavoriteEntry.COLUMN_POPULARITY,
            FavoriteEntry.COLUMN_VOTE_AVERAGE};


    public MovieDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }


    public void close() {
        dbHelper.close();
    }


    /**
     * Check if Movies are empty
     */
    public boolean isFavouriteEmpty() {

        boolean isEmpty = true;
        Cursor cursor = database.query(FavoriteEntry.TABLE_NAME,
                allColumns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            isEmpty = false;
        }
        cursor.close();

        return isEmpty;
    }



    /**
     * Creating new Movies
     */
    public MovieResult createMovies(MovieResult movieResult) {

        ContentValues values = new ContentValues();
        values.put(FavoriteEntry.COLUMN_POSTER_PATH, movieResult.getPosterPath());
        values.put(FavoriteEntry.COLUMN_OVERVIEW, movieResult.getOverview());
        values.put(FavoriteEntry.COLUMN_RELEASE_DATE, movieResult.getReleaseDate());
        values.put(FavoriteEntry.COLUMN_MOVIE_ID, movieResult.getId());
        values.put(FavoriteEntry.COLUMN_TITLE, movieResult.getTitle());
        values.put(FavoriteEntry.COLUMN_BACKDROP_PATH, movieResult.getBackdropPath());
        values.put(FavoriteEntry.COLUMN_POPULARITY, movieResult.getPopularity());
        values.put(FavoriteEntry.COLUMN_VOTE_AVERAGE, movieResult.getVoteAverage());

        // insert row
        database.insert(FavoriteEntry.TABLE_NAME, FavoriteEntry.COLUMN_MOVIE_ID, values);

        return movieResult;
    }


    /**
     * Read All Movies
     */
    public List<MovieResult> readAllMovies() {

        String whereClause = null;
        String[] whereArgs = null;
        List<MovieResult> movieResults = new ArrayList<>();

        Cursor cursor = database.query(FavoriteEntry.TABLE_NAME,
                allColumns, whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MovieResult movieResult = cursorToMovies(cursor);
            movieResults.add(movieResult);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return movieResults;
    }


    /**
     * Read Movie by Id
     */
    public MovieResult readMovieById(String id) {

        String whereClause = FavoriteEntry.COLUMN_MOVIE_ID + " LIKE ?";
        String [] whereArgs = {"%"+id+"%"};
        MovieResult movieResult = new MovieResult();

        Cursor cursor = database.query(FavoriteEntry.TABLE_NAME,
                allColumns,whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            movieResult = cursorToMovies(cursor);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return movieResult;
    }


    /**
     * Read Movie by Id
     */
    public boolean isFavourite(String id) {

        String whereClause = FavoriteEntry.COLUMN_MOVIE_ID + " LIKE ?";
        String [] whereArgs = {"%"+id+"%"};

        Cursor cursor = database.query(FavoriteEntry.TABLE_NAME,
                allColumns,whereClause, whereArgs, null, null, null);

        boolean isFavourite = (cursor.getCount() > 0);
        // make sure to close the cursor
        cursor.close();
        return isFavourite;
    }


    /**
     * Delete Movie
     */
    public long deleteMovie(MovieResult movieResult) {
        String id = String.valueOf(movieResult.getId());
        return database.delete(FavoriteEntry.TABLE_NAME, FavoriteEntry.COLUMN_MOVIE_ID
                + " = " + "'" + id + "'", null);
    }


    private MovieResult cursorToMovies(Cursor cursor) {

        MovieResult movieResult = new MovieResult();
        movieResult.setPosterPath(cursor.getString(0));
        movieResult.setOverview(cursor.getString(1));
        movieResult.setReleaseDate(cursor.getString(2));
        movieResult.setId(cursor.getInt(3));
        movieResult.setTitle(cursor.getString(4));
        movieResult.setBackdropPath(cursor.getString(5));
        movieResult.setPopularity(cursor.getDouble(6));
        movieResult.setVoteAverage(cursor.getDouble(7));

        return movieResult;
    }
}
