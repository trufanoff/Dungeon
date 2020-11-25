package ru.gb.dungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameController {
    private SpriteBatch batch;
    private UnitController unitController;
    private ProjectileController projectileController;
    private GameMap gameMap;
    private int cursorX;
    private int cursorY;

    public int getCursorX() {
        return cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public ProjectileController getProjectileController() {
        return projectileController;
    }

    public UnitController getUnitController() {
        return unitController;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public GameController(SpriteBatch batch) {
        this.batch = batch;
        this.unitController = new UnitController(this);
        this.gameMap = new GameMap();
        this.projectileController = new ProjectileController();
        this.unitController.init();
    }

    public void update(float dt) {
        cursorX = (Gdx.input.getX() / GameMap.CELLS_SIZE);
        cursorY = ((720 - Gdx.input.getY()) / GameMap.CELLS_SIZE);
        projectileController.update(dt);
        unitController.update(dt);
    }

}
