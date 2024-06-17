package com.jp.module;

import com.ads.jp.admob.Admob;
import com.ads.jp.admob.AppOpenManager;
import com.ads.jp.ads.JPAd;
import com.ads.jp.application.AdsMultiDexApplication;
import com.ads.jp.billing.AppPurchase;
import com.ads.jp.config.AdjustConfig;
import com.ads.jp.config.JPAdConfig;

import java.util.ArrayList;
import java.util.List;

public class App extends AdsMultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initAds();
        initBilling();
    }

    private void initAds() {
        String environment = BuildConfig.DEBUG ? JPAdConfig.ENVIRONMENT_DEVELOP : JPAdConfig.ENVIRONMENT_PRODUCTION;
        mJPAdConfig = new JPAdConfig(this, environment);

        AdjustConfig adjustConfig = new AdjustConfig(true, getString(R.string.adjust_token));
        mJPAdConfig.setAdjustConfig(adjustConfig);
        mJPAdConfig.setFacebookClientToken(getString(R.string.facebook_client_token));
        mJPAdConfig.setAdjustTokenTiktok(getString(R.string.tiktok_token));

        mJPAdConfig.setIdAdResume("");

        JPAd.getInstance().init(this, mJPAdConfig);
        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        Admob.getInstance().setOpenActivityAfterShowInterAds(true);
        AppOpenManager.getInstance().disableAppResumeWithActivity(MainActivity.class);
    }

    private void initBilling() {
        List<String> listIAP = new ArrayList<>();
        listIAP.add("android.test.purchased");
        List<String> listSub = new ArrayList<>();
        AppPurchase.getInstance().initBilling(this, listIAP, listSub);
    }
}
