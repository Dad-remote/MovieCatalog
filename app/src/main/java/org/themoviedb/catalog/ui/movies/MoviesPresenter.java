package org.themoviedb.catalog.ui.movies;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.model.Movie;
import org.themoviedb.catalog.net.MovieSource;
import org.themoviedb.catalog.repositories.RepositoryFactory;
import org.themoviedb.catalog.support.MainThreadExecutor;
import org.themoviedb.catalog.util.Logic;

import java.util.concurrent.Executors;

import androidx.paging.PagedList;

@InjectViewState
public class MoviesPresenter extends MvpPresenter<MoviesView> {

    private static final int PAGE_SIZE = 20;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showMovies(getPagedList());
    }

    private PagedList<Movie> getPagedList() {
        MovieSource dataSource = RepositoryFactory.getInstance().getMovieRepository().getMovieSource(PAGE_SIZE);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        return new PagedList.Builder<>(dataSource, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(new MainThreadExecutor())
                .build();
    }

    void onItemClick(Movie item) {
        getViewState().navigate(R.id.action_mainFragment_to_movieDetailsFragment, Logic.toDetailsArgs(item.getId()));
    }

}
