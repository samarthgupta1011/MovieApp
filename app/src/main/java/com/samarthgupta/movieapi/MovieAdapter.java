package com.samarthgupta.movieapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samarthgupta on 25/03/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

   Movie movie;

    public MovieAdapter(Movie movie) {
        this.movie = movie;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card,parent,false);
        MovieHolder holder = new MovieHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

       List<Result> results = movie.getResults();
        holder.tv_title.setText(results.get(position).getTitle());
        holder.tv_date.setText(results.get(position).getReleaseDate());

        String image_url = "https://image.tmdb.org/t/p/w154/"+results.get(position).getPosterPath();
        Picasso.with(GlobalClass.context).load(image_url).into(holder.iv_poster);


    }

    @Override
    public int getItemCount() {

        List<Result> results = movie.getResults();
        return results.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView iv_poster; TextView tv_title,tv_date;

        public MovieHolder(View itemView) {
            super(itemView);
            iv_poster = (ImageView) itemView.findViewById(R.id.iv_poster);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date= (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
