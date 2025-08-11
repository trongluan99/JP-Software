package com.ads.jp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ads.jp.R;
import com.ads.jp.ads.JPAd;
import com.ads.jp.ads.wrapper.ApNativeAd;
import com.ads.jp.funtion.AdCallback;
import com.facebook.shimmer.ShimmerFrameLayout;

public class DialogShowNativeFull extends Dialog {

    private FrameLayout frAds;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ApNativeAd apNativeAd;
    private AdCallback adCallback;
    private Activity activity;
    private ImageView ivClose;

    public DialogShowNativeFull(Activity activity, ApNativeAd apNativeAd, AdCallback adCallback) {
        super(activity, R.style.AppTheme);
        this.activity = activity;
        this.apNativeAd = apNativeAd;
        this.adCallback = adCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show_native_full);

        hideSystemUI();

        frAds = findViewById(R.id.frAds);
        shimmerFrameLayout = findViewById(R.id.shimmer_container_native);
        ivClose = findViewById(R.id.ivClose);

        if (apNativeAd == null) {
            dismiss();
            adCallback.onNextAction();
        } else {
            JPAd.getInstance().populateNativeAdView(activity, apNativeAd, frAds, shimmerFrameLayout);
            new Handler().postDelayed(() -> {
                ivClose.setVisibility(View.VISIBLE);
            }, 3000);
        }

        ivClose.setOnClickListener(view -> {
            dismiss();
            adCallback.onNextAction();
        });
    }

    private void hideSystemUI() {
        Window window = getWindow();
        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false);
                window.getInsetsController().hide(
                        android.view.WindowInsets.Type.navigationBars() |
                                android.view.WindowInsets.Type.statusBars()
                );
                window.getInsetsController().setSystemBarsBehavior(
                        android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                );
            } else {
                View decorView = window.getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(uiOptions);
            }

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}