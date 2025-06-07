package game.effects.bossparteffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Effect;

/**
 * Effect that heals the boss
 * Applied when healing parts (like leaves) grow
 *
 * @author Ng Jun Xian
 */
public class HealingEffect extends Effect {

    private final int healAmount;

    /**
     * Constructor for HealingEffect
     *
     * @param target the target actor (boss)
     * @param healAmount the amount of healing to apply
     */
    public HealingEffect(Actor target, int healAmount) {
        super(target);
        this.healAmount = healAmount;
    }

    /**
     * Constructor with no specific target
     *
     * @param healAmount the amount of healing to apply
     */
    public HealingEffect(int healAmount) {
        super();
        this.healAmount = healAmount;
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        Actor target = getTarget(actor);
        target.heal(healAmount);
        return target + " is healed by " + healAmount + " HP.";
    }
}