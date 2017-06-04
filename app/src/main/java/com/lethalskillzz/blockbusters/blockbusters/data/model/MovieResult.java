package com.lethalskillzz.blockbusters.blockbusters.data.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lethalskillzz.blockbusters.blockbusters.data.database.MovieContract;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 12/04/2017.
 */

public class MovieResult implements Parcelable {

    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel in) {
            return new MovieResult(in);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;


    public MovieResult() {}

    public MovieResult(String posterPath, String overview, String releaseDate, int id, String title,
                       String backdropPath, double popularity, double voteAverage) {

        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
    }

    public static MovieResult buildResult(Cursor cursor) {
        return new MovieResult(
                cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_POSTER_PATH)),
                cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_RELEASE_DATE)),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_MOVIE_ID))),
                cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_BACKDROP_PATH)),
                Double.valueOf(cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_POPULARITY))),
                Double.valueOf(cursor.getString(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_VOTE_AVERAGE)))
        );
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    protected MovieResult(Parcel in) {
        overview = in.readString();
        releaseDate = in.readString();
        id = in.readInt();
        title = in.readString();
        voteAverage = in.readDouble();
        posterPath = in.readString();
        backdropPath = in.readString();
        popularity = in.readDouble();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeDouble(voteAverage);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeDouble(popularity);
    }


}
