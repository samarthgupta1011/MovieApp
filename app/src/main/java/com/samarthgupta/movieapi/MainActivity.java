package com.samarthgupta.movieapi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Movie movie; int status=-10;
    List<Result> results;
    String BASE__URL= "https://api.themoviedb.org/";
    String API_KEY = "ee5b18c57bdf35ee47dbe14e65578d61";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For SETTING CONTEXT TO USE PICASSO
        GlobalClass.context=this;

        //Retrofit
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE__URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MovieClient client = retrofit.create(MovieClient.class);

        int i=1;
        Call<Movie> callTopRated = client.getTopRatedMovies(API_KEY,"en-US",i);
        callTopRated.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie=response.body();
                //RecyclerView
                recyclerView = (RecyclerView) findViewById(R.id.recycler);
                adapter = new MovieAdapter(movie);
                layoutManager = new GridLayoutManager(getApplicationContext(),2);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);


            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });












     /*   Call<Movie> call = client.getMovieDetails(API_KEY,"Doctor Strange");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie = response.body();
                status = response.code();
                Log.i("TAG","Success thod sa");
                Log.i("TAG", String.valueOf(movie.getPage()));
                results = movie.getResults();
                Result res = results.get(0);
                Log.i("TAG", res.getOverview());



            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

                Log.i("TAG","I AM DONE  "+t.toString() );

            }
        });

        Log.i("TAG", String.valueOf(status)); */

    }
}
