package org.themoviedb.catalog.ui.details;

import com.arellomobile.mvp.MvpView;

import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.model.Movie;

import androidx.lifecycle.LiveData;

public interface DetailsView extends MvpView {

    void showMovieDetails(LiveData<Movie> movie);
    void showFavorite(LiveData<Favorite> favorite);
}
