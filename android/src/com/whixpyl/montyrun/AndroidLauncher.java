package com.whixpyl.montyrun;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.whixpyl.montyrun.MontyRun;

public class AndroidLauncher extends AndroidApplication {

	InterstitialAd mInterstitialAd;
	AdRequest adRequest;


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		//Interstitial ad
		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-5000196928361978/5935672840");
		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				requestNewInterstitial();
//				beginPlayingGame();
			}
		});
		requestNewInterstitial();
		initialize(new MontyRun(), config);


		if (mInterstitialAd.isLoading()) {
			System.err.println("Loading ad");
		}
		else {
			mInterstitialAd.show();
		}
	}

	private void requestNewInterstitial() {
		adRequest = new AdRequest.Builder()
				.addTestDevice("65D974177B7DD6F6CE05708C0E2507BD")
				.build();
		mInterstitialAd.loadAd(adRequest);
	}
}
