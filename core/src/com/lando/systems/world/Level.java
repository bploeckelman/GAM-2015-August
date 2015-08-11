package com.lando.systems.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lando.systems.utils.Assets;

import java.util.Arrays;

/**
 * Brian Ploeckelman created on 8/10/2015.
 */
public class Level {

    public static final int CELL_WIDTH = 32;
    public static final int CELL_HEIGHT = 32;

    int width;
    int height;
    int numCells;
    int[] cells;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        numCells = width * height;
        cells = new int[numCells];
        Arrays.fill(cells, 0);
    }

    public int getCellAt(int x, int y) {
        return cells[y * width + x];
    }

    public void render(SpriteBatch batch) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                batch.draw(Assets.testTexture, x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

}
