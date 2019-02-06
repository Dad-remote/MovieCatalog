package org.themoviedb.catalog.net.response;

import com.google.gson.annotations.SerializedName;

import org.themoviedb.catalog.model.Movie;

import java.util.Arrays;
import java.util.List;

public class MovieResponse {

    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    private Movie[] results;

    public List<Movie> getMovies() {
        return Arrays.asList(results);
    }
}
