package com.lando.systems.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.lando.systems.August2015GAM;
import com.lando.systems.utils.Assets;
import com.lando.systems.world.Level;

/**
 * Brian Ploeckelman created on 8/8/2015.
 */
public class PrototypeScreen extends GAMScreen {

    OrthographicCamera uiCamera;
    FrameBuffer        sceneFrameBuffer;
    TextureRegion      sceneRegion;
    Level              level;

    public PrototypeScreen(August2015GAM game) {
        super(game);

        uiCamera = new OrthographicCamera();
        uiCamera.setToOrtho(false, August2015GAM.win_width, August2015GAM.win_height);
        sceneFrameBuffer = new FrameBuffer(Format.RGBA8888, August2015GAM.win_width, August2015GAM.win_height, false);
        sceneRegion = new TextureRegion(sceneFrameBuffer.getColorBufferTexture());
        sceneRegion.flip(false, true);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            LevelEditorScreen editorScreen = new LevelEditorScreen(game);
            if (level != null) {
                editorScreen.setLevel(level);
            }
            game.setScreen(editorScreen);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        sceneFrameBuffer.begin();
        {
            Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            batch.setProjectionMatrix(camera.combined);
            if (level != null) {
                level.render(batch);
            } else {
                batch.draw(Assets.testTexture, 0, 0);
            }
            batch.end();
        }
        sceneFrameBuffer.end();

        // TODO: add default screen shader
        batch.setShader(null);
        batch.begin();
        {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.setProjectionMatrix(uiCamera.combined);
            batch.draw(sceneRegion, 0, 0);
            batch.end();
        }
    }

    // ------------------------------------------------------------------------
    // Accessors
    // ------------------------------------------------------------------------

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
        centerOnSpawn();
    }

    // ------------------------------------------------------------------------
    // Private Implementation
    // ------------------------------------------------------------------------

    private void centerOnSpawn() {
        if (level == null) return;
        if (!level.hasSpawn()) return;

        int spawnCellIndex = level.getSpawnCellIndex();
        int spawnCellX = spawnCellIndex % level.getWidth();
        int spawnCellY = spawnCellIndex / level.getHeight();
        float spawnCellCenterPosX = spawnCellX * level.getCellWidth()  + level.getCellWidth()  / 2f;
        float spawnCellCenterPosY = spawnCellY * level.getCellHeight() + level.getCellHeight() / 2f;

        camera.translate(spawnCellCenterPosX - camera.viewportWidth  / 2f,
                         spawnCellCenterPosY - camera.viewportHeight / 2f);
    }

}
