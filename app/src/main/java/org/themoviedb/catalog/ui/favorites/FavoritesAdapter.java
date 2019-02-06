package org.themoviedb.catalog.ui.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.support.OnItemClickListener;
import org.themoviedb.catalog.util.ImageHelper;
import org.themoviedb.catalog.util.Logic;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesAdapter extends PagedListAdapter<Favorite, FavoritesAdapter.FavoriteViewHolder> {

    private static final DiffUtil.ItemCallback<Favorite> DIFF_MOVIE_CALLBACK = new DiffMovieCallback();

    private OnItemClickListener<Favorite> clickListener;

    FavoritesAdapter(OnItemClickListener<Favorite> clickListener) {
        super(DIFF_MOVIE_CALLBACK);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movies, parent, false);
        return new FavoriteViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favorite item = getItem(position);
        Logic.checkNotNull(item);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getOverview());
        ImageHelper.showImage(holder.image, item.getPosterPath());
        holder.layout.setOnClickListener((v) -> clickListener.onItemClick(item));
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private final View layout;
        private final TextView title;
        private final TextView description;
        private final ImageView image;

        FavoriteViewHolder(@NonNull View layout) {
            super(layout);
            this.layout = layout;
            title = layout.findViewById(R.id.title);
            description = layout.findViewById(R.id.description);
            image = layout.findViewById(R.id.image);
        }
    }

    private static class DiffMovieCallback extends DiffUtil.ItemCallback<Favorite> {
        @Override
        public boolean areItemsTheSame(@NonNull Favorite oldItem, @NonNull Favorite newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Favorite oldItem, @NonNull Favorite newItem) {
            return oldItem.equals(newItem);
        }
    }
}
