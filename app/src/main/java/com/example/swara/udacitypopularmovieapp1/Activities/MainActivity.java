package com.example.swara.udacitypopularmovieapp1.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.swara.udacitypopularmovieapp1.BuildConfig;
import com.example.swara.udacitypopularmovieapp1.Data.MovieRecyclerViewAdapter;
import com.example.swara.udacitypopularmovieapp1.Model.Movie;
import com.example.swara.udacitypopularmovieapp1.Model.MovieResponse;
import com.example.swara.udacitypopularmovieapp1.R;
import com.example.swara.udacitypopularmovieapp1.Util.Client;
import com.example.swara.udacitypopularmovieapp1.Util.Service;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private RecyclerView mRecyclerView;
    private MovieRecyclerViewAdapter mAdapter;
    private List<Movie> movieList;
    public static final String LOG_TAG= MovieRecyclerViewAdapter.class.getName();
    public static final String API_KEY = BuildConfig.ApiKey;
    public static final String popular_movie = "https://api.themoviedb.org/3/movie/popular?api_key=API_KEY&language=en-US&page=1";
    public static final String topRated_movie = "https://api.themoviedb.org/3/movie/top_rated?api_key=API_KEY&language=en-US&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        mAdapter = new MovieRecyclerViewAdapter(this,movieList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        loadJSON();
        loadJSON1();
    }
        private void loadJSON(){

            try {
                if (API_KEY.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please obtain own API KEY ", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    Client client = new Client();
                    Service service = client.getClient().create(Service.class);
                    Call<MovieResponse> call = service.getPopularMovies(popular_movie);
                    call.enqueue(new Callback<MovieResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<MovieResponse> call,@NonNull Response<MovieResponse> response) {
                            List<Movie> movies = response.body().getResults();
                            mRecyclerView.setAdapter(new MovieRecyclerViewAdapter(getApplicationContext(), movies));

                        }

                        @Override
                        public void onFailure(Call<MovieResponse> call, Throwable t) {
                            Log.d("Error", t.getMessage());
                            Toast.makeText(MainActivity.this, "Error to fetch data", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            } catch (Exception e) {
                Log.d("Error", e.getMessage());
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }

        }

    private void loadJSON1(){

        try {
            if (API_KEY.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain own API KEY ", Toast.LENGTH_SHORT).show();
                return;
            }else {

                Client client = new Client();
                Service service = client.getClient().create(Service.class);
                Call<MovieResponse> call = service.getTopRatedMovies(topRated_movie);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call,@NonNull Response<MovieResponse> response) {
                       List<Movie> movies = response.body().getResults();
                        mRecyclerView.setAdapter(new MovieRecyclerViewAdapter(getApplicationContext(), movies));

                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(MainActivity.this, "Error to fetch data", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }



    @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()) {
                case R.id.action_settings:
                    Intent intent = new Intent(this, SettingsActivity.class);
                    startActivity(intent);
                    return true;

                default:

                    return super.onOptionsItemSelected(item);
            }
        }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(LOG_TAG,"updated preference");
        sortOrder();

    }
    private void sortOrder(){
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    String sortOrder = preferences.getString(this.getString(R.string.popular_movies),
            this.getString(R.string.top_rated));
    if(sortOrder.equals(this.getString(R.string.popular_movies))){
        Log.d(LOG_TAG,"sort_by_popularity");
        loadJSON();
    }else if(sortOrder.equals(this.getString(R.string.top_rated))){
        Log.d(LOG_TAG,"sort_by_top_rated");
        loadJSON1();
    }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(movieList.isEmpty()){
            sortOrder();
        }
    }
}
