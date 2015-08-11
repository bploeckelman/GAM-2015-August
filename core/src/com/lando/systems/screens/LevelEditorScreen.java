package com.lando.systems.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lando.systems.August2015GAM;
import com.lando.systems.stages.Level;
import com.lando.systems.utils.ui.ButtonInputListenerAdapter;
import com.lando.systems.utils.ui.InfoDialog;

/**
 * Brian Ploeckelman created on 8/9/2015.
 */
public class LevelEditorScreen extends GAMScreen {

    private static final int MAX_WIDTH = 20;
    private static final int MAX_HEIGHT = 20;

    FrameBuffer   sceneFrameBuffer;
    TextureRegion sceneRegion;
    Skin          skin;
    Stage         stage;
    Window        window;
    InfoDialog    infoDialog;
    Level         level;

    public LevelEditorScreen(August2015GAM game) {
        super(game);

        sceneFrameBuffer = new FrameBuffer(Format.RGBA8888, August2015GAM.win_width, August2015GAM.win_height, false);
        sceneRegion = new TextureRegion(sceneFrameBuffer.getColorBufferTexture());
        sceneRegion.flip(false, true);

        initializeUserInterface();
        enableInput();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        sceneFrameBuffer.begin();
        {
            Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            if (level != null) {
                level.render(batch);
            }
            batch.end();

            stage.draw();
        }
        sceneFrameBuffer.end();

        // TODO: add default screen shader
        batch.setShader(null);
        batch.begin();
        {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.setProjectionMatrix(camera.combined);
            batch.draw(sceneRegion, 0, 0);
        }
        batch.end();
    }

    @Override
    protected void enableInput() {
        final InputMultiplexer mux = new InputMultiplexer();
        mux.addProcessor(stage);
        Gdx.input.setInputProcessor(mux);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    // ------------------------------------------------------------------------
    // Private Implementation
    // ------------------------------------------------------------------------

    private void initializeUserInterface() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        window = new Window("LevelEd", skin);
        window.setMovable(false);
        window.setResizable(false);
        window.setSize(camera.viewportWidth, 60f);
        window.setPosition(0f, camera.viewportHeight - 60f);
        window.setZIndex(0);

        infoDialog = new InfoDialog("Info", skin);

        final TextButton newLevelBtn  = new TextButton("New", skin);
        final TextButton saveLevelBtn = new TextButton("Save", skin);
        final TextButton loadLevelBtn = new TextButton("Load", skin);

        newLevelBtn.addListener(new ButtonInputListenerAdapter() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                final Dialog dlg = new Dialog("New Level", skin);

                final TextField widthField = new TextField("", skin);
                final TextField heightField = new TextArea("", skin);
                final TextButton okButton = new TextButton("Ok", skin);
                final TextButton cancelButton = new TextButton("Cancel", skin);

                okButton.addListener(new ButtonInputListenerAdapter() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        String widthStr  = widthField.getText();
                        String heightStr = heightField.getText();
                        try {
                            int width  = Integer.parseInt(widthStr);
                            int height = Integer.parseInt(heightStr);
                            if (width < 0 || height < 0) throw new NumberFormatException();
                            if (width > MAX_WIDTH || height > MAX_HEIGHT) throw new NumberFormatException();

                            newLevel(width, height);
                            dlg.hide();
                        } catch (NumberFormatException e) {
                            widthField.clear();
                            heightField.clear();
                            infoDialog.resetText("Invalid value for width or height.\n"
                                                 + "Must be integers > 0 and < " + MAX_WIDTH + "x" + MAX_HEIGHT);
                            infoDialog.show(stage);
                        }
                    }
                });
                cancelButton.addListener(new ButtonInputListenerAdapter() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        dlg.hide();
                    }
                });

                dlg.getContentTable().add("Width");
                dlg.getContentTable().add(widthField);
                dlg.getContentTable().row();
                dlg.getContentTable().add("Height");
                dlg.getContentTable().add(heightField);
                dlg.getContentTable().row();
                dlg.button(okButton);
                dlg.button(cancelButton);
                dlg.show(stage);
            }
        });
        saveLevelBtn.addListener(new ButtonInputListenerAdapter() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                infoDialog.resetText("Not yet implemented");
                infoDialog.show(stage);
            }
        });
        loadLevelBtn.addListener(new ButtonInputListenerAdapter() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                infoDialog.resetText("Not yet implemented");
                infoDialog.show(stage);
            }
        });

        window.top().add("Level:").padRight(5f);
        window.top().add(newLevelBtn);
        window.top().add(saveLevelBtn);
        window.top().add(loadLevelBtn);
        window.row();
        final float titleHeight = window.getTitleLabel().getHeight();
        window.padTop(titleHeight);

        stage.addActor(window);
    }

    private void newLevel(int width, int height) {
        infoDialog.resetText("Instantiated new level of size: " + width + "x" + height);
        infoDialog.show(stage);
        level = new Level(width, height);
    }

}
