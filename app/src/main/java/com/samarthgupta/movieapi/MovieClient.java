package com.samarthgupta.movieapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by samarthgupta on 20/03/17.
 */

public interface MovieClient {

   @GET("/3/search/movie")
   Call<Movie> getMovieDetails(@Query("api_key") String api, @Query("query") String moviename);

   @GET("/3/movie/top_rated")
   Call<Movie> getTopRatedMovies(@Query("api_key") String api, @Query("language") String language,
                                      @Query("page") Integer page);


}
