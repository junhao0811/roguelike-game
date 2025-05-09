package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Edible;

/**
 * An action for actors to eat edible items.
 * 
 * @author Ng Jun Xian
 */
public class EatAction extends Action {
    
    private final Item item;
    private final Edible edible;
    
    /**
     * Constructor for EatAction.
     * 
     * @param item The item to be eaten
     * @param edible The edible implementation for the item
     */
    public EatAction(Item item, Edible edible) {
        this.item = item;
        this.edible = edible;
    }
    
    /**
     * Executes the eat action. The actor consumes the item and 
     * the edible's effects are applied.
     * 
     * @param actor The actor eating the item
     * @param map The map the actor is on
     * @return A description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean success = edible.eaten(actor);
        if (success) {
            actor.removeItemFromInventory(item);
            return actor + " eats the " + item + ".";
        }
        return actor + " couldn't eat the " + item;
    }
    
    /**
     * Returns a description of this action suitable for displaying in a menu.
     * 
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + item;
    }
}