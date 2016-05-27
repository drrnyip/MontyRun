package com.whixpyl.montyrun;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.whixpyl.montyrun.MontyRun;

public class AndroidLauncher extends AndroidApplication implements AdCall{

	InterstitialAd mInterstitialAd;
	AdRequest adRequest;
	private View view;
	private RelativeLayout layout;
	private boolean show;
	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;

	//Handler
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				show = true;
				if (mInterstitialAd.isLoaded()){
					mInterstitialAd.show();
				}
			}
			else if (msg.what == 0)
			{
				System.err.println("handle: 2");
				show = false;
			}
		}
	};


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		//View
		view = new View(this);
		view = initializeForView(new MontyRun(this),config);
		layout = new RelativeLayout(this);
		layout.addView(view);
		setContentView(layout);

		//Interstitial ad
		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-5000196928361978/5935672840");
		requestNewInterstitial();

		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				requestNewInterstitial();
			}

			@Override
			public void onAdLoaded() {
				if (mInterstitialAd.isLoaded() && show == true) {
					mInterstitialAd.show();
					show = false;
				}
			}
		});

	}

	private void requestNewInterstitial() {
		adRequest = new AdRequest.Builder()
				.addTestDevice("65D974177B7DD6F6CE05708C0E2507BD")
				.build();
		mInterstitialAd.loadAd(adRequest);
	}

	@Override
	public void showAd(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);

	}
}
