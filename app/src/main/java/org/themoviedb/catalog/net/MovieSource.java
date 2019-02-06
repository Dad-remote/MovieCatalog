package org.themoviedb.catalog.net;

import android.util.Log;

import org.themoviedb.catalog.model.Movie;
import org.themoviedb.catalog.net.response.MovieResponse;
import org.themoviedb.catalog.repositories.RepositoryFactory;
import org.themoviedb.catalog.util.Logic;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSource extends PositionalDataSource<Movie> {

    private static final String TAG = MovieSource.class.getSimpleName();

    private final MovieApi service;
    private final int pageSize;

    public MovieSource(MovieApi service, int pageSize) {
        this.service = service;
        this.pageSize = pageSize;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<Movie> callback) {
        loadMovies(getPageSize(params.requestedStartPosition), (movies) -> callback.onResult(movies, 0));
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Movie> callback) {
        loadMovies(getPageSize(params.startPosition), callback::onResult);
    }

    private int getPageSize(int startPosition) {
        return startPosition / pageSize + 1;
    }

    private void loadMovies(int page, NetCallback callback) {
        service.getPopularMovies(page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse body = response.body();
                if (body != null) {
                    List<Movie> movies = body.getMovies();
                    Logic.background(() -> RepositoryFactory.getInstance().getMovieRepository().saveAll(movies));
                    callback.onItemsArrived(movies);
                } else {
                    callback.onItemsArrived(new ArrayList<>(0));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, "onFailure", throwable);
                callback.onItemsArrived(new ArrayList<>(0));
            }
        });
    }

    interface NetCallback {
        void onItemsArrived(List<Movie> items);
    }
}
