package ru.gb.dungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.gb.dungeon.helpers.Assets;

public class ProjectileController extends ObjectPool<Projectile> {
    private TextureRegion projectileTexture;
    public ProjectileController() {
        projectileTexture = Assets.getInstance().getAtlas().findRegion("projectile");
    }

    public void activate(float x, float y, float vx, float vy) {
        getActiveElement().activate(x, y, vx, vy);
    }

    public void update(float dt) {
        for (Projectile p : getActiveList()) {
            p.update(dt);
        }
        checkPool();
    }

    public void render(SpriteBatch batch) {
        for (Projectile p : getActiveList()) {
            p.render(batch);
        }
    }

    @Override
    protected Projectile newObject() {
        return new Projectile(projectileTexture);
    }
}
