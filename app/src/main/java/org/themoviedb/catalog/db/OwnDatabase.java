package org.themoviedb.catalog.db;

import org.themoviedb.catalog.model.Favorite;
import org.themoviedb.catalog.model.Movie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class, Favorite.class}, version = 3, exportSchema = false)
public abstract class OwnDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
