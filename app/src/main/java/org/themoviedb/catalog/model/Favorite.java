package org.themoviedb.catalog.model;

import androidx.room.Entity;

@Entity(tableName = "Favorites")
public class Favorite extends Movie {

    private Boolean favorite;

    public static Favorite valueOf(Movie movie) {
        Favorite favorite = new Favorite();
        favorite.setId(movie.getId());
        favorite.setTitle(movie.getTitle());
        favorite.setOverview(movie.getOverview());
        favorite.setPosterPath(movie.getPosterPath());
        return favorite;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
