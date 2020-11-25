package ru.gb.dungeon.game;

import com.badlogic.gdx.math.MathUtils;
import ru.gb.dungeon.game.units.Unit;

public class BattleCalc {

    public static int attack(Unit attacker, Unit target) {
        int out = attacker.getDamage();
        out -= target.getDefence();
        if (out < 0) {
            out = 0;
        }
        return out;
    }

    public static int checkAttackCounter(Unit attacker, Unit target) {
        if (MathUtils.random() < 0.25f) {
            int amount = attack(target, attacker);
            return amount;
        }
        return 0;
    }
}
