package com.lethalskillzz.blockbusters.blockbusters.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ibrahimabdulkadir on 13/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    //TABLE
    public static final String TABLE_FAVOURITES = "favourites";


    //COLUMN
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE_TIME = "dateTime";
    public static final String COLUMN_COVER = "cover";
    public static final String COLUMN_UPS = "ups";
    public static final String COLUMN_DOWNS = "downs";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_IS_ALBUM = "isAlbum";

    public static final String DATABASE_NAME = "blockbusters.db";
    private static final int DATABASE_VERSION = 1;


    //Database creation sql statement
    private static final String DATABASE_CREATE_FAVOURITES = "CREATE TABLE " + TABLE_FAVOURITES + "( _id INTEGER PRIMARY KEY, "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL, " + COLUMN_TITLE + " TEXT NOT NULL, " + COLUMN_DESCRIPTION + " TEXT, "
            + COLUMN_DATE_TIME + " TEXT NOT NULL, " + COLUMN_COVER + " TEXT, " + COLUMN_UPS + " INTEGER NOT NULL, "
            + COLUMN_DOWNS + " INTEGER NOT NULL, " + COLUMN_SCORE + " INTEGER NOT NULL, " + COLUMN_IS_ALBUM + " INTEGER NOT NULL );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_FAVOURITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITES);
        onCreate(db);
    }

}
