package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special healing potion given by Sellen at high affection
 * Extends ConsumableItem for common consumption behavior
 */
public class HealingPotion extends ConsumableItem {
    
    private static final int HEAL_AMOUNT = 50;
    
    public HealingPotion() {
        super("Sellen's Heal Potion", 'P', HEAL_AMOUNT, "restores");
    }
    
    @Override
    protected String applyConsumptionEffect(Actor actor, GameMap map) {
        actor.heal(effectValue);
        return "";
    }
}