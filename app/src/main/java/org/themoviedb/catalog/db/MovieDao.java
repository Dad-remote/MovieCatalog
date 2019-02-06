package org.themoviedb.catalog.db;

import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movies WHERE id = :id")
    LiveData<Movie> getById(long id);

    @Query("SELECT * FROM Movies WHERE id = :id")
    Movie get(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Movie> movies);

    @Query("SELECT * FROM Favorites")
    DataSource.Factory<Integer, Favorite> getFavorites();

    @Query("SELECT * FROM Favorites WHERE id = :id")
    LiveData<Favorite> getFavoriteById(long id);

    @Query("SELECT * FROM Favorites WHERE id = :id")
    Favorite getFavorite(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveFavorite(Favorite favorite);

    @Delete
    void delete(Favorite favorite);
}
