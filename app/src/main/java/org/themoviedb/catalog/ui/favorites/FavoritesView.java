package org.themoviedb.catalog.ui.favorites;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import org.themoviedb.catalog.model.Favorite;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public interface FavoritesView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void navigate(int action, Bundle args);

    void showFavorites(LiveData<PagedList<Favorite>> pagedList);
}
