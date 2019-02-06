package org.themoviedb.catalog.net;

import org.themoviedb.catalog.net.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    String ROOT = "https://api.themoviedb.org/";

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=b66ffea8276ce576d60df52600822c88")
    Call<MovieResponse> getPopularMovies(@Query("page") int page);
}
