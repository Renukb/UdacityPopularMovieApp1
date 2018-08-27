package com.example.swara.udacitypopularmovieapp1.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie{

    @SerializedName("original_title")
        private String originalTitle;
        @SerializedName("title")
        private String title;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("overview")
        private String overview;
        @SerializedName("id")
        private Integer id;
        @SerializedName("popularity")
        private Double popularity;
        @SerializedName("vote_average")
        private Double voteAverage;

        public Movie(String originalTitle, String title,String releaseDate,String posterPath,String overview,
                     Integer id,Double popularity,Double voteAverage){
            this.originalTitle=originalTitle;
            this.title=title;
            this.releaseDate=releaseDate;
            this.posterPath=posterPath;
            this.overview =overview;
            this.id=id;
            this.popularity=popularity;
            this.voteAverage=voteAverage;

        }
        String baseImageUrl ="http://image.tmdb.org/t/p/w500";
    public String getPosterPath() {
        return "http://image.tmdb.org/t/p/w500" + posterPath;
    }
    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }


    public String getOriginalTitle(){
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle){
        this.originalTitle=originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title)  {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public Integer getId(){return id;}

    public void setId(int id){this.id = id;}

    public Double getPopularity(){return popularity;}

    public void setPopularity(Double popularity){this.popularity = popularity;}

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage){
        this.voteAverage = voteAverage;
    }

}
