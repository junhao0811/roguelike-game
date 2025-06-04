package game.bossparts;

import game.effects.bossparteffects.AttackBonusEffect;
import game.effects.bossparteffects.HealingEffect;

public class Leaf extends BossPart {

    private static final int ATTACK_BONUS = 1;
    private static final int HEAL_AMOUNT = 5;

    /**
     * Constructor for Leaf
     */
    public Leaf() {
        super("Leaf", false);
        this.effects.add(new AttackBonusEffect(ATTACK_BONUS));
        this.effects.add(new HealingEffect(HEAL_AMOUNT));
    }

    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    @Override
    public BossPart grow() {
        return null; // A leaf cannot grow further
    }
    
}
