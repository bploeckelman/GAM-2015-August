package com.lando.systems.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        if (x >= 0 && x < width & y >= 0 && y < height) {
            return cells[y * width + x];
        }
        return -1;
    }

    public void setCellAt(int x, int y, int value) {
        if (x >= 0 && x < width & y >= 0 && y < height) {
            cells[y * width + x] = value;
        }
    }

    public void render(SpriteBatch batch) {
        TextureRegion texture = Assets.blankRegion;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int value = getCellAt(x, y);
                switch (value) {
                    case 0: texture = Entity.Type.BLANK.getRegion(); break;
                    case 1: texture = Entity.Type.SPAWN.getRegion(); break;
                    case 2: texture = Entity.Type.WALL.getRegion(); break;
                    case 3: texture = Entity.Type.EXIT.getRegion(); break;
                }
                batch.draw(texture, x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

}
