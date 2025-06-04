package game.bossparts;


import java.util.Random;

import game.effects.bossparteffects.AttackBonusEffect;

public class Branch extends BossPart {

    private Random random = new Random();
    private static final int ATTACK_BONUS = 3;

    /**
     * Constructor for Branch
     * 
     * @param name the name of the branch part
     * @param canGrow whether this branch can continue growing
     */
    public Branch() {
        super("Branch", true);
        this.effects.add(new AttackBonusEffect(ATTACK_BONUS));
    }

    public void applyGrowEffect() {
        
    }
    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    @Override
    public BossPart grow() {
        if (random.nextBoolean()) {
            return this;
        } else {
            return new Leaf();
        }
    }
    
}
