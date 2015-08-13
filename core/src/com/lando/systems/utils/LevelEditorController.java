package com.lando.systems.utils;

import com.badlogic.gdx.InputAdapter;
import com.lando.systems.screens.LevelEditorScreen;
import com.lando.systems.world.Entity;
import com.lando.systems.world.Level;

/**
 * Brian Ploeckelman created on 8/11/2015.
 */
public class LevelEditorController extends InputAdapter {

    private final LevelEditorScreen levelEditorScreen;

    public LevelEditorController(LevelEditorScreen levelEditorScreen) {
        this.levelEditorScreen = levelEditorScreen;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (levelEditorScreen.getLevel() != null && button == 0) {
            int cellValue = levelEditorScreen.getSelectedEntityType().getValue();
            float wx = levelEditorScreen.getMouseWorldPos().x;
            float wy = levelEditorScreen.getMouseWorldPos().y;
            int x = (int) (wx / Level.CELL_WIDTH);
            int y = (int) (wy / Level.CELL_HEIGHT);
            if (levelEditorScreen.isRemovalMode()) {
                levelEditorScreen.getLevel().setCellAt(x, y, Entity.Type.BLANK.getValue());
            } else {
                levelEditorScreen.getLevel().setCellAt(x, y, cellValue);
            }
            return true;
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO: handle drag event for 'painting' cells of the level
        return super.touchDragged(screenX, screenY, pointer);
    }

}
