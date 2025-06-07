package game.bossparts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Effect;
import game.effects.EffectList;

/**
 * Abstract class representing a growth part of the Bed of Chaos boss
 * Follows Strategy pattern for different part behaviors
 *
 * @author Ng Jun Xian
 */
public abstract class BossPart {

    /**
     * The name of the part
     */
    protected final String name;

    /**
     * Whether this part can continue growing
     */
    protected final boolean canGrow;

    /**
     * The list of effects associated with this part
     */
    protected final EffectList effects;

    /**
     * The attack bonus provided by this part
     * This can be used to enhance the boss's attack capabilities
     */
    private final int attackBonus;

    /**
     * Constructor for BossGrowthPart
     *
     * @param name the name of the part
     * @param attackBonus the attack bonus this part provides
     * @param canGrow whether this part can continue growing
     */
    protected BossPart(String name, boolean canGrow, int attackBonus) {
        this.name = name;
        this.canGrow = canGrow;
        this.attackBonus = attackBonus;
        this.effects = new EffectList();
    }

    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    public abstract BossPart growPart();

    /**
     * Checks if this part can grow further
     *
     * @return true if this part can grow, false otherwise
     */
    public boolean canGrow() {
        return canGrow;
    }

    /**
     * Gets the effects of this part
     */
    public EffectList getEffects() {
        return effects;
    }

    /**
     * Gets the attack bonus this part provides
     */
    public int getAttackBonus() {
        return attackBonus;
    }

    /**
     * Applies all growth effects to the boss
     *
     * @param boss the boss actor
     * @param map the game map
     * @return description of applied effects
     */
    public String applyGrowEffect(Actor boss, GameMap map) {
        StringBuilder result = new StringBuilder();

        for (Effect effect : effects) {
            String effectResult = effect.applyEffect(boss, map);
            if (!effectResult.isEmpty()) {
                result.append(effectResult).append("\n");
            }
        }

        return result.toString().trim();
    }

    /**
     * Gets the name of the part
     *
     * @return the name of the part
     */
    @Override
    public String toString() {
        return name;
    }
}