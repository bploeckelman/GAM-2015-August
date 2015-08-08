package com.lando.systems.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.lando.systems.August2015GAM;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(August2015GAM.win_width, August2015GAM.win_height);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new August2015GAM();
        }
}