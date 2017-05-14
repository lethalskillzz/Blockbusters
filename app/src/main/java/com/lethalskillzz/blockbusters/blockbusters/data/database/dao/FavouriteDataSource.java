package com.lethalskillzz.blockbusters.blockbusters.data.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lethalskillzz.blockbusters.blockbusters.data.database.DatabaseHelper;
import com.lethalskillzz.blockbusters.blockbusters.data.database.MovieContract.FavoriteEntry;


/**
 * Created by ibrahimabdulkadir on 13/05/2017.
 */

public class FavouriteDataSource {


    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { FavoriteEntry._ID, FavoriteEntry.COLUMN_MOVIE_ID,
            FavoriteEntry.COLUMN_TITLE, FavoriteEntry.COLUMN_POSTER_PATH,
            FavoriteEntry.COLUMN_SYNOPSIS, FavoriteEntry.COLUMN_RATING,
            FavoriteEntry.COLUMN_POPULARITY, FavoriteEntry.COLUMN_DATE};


    public FavouriteDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }


    public void close() {
        dbHelper.close();
    }


    /**
     * Check if Favourites are empty
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
     * Creating new Images
     */
    public Image createImage(Image image) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ID, image.getId());
        values.put(DatabaseHelper.COLUMN_TITLE, image.getTitle());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, image.getDescription());
        values.put(DatabaseHelper.COLUMN_DATE_TIME, image.getDatetime());
        values.put(DatabaseHelper.COLUMN_COVER, image.getCover());
        values.put(DatabaseHelper.COLUMN_UPS, image.getUps());
        values.put(DatabaseHelper.COLUMN_DOWNS, image.getDowns());
        values.put(DatabaseHelper.COLUMN_SCORE, image.getScore());
        values.put(DatabaseHelper.COLUMN_IS_ALBUM, image.getIsAlbum());

        // insert row
        database.insert(DatabaseHelper.TABLE_IMAGE, DatabaseHelper.COLUMN_ID, values);

        return image;
    }


    /**
     * Read All Images
     */
    public List<Image> readAllImages() {

        String whereClause = null;
        String[] whereArgs = null;
        List<Image> images = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_IMAGE,
                allColumns, whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Image image = cursorToImage(cursor);
            images.add(image);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return images;
    }


    /**
     * Read Image by Id
     */
    public Image readImageById(String id) {

        String whereClause = DatabaseHelper.COLUMN_ID + " LIKE ?";
        String [] whereArgs = {"%"+id+"%"};
        Image image = new Image();

        Cursor cursor = database.query(DatabaseHelper.TABLE_IMAGE,
                allColumns,whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            image = cursorToImage(cursor);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return image;
    }



    /**
     * Delete Image
     */
    public long deleteImage(Image image) {
        String id = image.getId();
        return database.delete(DatabaseHelper.TABLE_IMAGE, DatabaseHelper.COLUMN_ID
                + " = " + "'" + id + "'", null);
    }


    private Image cursorToImage(Cursor cursor) {
        Image image = new Image();
        image.setId(cursor.getString(0));
        image.setTitle(cursor.getString(1));
        image.setDescription(cursor.getString(2));
        image.setDatetime(cursor.getString(3));
        image.setCover(cursor.getString(4));
        image.setUps(cursor.getInt(5));
        image.setDowns(cursor.getInt(6));
        image.setScore(cursor.getInt(7));
        image.setIsAlbum(cursor.getInt(8)==1);

        return image;
    }
}
