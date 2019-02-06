package org.themoviedb.catalog.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.model.Movie;
import org.themoviedb.catalog.support.MvpFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesFragment extends MvpFragment implements MoviesView {

    public static final int TITLE_RES_ID = R.string.popular;

    @InjectPresenter
    MoviesPresenter moviesPresenter;

    private RecyclerView list;
    private MoviesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MoviesAdapter(moviesPresenter::onItemClick);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_movies, container, false);
        list = layout.findViewById(R.id.list);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }

    @Override
    public void navigate(int action, Bundle args) {
        NavHostFragment.findNavController(this).navigate(action, args);
    }

    @Override
    public void showMovies(PagedList<Movie> pagedList) {
        adapter.submitList(pagedList);
    }
}
