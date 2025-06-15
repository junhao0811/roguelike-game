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
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
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
    
    /**
     * Consumes the item, applying its effect to the actor
     * This method is called when the item is consumed
     *
     * @param actor the actor consuming the item
     * @param map the game map
     * @return a description of the consumption effect
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        // Remove item from actor's inventory
        actor.removeItemFromInventory(this);
        
        // Apply specific consumption effect
        return applyConsumptionEffect(actor, map);
    }
    
    /**
     * Abstract method for specific consumption effects
     * Subclasses override this for their unique effects
     */
    protected abstract String applyConsumptionEffect(Actor actor, GameMap map);
    
    /**
     * Returns a list of actions that can be performed with this item
     * This method is called to populate the action menu for the item
     *
     * @param owner the actor owning the item
     * @param map the current game map
     * @return a list of allowable actions for this item
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        
        // Add consume action if actor can consume items
        if (owner.hasCapability(Ability.CONSUME)) {
            actions.add(new ConsumeAction(this));
        }
        
        return actions;
    }
    
    /**
     * Returns a string representation of the consumable item
     * This is used for displaying the item in menus and descriptions
     *
     * @return a string representation of the consumable item
     */
    @Override
    public String toString() {
        return super.toString() + " (" + effectType + " " + effectValue + ")";
    }
}