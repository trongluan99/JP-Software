package com.jp.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.jp.admob.AppOpenManager;
import com.ads.jp.ads.JPAd;
import com.ads.jp.config.JPAdConfig;
import com.ads.jp.funtion.AdCallback;
import com.jp.module.BuildConfig;
import com.jp.module.R;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private List<String> list = new ArrayList<>();
    private String idAdSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (JPAd.getInstance().getMediationProvider() == JPAdConfig.PROVIDER_ADMOB)
            idAdSplash = BuildConfig.ad_interstitial_splash;
        else
            idAdSplash = getString(R.string.applovin_test_inter);

        AppOpenManager.getInstance().loadAdOpenSplash2id(SplashActivity.class, this,
                "ca-app-pub-3940256099942544/3419835294",
                "ca-app-pub-3940256099942544/3419835294", 25000, new AdCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        startMain();
                    }
                });
    }

    private void startMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppOpenManager.getInstance().onCheckShowAppOpenSplashWhenFail(this, new AdCallback() {
            @Override
            public void onNextAction() {
                super.onNextAction();
                startMain();
            }
        }, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "Splash onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "Splash onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}