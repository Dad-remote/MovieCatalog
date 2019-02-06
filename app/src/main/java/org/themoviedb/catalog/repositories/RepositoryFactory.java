package org.themoviedb.catalog.repositories;

import org.themoviedb.catalog.db.OwnDatabase;
import org.themoviedb.catalog.net.MovieApi;

public class RepositoryFactory {

    private static volatile RepositoryFactory instance = null;

    private MovieApi service;
    private OwnDatabase database;
    private MovieRepository movieRepository;

    public static RepositoryFactory getInstance() {
        if (instance == null) {
            synchronized (RepositoryFactory.class) {
                if (instance == null) {
                    instance = new RepositoryFactory();
                }
            }
        }
        return instance;
    }

    public void init(MovieApi service, OwnDatabase database) {
        this.service = service;
        this.database = database;
    }

    public MovieRepository getMovieRepository() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository(service, database);
        }
        return movieRepository;
    }
}
