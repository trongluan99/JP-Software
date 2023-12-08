package com.jp.module;

import com.ads.jp.admob.Admob;
import com.ads.jp.admob.AppOpenManager;
import com.ads.jp.ads.JPAd;
import com.ads.jp.application.AdsMultiDexApplication;
import com.ads.jp.applovin.AppLovin;
import com.ads.jp.applovin.AppOpenMax;
import com.ads.jp.billing.AppPurchase;
import com.ads.jp.config.AdjustConfig;
import com.ads.jp.config.AppsflyerConfig;
import com.ads.jp.config.JPAdConfig;
import com.jp.module.activity.MainActivity;
import com.jp.module.activity.SplashActivity;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends AdsMultiDexApplication {
    private final String APPSFLYER_TOKEN = "";
    private final String ADJUST_TOKEN = "";
    private final String EVENT_PURCHASE_ADJUST = "";
    private final String EVENT_AD_IMPRESSION_ADJUST = "";
    protected StorageCommon storageCommon;
    private static MyApplication context;

    public static MyApplication getApplication() {
        return context;
    }

    public StorageCommon getStorageCommon() {
        return storageCommon;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Admob.getInstance().setNumToShowAds(0);

        storageCommon = new StorageCommon();
        initBilling();
        initAds();

    }

    private void initAds() {
        String environment = BuildConfig.env_dev ? JPAdConfig.ENVIRONMENT_DEVELOP : JPAdConfig.ENVIRONMENT_PRODUCTION;
        jpAdConfig = new JPAdConfig(this, JPAdConfig.PROVIDER_ADMOB, environment);

        AdjustConfig adjustConfig = new AdjustConfig(true, ADJUST_TOKEN);
        adjustConfig.setEventAdImpression(EVENT_AD_IMPRESSION_ADJUST);
        adjustConfig.setEventNamePurchase(EVENT_PURCHASE_ADJUST);
        jpAdConfig.setAdjustConfig(adjustConfig);

        AppsflyerConfig appsflyerConfig = new AppsflyerConfig(true, APPSFLYER_TOKEN);

        jpAdConfig.setIdAdResume(BuildConfig.ads_open_app);

        listTestDevice.add("EC25F576DA9B6CE74778B268CB87E431");
        jpAdConfig.setListDeviceTest(listTestDevice);
        jpAdConfig.setIntervalInterstitialAd(15);
        jpAdConfig.setAdjustTokenTiktok("1234756");

        JPAd.getInstance().init(this, jpAdConfig, false);

        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        AppLovin.getInstance().setDisableAdResumeWhenClickAds(true);
        Admob.getInstance().setOpenActivityAfterShowInterAds(true);

        if (JPAd.getInstance().getMediationProvider() == JPAdConfig.PROVIDER_ADMOB) {
            AppOpenManager.getInstance().disableAppResumeWithActivity(SplashActivity.class);
        } else {
            AppOpenMax.getInstance().disableAppResumeWithActivity(SplashActivity.class);
        }
    }

    private void initBilling() {
        List<String> listINAPId = new ArrayList<>();
        listINAPId.add(MainActivity.PRODUCT_ID);
        List<String> listSubsId = new ArrayList<>();

        AppPurchase.getInstance().initBilling(getApplication(), listINAPId, listSubsId);
    }

}
