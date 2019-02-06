package org.themoviedb.catalog.ui.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.model.Movie;
import org.themoviedb.catalog.support.OnItemClickListener;
import org.themoviedb.catalog.util.ImageHelper;
import org.themoviedb.catalog.util.Logic;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends PagedListAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    private static final DiffUtil.ItemCallback<Movie> DIFF_MOVIE_CALLBACK = new DiffMovieCallback();

    private OnItemClickListener<Movie> clickListener;

    MoviesAdapter(OnItemClickListener<Movie> clickListener) {
        super(DIFF_MOVIE_CALLBACK);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movies, parent, false);
        return new MovieViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie item = getItem(position);
        Logic.checkNotNull(item);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getOverview());
        ImageHelper.showImage(holder.image, item.getPosterPath());
        holder.layout.setOnClickListener((v) -> clickListener.onItemClick(item));
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final View layout;
        private final TextView title;
        private final TextView description;
        private final ImageView image;

        MovieViewHolder(@NonNull View layout) {
            super(layout);
            this.layout = layout;
            title = layout.findViewById(R.id.title);
            description = layout.findViewById(R.id.description);
            image = layout.findViewById(R.id.image);
        }
    }

    private static class DiffMovieCallback extends DiffUtil.ItemCallback<Movie> {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    }
}
