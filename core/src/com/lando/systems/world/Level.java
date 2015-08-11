package com.lando.systems.world;

import com.badlogic.gdx.graphics.Texture;
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

    // Json reader requires no-arg ctor
    public Level() {}

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        numCells = width * height;
        cells = new int[numCells];
        Arrays.fill(cells, 0);
    }

    public int getCellAt(int x, int y) {
        int index = y * width + x;
        if (index >= 0 && index < cells.length) {
            return cells[index];
        }
        return -1;
    }

    public void setCellAt(int x, int y, int value) {
        int index = y * width + x;
        if (index >= 0 && index < cells.length) {
            cells[index] = value;
        }
    }

    public void render(SpriteBatch batch) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int value = getCellAt(x, y);
                Texture texture = Assets.testTexture;
                switch (value) {
                    case 0: texture = Assets.testTexture; break;
                    case 1: texture = Assets.tempTexture; break;
                }
                batch.draw(texture, x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

}
