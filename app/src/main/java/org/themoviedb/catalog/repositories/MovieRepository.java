package org.themoviedb.catalog.repositories;

import org.themoviedb.catalog.db.OwnDatabase;
import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.model.Movie;
import org.themoviedb.catalog.net.MovieApi;
import org.themoviedb.catalog.net.MovieSource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

public class MovieRepository {

    private MovieApi service;
    private OwnDatabase database;

    public MovieRepository(MovieApi service, OwnDatabase database) {
        this.service = service;
        this.database = database;
    }

    public LiveData<Movie> getById(long id) {
        return database.movieDao().getById(id);
    }

    public void saveAll(List<Movie> movies) {
        database.movieDao().saveAll(movies);
    }

    public DataSource.Factory<Integer, Favorite> getFavorites() {
        return database.movieDao().getFavorites();
    }

    public LiveData<Favorite> getFavoriteById(long id) {
        return database.movieDao().getFavoriteById(id);
    }

    public void setFavorite(long movieId) {
        Favorite favorite = Favorite.valueOf(database.movieDao().get(movieId));
        favorite.setFavorite(true);
        database.movieDao().saveFavorite(favorite);
    }

    public void removeFavorite(long movieId) {
        Favorite favorite = database.movieDao().getFavorite(movieId);
        if (favorite != null) {
            database.movieDao().delete(favorite);
        }
    }

    public MovieSource getMovieSource(int pageSize) {
        return new MovieSource(service, pageSize);
    }
}
