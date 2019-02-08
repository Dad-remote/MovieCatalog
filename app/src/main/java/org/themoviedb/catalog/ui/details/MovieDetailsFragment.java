package org.themoviedb.catalog.ui.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.databinding.FragmentMovieDetailsBinding;
import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.model.Movie;
import org.themoviedb.catalog.support.MvpFragment;
import org.themoviedb.catalog.ui.MainActivity;
import org.themoviedb.catalog.util.ImageHelper;
import org.themoviedb.catalog.util.Logic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class MovieDetailsFragment extends MvpFragment implements DetailsView {

    public static final String ID_ARG = "id";

    private ImageView image;
    private TextView title;
    private TextView description;

    @InjectPresenter
    DetailsPresenter detailsPresenter;

    private Toolbar toolbar;
    private MaterialFavoriteButton toolbarFavorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logic.checkNotNull(getArguments());

        long movieId = getArguments().getLong(ID_ARG);
        detailsPresenter.setMovieId(movieId);
        toolbarFavorite = buildFavoriteButton();
        toolbarFavorite.setOnFavoriteChangeListener((buttonView, favorite) -> detailsPresenter.changeFavorite(movieId, favorite));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding viewDataBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        View layout = viewDataBinding.getRoot();
        //View layout = inflater.inflate(R.layout.fragment_movie_details, container, false);
        image = layout.findViewById(R.id.image);
        title = layout.findViewById(R.id.title);
        description = layout.findViewById(R.id.description);
        Logic.checkNotNull(getActivity());
        toolbar = ((MainActivity) getActivity()).getToolbar();

        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.addView(toolbarFavorite);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbar.removeView(toolbarFavorite);
    }

    @Override
    public void showMovieDetails(LiveData<Movie> movieData) {
        Log.d("Dad", "showMovieDetails");
        movieData.observe(this, new MovieDataObserver());
    }

    @Override
    public void showFavorite(LiveData<Favorite> favorite) {
        Log.d("Dad", "showFavorite");
        favorite.observe(this, new FavoriteDataObserver());
    }

    private MaterialFavoriteButton buildFavoriteButton() {
        return new MaterialFavoriteButton.Builder(getContext())
                .favorite(false)
                .color(MaterialFavoriteButton.STYLE_BLACK)
                .type(MaterialFavoriteButton.STYLE_STAR)
                .rotationDuration(400)
                .create();
    }

    private class MovieDataObserver implements Observer<Movie> {
        @Override
        public void onChanged(Movie movie) {
            if (movie != null) {
                Logic.checkNotNull(getActivity());
                getActivity().setTitle(movie.getTitle());
                ImageHelper.showImage(image, movie.getPosterPath());
                title.setText(movie.getTitle());
                description.setText(movie.getOverview());
            }
        }
    }

    private class FavoriteDataObserver implements Observer<Favorite> {
        @Override
        public void onChanged(Favorite favorite) {
            toolbarFavorite.setFavorite(favorite != null && Boolean.TRUE.equals(favorite.getFavorite()));
        }
    }
}
