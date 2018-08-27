package com.example.swara.udacitypopularmovieapp1.Activities;

import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
        private void loadJSON(){

            try {
                if (BuildConfig.ApiKey.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please obtain own API KEY ", Toast.LENGTH_SHORT).show();
                    return;
                }

                Client client = new Client();
                Service service = client.getClient().create(Service.class);
                Call<MovieResponse> call = service.getPopularMovies(BuildConfig.ApiKey);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        List<Movie> movies = response.body().getResults();
                        mRecyclerView.setAdapter(new MovieRecyclerViewAdapter(getApplicationContext(), movies));

                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(MainActivity.this, "Error to fetch data", Toast.LENGTH_SHORT).show();

                    }
                });
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
                    return true;

                default:

                    return super.onOptionsItemSelected(item);
            }
        }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }




}
