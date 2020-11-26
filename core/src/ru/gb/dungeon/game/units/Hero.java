package ru.gb.dungeon.game.units;

import com.badlogic.gdx.Gdx;
import ru.gb.dungeon.game.GameController;
import ru.gb.dungeon.helpers.Assets;

public class Hero extends Unit {
    private String name;
    private int coins;

    public Hero(GameController gc) {
        super(gc, 1, 1, 10);
        this.name = "Sir Mullih";
        this.hpMax = 100;
        this.hp = hpMax;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.coins = 0;
        this.alpha = 0.2f;
    }

    public int getCoins() {
        return coins;
    }

    public String getName() {
        return name;
    }

    public void update(float dt) {
        super.update(dt);
        if (Gdx.input.justTouched() && canIMakeAction()) {
            Monster m = gc.getUnitController().getMonsterController().getMonsterInCell(gc.getCursorX(), gc.getCursorY());
            if (m != null && canIAttackThisTarget(m)) {
                attack(m);
            } else {
                goTo(gc.getCursorX(), gc.getCursorY());
            }
        }
        if (this.getHp() < 100) {
            alpha = 1.0f;
        }

    }
}
