package game.bossparts;

import game.effects.bossparteffects.HealingEffect;

/**
 * Class representing a Leaf part of the Bed of Chaos boss
 * This part does not grow further
 *
 * @author Ng Jun Xian
 */
public class Leaf extends BossPart {

    private static final int ATTACK_BONUS = 1;
    private static final int HEAL_AMOUNT = 5;

    /**
     * Constructor for Leaf
     */
    public Leaf() {
        super("Leaf", false, ATTACK_BONUS);
        this.effects.add(new HealingEffect(HEAL_AMOUNT));
    }

    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    @Override
    public BossPart growPart() {
        return null; // A leaf cannot grow further
    }
}
