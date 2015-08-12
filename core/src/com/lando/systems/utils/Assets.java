package com.lando.systems.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Brian Ploeckelman created on 8/21/2015.
 */
public class Assets {

    public static SpriteBatch batch;
    public static ModelBatch  modelBatch;
    public static BitmapFont  font;

    public static Texture testTexture;
    public static Texture tempTexture;
    public static Texture spritesheetTexture;

    public static TextureRegion blankRegion;
    public static TextureRegion spawnRegion;
    public static TextureRegion wallRegion;
    public static TextureRegion exitRegion;

    public static void load() {
        batch = new SpriteBatch();
        modelBatch = new ModelBatch();

        font = new BitmapFont();
        font.getData().markupEnabled = true;

        testTexture = new Texture("badlogic.jpg");
        tempTexture = new Texture("temp.png");
        spritesheetTexture = new Texture("spritesheet.png");

        TextureRegion[][] regions = TextureRegion.split(spritesheetTexture, 32, 32);
        blankRegion = new TextureRegion(tempTexture);
        spawnRegion = regions[0][0];
        wallRegion = regions[0][1];
        exitRegion = regions[0][2];
    }

    public static void dispose() {
        batch.dispose();
        modelBatch.dispose();
        font.dispose();
        testTexture.dispose();
        tempTexture.dispose();
        spritesheetTexture.dispose();
    }

    private static ShaderProgram compileShaderProgram(FileHandle vertSource, FileHandle fragSource) {
        ShaderProgram.pedantic = false;
        final ShaderProgram shader = new ShaderProgram(vertSource, fragSource);
        if (!shader.isCompiled()) {
            throw new GdxRuntimeException("Failed to compile shader program:\n" + shader.getLog());
        }
        else if (shader.getLog().length() > 0) {
            Gdx.app.debug("SHADER", "ShaderProgram compilation log:\n" + shader.getLog());
        }
        return shader;
    }

}
