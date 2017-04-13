package com.lethalskillzz.blockbusters.blockbusters.presentation.Splash;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery.DiscoveryActivity;

public class SplashActivity extends AppCompatActivity implements SplashMvpContract.View {

    private static final String TAG = "DiscoveryActivity";
    SplashMvpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        presenter = new SplashPresenter();
        presenter.attachView(this);
        presenter.startSplash();
    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void endSplash() {
        Intent intent = new Intent(SplashActivity.this, DiscoveryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }

}
