package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Stamina potion given by Guts at high affection
 * Extends ConsumableItem for common consumption behavior
 */
public class StaminaTonic extends ConsumableItem {
    
    private static final int STAMINA_RESTORE = 50;
    
    public StaminaTonic() {
        super("Stamina Tonic", 'S', STAMINA_RESTORE, "restores");
    }
    
    @Override
    protected String applyConsumptionEffect(Actor actor, GameMap map) {
        if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.INCREASE, effectValue);
        }

        return "";
    }
}