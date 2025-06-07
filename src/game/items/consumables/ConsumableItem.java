package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.capabilities.Consumable;
import game.enums.Ability;

/**
 * Abstract base class for consumable items
 * Follows DRY principle by providing common consumable functionality
 * Template Method pattern for consumption behavior
 *
 * @author Ng Jun Xian
 */
public abstract class ConsumableItem extends Item implements Consumable {
    
    protected final int effectValue;
    protected final String effectType;
    
    /**
     * Constructor for ConsumableItem
     *
     * @param name the name of the consumable item
     * @param displayChar the display character
     * @param effectValue the value of the effect (amount to heal/restore)
     * @param effectType the type of effect (for display purposes)
     */
    protected ConsumableItem(String name, char displayChar, int effectValue, String effectType) {
        super(name, displayChar, true);
        this.effectValue = effectValue;
        this.effectType = effectType;
    }
    
    @Override
    public void consume(Actor actor, GameMap map) {
        // Remove item from actor's inventory
        actor.removeItemFromInventory(this);
        
        // Apply specific consumption effect
        applyConsumptionEffect(actor, map);
    }
    
    /**
     * Template method for specific consumption effects
     * Subclasses override this for their unique effects
     */
    protected abstract void applyConsumptionEffect(Actor actor, GameMap map);
    
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        
        // Add consume action if actor can consume items
        if (owner.hasCapability(Ability.CONSUME)) {
            actions.add(new ConsumeAction(this));
        }
        
        return actions;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + effectType + " " + effectValue + ")";
    }
}