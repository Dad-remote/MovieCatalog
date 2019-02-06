package org.themoviedb.catalog.ui.movies;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import org.themoviedb.catalog.model.Movie;

import androidx.paging.PagedList;

public interface MoviesView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void navigate(int action, Bundle args);

    void showMovies(PagedList<Movie> pagedList);
}
