package org.themoviedb.catalog.support;

import android.os.Bundle;

import com.arellomobile.mvp.MvpDelegate;

import androidx.appcompat.app.AppCompatActivity;

public class MvpAppCompatActivity extends AppCompatActivity {
    private MvpDelegate<? extends MvpAppCompatActivity> mMvpDelegate;

    public MvpAppCompatActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    protected void onStart() {
        super.onStart();
        this.getMvpDelegate().onAttach();
    }

    protected void onResume() {
        super.onResume();
        this.getMvpDelegate().onAttach();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.getMvpDelegate().onSaveInstanceState(outState);
        this.getMvpDelegate().onDetach();
    }

    protected void onStop() {
        super.onStop();
        this.getMvpDelegate().onDetach();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getMvpDelegate().onDestroyView();
        if (this.isFinishing()) {
            this.getMvpDelegate().onDestroy();
        }

    }

    public MvpDelegate getMvpDelegate() {
        if (this.mMvpDelegate == null) {
            this.mMvpDelegate = new MvpDelegate(this);
        }

        return this.mMvpDelegate;
    }
}
