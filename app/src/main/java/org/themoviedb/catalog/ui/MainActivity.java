package org.themoviedb.catalog.ui;

import android.os.Bundle;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.support.MvpAppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.Navigation;

public class MainActivity extends MvpAppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.navigationHostFragment).navigateUp();
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
