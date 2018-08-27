package com.example.swara.udacitypopularmovieapp1.Util;

import com.example.swara.udacitypopularmovieapp1.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Service {
    @GET("movie/popular")
    public Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey) {
        return null;
    }

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey) {
        return null;
    }
}
