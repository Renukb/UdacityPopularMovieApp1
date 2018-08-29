package com.example.swara.udacitypopularmovieapp1.Util;

import com.example.swara.udacitypopularmovieapp1.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/popular")
   Call<MovieResponse> getPopularMovies(@Query("api_key") String api_Key);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_Key);

    //referance from https://medium.com/@prakash_pun/retrofit-a-simple-android-tutorial-48437e4e5a23
}
