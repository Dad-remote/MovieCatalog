package org.themoviedb.catalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movies")
public class Movie {

    @PrimaryKey
    private long id;

    private String title;
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath == null || posterPath.startsWith("http") ? posterPath : "https://image.tmdb.org/t/p/w342" + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(title, movie.title) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(posterPath, movie.posterPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, overview, posterPath);
    }
}
