package game.bossparts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.EffectList;
import game.effects.Effect;

/**
 * Abstract class representing a growth part of the Bed of Chaos boss
 * Follows Strategy pattern for different part behaviors
 *
 */
public abstract class BossPart {
    
    protected final String name;
    protected final boolean canGrow;
    protected final EffectList effects;
    
    /**
     * Constructor for BossGrowthPart
     *
     * @param name the name of the part
     * @param attackBonus the attack bonus this part provides
     * @param healAmount the heal amount this part provides when grown
     * @param canGrow whether this part can continue growing
     */
    protected BossPart(String name, boolean canGrow) {
        this.name = name;
        this.canGrow = canGrow;
        this.effects = new EffectList();
    }
    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    public abstract BossPart grow();

    /**
     * Gets the effects of this part
     */
    public EffectList getEffects() {
        return effects;
    }

    /**
     * Applies all growth effects to the boss
     *
     * @param boss the boss actor
     * @param map the game map
     * @return description of applied effects
     */
    public String applyBranchEffect(Actor boss, GameMap map) {
        StringBuilder result = new StringBuilder();
        
        for (Effect effect : effects) {
            String effectResult = effect.applyEffect(boss, map);
            if (!effectResult.isEmpty()) {
                result.append(effectResult).append("\n");
            }
        }
        
        return result.toString().trim();
    }

    @Override
    public String toString() {
        return name;
    }
}