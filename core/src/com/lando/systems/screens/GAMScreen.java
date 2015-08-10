package com.lando.systems.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.lando.systems.August2015GAM;
import com.lando.systems.utils.Assets;

/**
 * Brian Ploeckelman created on 8/9/2015.
 */
public class GAMScreen extends ScreenAdapter {

    protected final August2015GAM game;

    protected SpriteBatch batch;
    protected Vector3     mouseScreenPos;
    protected Vector3     mouseWorldPos;
    protected OrthographicCamera camera;

    public GAMScreen(August2015GAM game) {
        this.game = game;
        batch = Assets.batch;
        mouseScreenPos = new Vector3();
        mouseWorldPos = new Vector3();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, August2015GAM.win_width, August2015GAM.win_height);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        camera.update();
        updateMouseVectors(camera);
    }

    // ------------------------------------------------------------------------
    // ScreenAdapter Methods
    // ------------------------------------------------------------------------

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth  = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {
        disableInput();
    }

    @Override
    public void resume() {
        enableInput();
    }

    // ------------------------------------------------------------------------
    // Protected Implementation
    // ------------------------------------------------------------------------

    protected void updateMouseVectors(Camera camera) {
        float mx = Gdx.input.getX();
        float my = Gdx.input.getY();
        mouseScreenPos.set(mx, my, 0);
        mouseWorldPos.set(mx, my, 0);
        camera.unproject(mouseWorldPos);
    }

    protected void enableInput() {
        final InputMultiplexer mux = new InputMultiplexer();
        // TODO: add input processor(s) here
        Gdx.input.setInputProcessor(mux);
    }

    protected void disableInput() {
        Gdx.input.setInputProcessor(null);
    }

}
