package ru.gb.dungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class DungeonDame extends ApplicationAdapter {
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Hero hero;
    private ProjectileController projectileController;
    private GameMap gameMap;

    @Override
    public void create() {
        batch = new SpriteBatch();
        atlas = new TextureAtlas("images/game.pack");
        projectileController = new ProjectileController(atlas);
        hero= new Hero(atlas, projectileController);
        gameMap = new GameMap(atlas);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        gameMap.render(batch);
        hero.render(batch);
        projectileController.render(batch);
        batch.end();
    }

    public void update(float dt){
        hero.update(dt);
        projectileController.update(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
    }
}
