package com.lando.systems;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lando.systems.accessors.ColorAccessor;
import com.lando.systems.accessors.RectangleAccessor;
import com.lando.systems.accessors.Vector2Accessor;
import com.lando.systems.accessors.Vector3Accessor;
import com.lando.systems.screens.PrototypeScreen;
import com.lando.systems.utils.Assets;

public class August2015GAM extends Game {

	public static TweenManager tween;

	private SpriteBatch batch;

	@Override
	public void create () {
		Assets.load();
		batch = Assets.batch;

		if (tween == null) {
			tween = new TweenManager();
			Tween.registerAccessor(Color.class, new ColorAccessor());
			Tween.registerAccessor(Rectangle.class, new RectangleAccessor());
			Tween.registerAccessor(Vector2.class, new Vector2Accessor());
			Tween.registerAccessor(Vector3.class, new Vector3Accessor());
		}

		setScreen(new PrototypeScreen(this));
	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime();
		tween.update(delta);
		super.render();
	}

	@Override
	public void dispose() {
		Assets.dispose();
	}

	// ------------------------------------------------------------------------
	// Game Constants
	// ------------------------------------------------------------------------

	public static final int     win_width      = 640;
	public static final int     win_height     = 480;
	public static final boolean win_resizeable = false;
	public static final String  win_title      = "GAM - August 2015";

}
