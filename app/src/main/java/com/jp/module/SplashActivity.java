package com.jp.module;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.jp.ads.JPAd;
import com.ads.jp.ads.wrapper.ApInterstitialAd;
import com.ads.jp.billing.AppPurchase;
import com.ads.jp.funtion.AdCallback;
import com.ads.jp.util.SharePreferenceUtils;
import com.google.android.gms.ads.LoadAdError;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private ApInterstitialAd mInterSplash;
    private Button btnLoad, btnShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btnLoad = findViewById(R.id.btn_load);
        btnShow = findViewById(R.id.btn_show);


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JPAd.getInstance().getInterstitialAds(SplashActivity.this, BuildConfig.ad_interstitial_splash, new AdCallback() {
                    @Override
                    public void onApInterstitialLoad(@Nullable ApInterstitialAd apInterstitialAd) {
                        super.onApInterstitialLoad(apInterstitialAd);
                        mInterSplash = apInterstitialAd;
                        Toast.makeText(SplashActivity.this, "Ads Ready", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdFailedToLoad(@Nullable LoadAdError i) {
                        super.onAdFailedToLoad(i);
                        Toast.makeText(SplashActivity.this, "Ads Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterSplash != null) {
                    JPAd.getInstance().forceShowInterstitial(SplashActivity.this, mInterSplash, new AdCallback() {
                        @Override
                        public void onNextAction() {
                            super.onNextAction();
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                    }, false);
                }
            }
        });
    }
}
