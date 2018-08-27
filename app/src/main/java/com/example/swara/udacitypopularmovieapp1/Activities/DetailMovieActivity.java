package com.example.swara.udacitypopularmovieapp1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.swara.udacitypopularmovieapp1.R;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {
    TextView movieName,userRating,releaseDate;
    ImageView mImageView;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detail_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageView = (ImageView)findViewById(R.id.imageView);
        movieName = (TextView) findViewById(R.id.movie_title);
        userRating = (TextView)findViewById(R.id.movie_userRating);
        releaseDate = (TextView) findViewById(R.id.movie_releaseDate);

        Intent intentStartThisAct= getIntent();

        if(intentStartThisAct.hasExtra("original_title")){

            String imageView = getIntent().getExtras().getString("poster_path");
            String mName = getIntent().getExtras().getString("original_title");
            String mRating = getIntent().getExtras().getString("user_rating");
            String mReleaseDate = getIntent().getExtras().getString("release_date");

            Picasso.with(this)
                    .load(imageView)
                    .into(mImageView);

            movieName.setText(mName);
            userRating.setText(mRating);
            releaseDate.setText(mReleaseDate);


        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }


    }
}
