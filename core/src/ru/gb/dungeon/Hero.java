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
    private Vector2 dir_left_right;
    private Vector2 dir_up_down;
    private TextureRegion texture;
    private boolean doubleFire = false;
    private float angle;
    private float speed = 5.0f;

    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 200);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
        this.dir_left_right = new Vector2(speed, 0);
        this.dir_up_down = new Vector2(0, speed);
    }

//    public void fire(boolean doubleFire, float angle) {
//        if (!doubleFire){
//            if (angle == 0) {
//                projectileController.activate(position.x, position.y, 400, 0);
//            }
//            if (angle == 90) {
//                projectileController.activate(position.x, position.y, 0, 400);
//            }
//            if (angle == 180) {
//                projectileController.activate(position.x, position.y, -400, 0);
//            }
//            if (angle == 270) {
//                projectileController.activate(position.x, position.y, 0, -400);
//            }
//        } else {
//            if (angle == 0) {
//                projectileController.activate(position.x, position.y, 400, 10);
//                projectileController.activate(position.x, position.y, 400, -10);
//            }
//            if (angle == 90) {
//                projectileController.activate(position.x, position.y, 10, 400);
//                projectileController.activate(position.x, position.y, -10, 400);
//            }
//            if (angle == 180) {
//                projectileController.activate(position.x, position.y, -400, 10);
//                projectileController.activate(position.x, position.y, -400, -10);
//            }
//            if (angle == 270) {
//                projectileController.activate(position.x, position.y, 10, -400);
//                projectileController.activate(position.x, position.y, -10, -400);
//            }
//        }
//    }

    public void fire(boolean doubleFire, float angle) {
        if (!doubleFire){
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
            position.add(dir_up_down);
            if (position.y > 720) {
                position.y = 720;
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            angle = 270.f;
            position.sub(dir_up_down);
            if (position.y < 0) {
                position.y = 0;
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle = 180.f;
            position.sub(dir_left_right);
            if (position.x < 0) {
                position.x = 0;
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle = 0.f;
            position.add(dir_left_right);
            if (position.x > 1280) {
                position.x = 1280;
            }
            return;
        }
    }

    public void render(SpriteBatch batch) {
//        batch.draw(texture, position.x - 20, position.y - 20);
        batch.draw(texture, position.x - 20, position.y - 20, 20, 20, 40, 40, 1, 1, angle);
    }
}
