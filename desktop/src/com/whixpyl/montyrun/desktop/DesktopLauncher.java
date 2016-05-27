package com.whixpyl.montyrun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.whixpyl.montyrun.MontyRun;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MontyRun.V_WIDTH;
		config.height = MontyRun.V_HEIGHT;
		new LwjglApplication(new MontyRun(), config);
	}
}
