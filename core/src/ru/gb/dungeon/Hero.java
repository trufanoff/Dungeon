package ru.gb.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private ProjectileController projectileController;
    private Vector2 position;
    private float velocity = 150.0f;
    private TextureRegion texture;
    private boolean doubleFire = false;
    private float angle;
    private float halfSize;



    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 200);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
        this.halfSize = texture.getRegionHeight() / 2.0f;

    }

    public void fire(boolean doubleFire, float angle) {
        if (!doubleFire) {
            if (angle == 0) {
                projectileController.activate(position.x, position.y, 400, 0);
            }
            if (angle == 90) {
                projectileController.activate(position.x, position.y, 0, 400);
            }
            if (angle == 180) {
                projectileController.activate(position.x, position.y, -400, 0);
            }
            if (angle == 270) {
                projectileController.activate(position.x, position.y, 0, -400);
            }
        } else {
            if (angle == 0) {
                projectileController.activate(position.x, position.y, 400, 10);
                projectileController.activate(position.x, position.y, 400, -10);
            }
            if (angle == 90) {
                projectileController.activate(position.x, position.y, 10, 400);
                projectileController.activate(position.x, position.y, -10, 400);
            }
            if (angle == 180) {
                projectileController.activate(position.x, position.y, -400, 10);
                projectileController.activate(position.x, position.y, -400, -10);
            }
            if (angle == 270) {
                projectileController.activate(position.x, position.y, 10, -400);
                projectileController.activate(position.x, position.y, -10, -400);
            }
        }
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            doubleFire = !doubleFire;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            fire(doubleFire, angle);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            angle = 90.f;
            position.y += velocity * dt;
            if (position.y > 720 - halfSize) {
                position.y = 720 - halfSize;
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            angle = 270.f;
            position.y -= velocity * dt;
            if (position.y < 0 + halfSize) {
                position.y = 0 + halfSize;
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle = 180.f;
            position.x -= velocity * dt;
            if (position.x < 0 + halfSize) {
                position.x = 0 + halfSize;
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle = 0.f;
            position.x += velocity * dt;
            if (position.x > 1280 - halfSize) {
                position.x = 1280 - halfSize;
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 20, position.y - 20, 20, 20, 40, 40, 1, 1, angle);
    }
}
