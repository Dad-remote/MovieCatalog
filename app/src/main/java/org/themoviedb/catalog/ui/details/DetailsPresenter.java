package org.themoviedb.catalog.ui.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.model.Movie;
import org.themoviedb.catalog.repositories.MovieRepository;
import org.themoviedb.catalog.repositories.RepositoryFactory;
import org.themoviedb.catalog.util.Logic;

import androidx.lifecycle.LiveData;

@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    public void loadMovie(long movieId) {
        MovieRepository repository = RepositoryFactory.getInstance().getMovieRepository();
        LiveData<Movie> movie = repository.getById(movieId);
        getViewState().showMovieDetails(movie);

        LiveData<Favorite> favorite = repository.getFavoriteById(movieId);
        getViewState().showFavorite(favorite);
    }

    public void changeFavorite(long movieId, boolean isFavorite) {
        Logic.background(() -> {
            MovieRepository repository = RepositoryFactory.getInstance().getMovieRepository();
            if (isFavorite) {
                repository.setFavorite(movieId);
            } else {
                repository.removeFavorite(movieId);
            }
        });
    }
}
