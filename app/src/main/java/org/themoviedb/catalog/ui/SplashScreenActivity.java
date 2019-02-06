package org.themoviedb.catalog.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

    private static final long DELAY_TIME = 700;

    private Handler handler = new Handler();
    private Runnable startMainScreenTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(startMainScreenTask, DELAY_TIME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(startMainScreenTask);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(startMainScreenTask);
    }
}
