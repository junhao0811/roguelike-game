package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special healing potion given by Sellen at high affection
 * Extends ConsumableItem for common consumption behavior
 *
 * @author Chong Jun Hao
 */
public class HealingPotion extends ConsumableItem {
    
    private static final int HEAL_AMOUNT = 50;
    
    public HealingPotion() {
        super("Healing Potion", 'P', HEAL_AMOUNT, "restores");
    }
    
    /**
     * Applies the healing effect to the actor consuming the potion.
     *
     * @param actor the actor consuming the potion
     * @param map the current game map
     * @return an empty string as no message is needed
     */
    @Override
    protected String applyConsumptionEffect(Actor actor, GameMap map) {
        actor.heal(effectValue);
        return actor + " recovers by " + effectValue + " HP.";
    }
}