package com.lando.systems.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lando.systems.August2015GAM;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = August2015GAM.win_width;
		config.height = August2015GAM.win_height;
		config.resizable = August2015GAM.win_resizeable;
		config.title = August2015GAM.win_title;
		new LwjglApplication(new August2015GAM(), config);
	}
}
