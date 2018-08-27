package com.example.swara.udacitypopularmovieapp1.Data;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swara.udacitypopularmovieapp1.Activities.DetailMovieActivity;
import com.example.swara.udacitypopularmovieapp1.Model.Movie;
import com.example.swara.udacitypopularmovieapp1.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private LayoutInflater inflater;

    public MovieRecyclerViewAdapter(Context context, List<Movie>movieList){
        this.context=context;
        this.movieList = movieList;
    }


    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_row, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MovieRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.title.setText(movieList.get(position).getOriginalTitle());
        String vote = Double.toString(movieList.get(position).getVoteAverage());
        holder.userRating.setText(vote);

        //This is how we use Picasso to load images from the internet.
        Picasso.with(context)
                .load(movieList.get(position).getPosterPath())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, userRating;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.movie_title);
            userRating = (TextView) itemView.findViewById(R.id.movie_userRating);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    if(i!= RecyclerView.NO_POSITION) {
                        Movie clickedDataItem = movieList.get(i);
                        Intent intent = new Intent(context,DetailMovieActivity.class);
                        intent.putExtra("original_title",movieList.get(i).getOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(i).getPosterPath());
                        intent.putExtra("overview", movieList.get(i).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(i).getVoteAverage()));
                        intent.putExtra("release_date", movieList.get(i).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(),"Clicked" + clickedDataItem.getOriginalTitle(),Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }
}
