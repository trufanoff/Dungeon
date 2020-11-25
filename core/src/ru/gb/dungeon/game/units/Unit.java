package ru.gb.dungeon.game.units;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import ru.gb.dungeon.game.BattleCalc;
import ru.gb.dungeon.game.GameController;
import ru.gb.dungeon.game.GameMap;
import ru.gb.dungeon.game.Poolable;

public abstract class Unit implements Poolable {
    GameController gc;
    TextureRegion texture;
    TextureRegion textureHp;
    int attackRange;
    int damage;
    int defence;
    int hp;
    int hpMax;
    int cellX;
    int cellY;
    int turns;
    int maxTurns;
    float movementTime;
    float movementMax;
    int targetX;
    int targetY;
    private float innerTimer;

    public Unit(GameController gc, int cellX, int cellY, int hpMax) {
        this.gc = gc;
        this.cellX = cellX;
        this.cellY = cellY;
        this.targetX = cellX;
        this.targetY = cellY;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.damage = 2;
        this.defence = 1;
        this.maxTurns = 5;
        this.movementMax = 0.2f;
        this.attackRange = 2;
        this.innerTimer = MathUtils.random(1000.0f);
    }

    public void goTo(int argCellX, int argCellY) {
        if (!gc.getGameMap().isCellPassable(argCellX, argCellY) || !gc.getUnitController().isCellFree(argCellX, argCellY)) {
            return;
        }
        if (Math.abs(argCellX - cellX) + Math.abs(argCellY - cellY) == 1) {
            targetX = argCellX;
            targetY = argCellY;
        }
    }

    public void attack(Unit target) {
        target.takeDamage(BattleCalc.attack(this, target));
        this.takeDamage(BattleCalc.checkAttackCounter(this, target));
        turns--;

    }

    public boolean canIAttackThisTarget(Unit target) {
        return cellX - target.getCellX() == 0 && Math.abs(cellY - target.getCellY()) <= attackRange ||
                cellY - target.getCellY() == 0 && Math.abs(cellX - target.getCellX()) <= attackRange;
    }

    public void startTurn() {
        turns = maxTurns;
    }

    public int getTurns() {
        return turns;
    }

    public int getDefence() {
        return defence;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public boolean isActive() {
        return hp > 0;
    }

    public boolean takeDamage(int amount) {
        hp -= amount;
        if (hp <= 0) {
            gc.getUnitController().removeUnitAfterDeath(this);
        }
        return hp <= 0;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public void update(float dt) {
        innerTimer += dt;
        if (!isStayStill()) {
            movementTime += dt;
            if (movementTime > movementMax) {
                movementTime = 0;
                cellX = targetX;
                cellY = targetY;
                turns--;
            }
        }
    }

    public boolean isStayStill() {
        return targetX == cellX && targetY == cellY;
    }

    public boolean canIMakeAction() {
        return gc.getUnitController().isItMyTurn(this) && isStayStill() && turns > 0;
    }

    public boolean isCellEmpty(int cx, int cy) {
        return gc.getGameMap().isCellPassable(cx, cy) && gc.getUnitController().isCellFree(cx, cy);
    }

    public boolean amIBlocked() {
        return !(isCellEmpty(cellX + 1, cellY) || isCellEmpty(cellX - 1, cellY) || isCellEmpty(cellX, cellY + 1) || isCellEmpty(cellX, cellY - 1));
    }


    public void render(SpriteBatch batch, BitmapFont font18) {
        float px = cellX * GameMap.CELLS_SIZE;
        float py = cellY * GameMap.CELLS_SIZE;

        if (!isStayStill()) {
            px = cellX * GameMap.CELLS_SIZE + (targetX - cellX) * (movementTime / movementMax) * GameMap.CELLS_SIZE;
            py = cellY * GameMap.CELLS_SIZE + (targetY - cellY) * (movementTime / movementMax) * GameMap.CELLS_SIZE;
        }
        batch.draw(texture, px, py);
        batch.setColor(0.0f, 0.0f, 0.0f, 1.0f);

        float barX = px, barY = py + MathUtils.sin(innerTimer * 5.0f) * 2;
        batch.draw(textureHp, barX + 1, barY + 52 - 1, 58, 10);
        batch.setColor(0.7f, 0.0f, 0.0f, 1.0f);
        batch.draw(textureHp, barX + 2, barY + 52, 56, 8);
        batch.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        batch.draw(textureHp, barX + 2, barY + 52, (float) hp / hpMax * 56, 8);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        font18.draw(batch, "" + hp, barX, barY + 64, 60, 1, false);
    }
}
