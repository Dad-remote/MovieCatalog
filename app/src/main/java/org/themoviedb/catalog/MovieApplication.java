package org.themoviedb.catalog;

import android.app.Application;

import org.themoviedb.catalog.db.OwnDatabase;
import org.themoviedb.catalog.net.MovieApi;
import org.themoviedb.catalog.repositories.RepositoryFactory;

import androidx.room.Room;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApplication extends Application {

    private static MovieApi service;

    @Override
    public void onCreate() {
        super.onCreate();

        service = buildRetrofit().create(MovieApi.class);
        OwnDatabase database = Room.databaseBuilder(this, OwnDatabase.class, "TheMovie").build();

        RepositoryFactory.getInstance().init(service, database);
    }

    private Retrofit buildRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS));

        return new Retrofit.Builder().baseUrl(MovieApi.ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }

    public static MovieApi getService() {
        return service;
    }
}
