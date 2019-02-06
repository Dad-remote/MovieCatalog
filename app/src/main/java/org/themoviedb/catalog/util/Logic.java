package org.themoviedb.catalog.util;

import android.os.Bundle;

import org.themoviedb.catalog.ui.details.MovieDetailsFragment;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Logic {

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(3);

    @NonNull
    public static <T> T checkNotNull(@Nullable T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static void background(Runnable task) {
        EXECUTOR.execute(task);
    }

    public static Bundle toDetailsArgs(long id) {
        Bundle args = new Bundle();
        args.putLong(MovieDetailsFragment.ID_ARG, id);
        return args;
    }
}
