package org.themoviedb.catalog.ui.favorites;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.repositories.RepositoryFactory;
import org.themoviedb.catalog.util.Logic;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritesView> {

    private static final int PAGE_SIZE = 20;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showFavorites(getPagedList());
    }

    LiveData<PagedList<Favorite>> getPagedList() {
        DataSource.Factory<Integer, Favorite> favorites = RepositoryFactory.getInstance().getMovieRepository().getFavorites();
        return new LivePagedListBuilder<>(favorites, PAGE_SIZE).build();
    }

    void onItemClick(Favorite item) {
        getViewState().navigate(R.id.action_mainFragment_to_movieDetailsFragment, Logic.toDetailsArgs(item.getId()));
    }
}
