package com.whixpyl.montyrun;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.whixpyl.montyrun.MontyRun;

public class AndroidLauncher extends AndroidApplication {

	InterstitialAd mInterstitialAd;
	AdRequest adRequest;
	private View view;
	private RelativeLayout layout;


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		//View
		view = new View(this);
		view = initializeForView(new MontyRun(),config);
		layout = new RelativeLayout(this);
		layout.addView(view);
		setContentView(layout);

		//Interstitial ad
		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-5000196928361978/5935672840");
		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				requestNewInterstitial();
//				beginPlayingGame();
			}

			@Override
			public void onAdLoaded() {
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				}
			}
		});
		requestNewInterstitial();

	}

	private void requestNewInterstitial() {
		adRequest = new AdRequest.Builder()
				.addTestDevice("65D974177B7DD6F6CE05708C0E2507BD")
				.build();
		mInterstitialAd.loadAd(adRequest);
	}
}
