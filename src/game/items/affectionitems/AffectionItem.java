package game.items.affectionitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.GiveAction;
import game.capabilities.Giveable;
import game.enums.Ability;
import game.enums.Status;

/**
 * Abstract base class for all items that can influence NPC affection
 * Implements the Affection interface and extends Item
 * Follows Template Method pattern for common affection item behavior
 *
 * @author Chong Jun Hao
 */
public abstract class AffectionItem extends Item implements Giveable {
    
    protected final int affectionValue;
    
    /**
     * Constructor for AffectionItem
     *
     * @param name the name of the affection item
     * @param displayChar the display character
     * @param affectionValue the affection value (positive or negative)
     */
    protected AffectionItem(String name, char displayChar, int affectionValue) {
        super(name, displayChar, true);
        this.affectionValue = affectionValue;
    }
    
    public String give(Actor giver, Actor target, GameMap map) {
        StringBuilder result = new StringBuilder();
        
        // Transfer the item from giver to target
        giver.removeItemFromInventory(this);
        target.addItemToInventory(this);
        
        // Apply the specific affection effect
        result.append(applySpecificEffect(giver, target, map));
        
        // Update NPC affection level if they support it
        if (target.hasCapability(Ability.RECEIVE)) {
            target.modifyAttribute(Status.AFFECTION, ActorAttributeOperations.INCREASE, affectionValue);
            result.append("\n").append(getAffectionMessage(target));
        }
        
        return result.toString();
    }
    
    /**
     * Template method for specific affection effects
     * Subclasses override this for unique behaviors
     */
    protected abstract String applySpecificEffect(Actor giver, Actor receiver, GameMap map);
    
    /**
     * Gets the appropriate affection message
     */
    private String getAffectionMessage(Actor target) {
        if (this.affectionValue > 0) {
            return target + " seems pleased with the gift! " + target + " affection level towards Farmer has been increased to level " + target.getAttribute(Status.AFFECTION) +  "!";
        } else {
            return target + " looks disgusted with the gift... " + target + " affection level towards Farmer has been decreased to level " + target.getAttribute(Status.AFFECTION) +  "!";
        }
    }
    
    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Example #1: a weapon can return an attacking action to the other actor.
     * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
     * Example #3: a food can return an action to feed the other actor.
     *
     * @param target the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor target, Location location){
        ActionList actions = new ActionList();

        if (target.hasCapability(Ability.RECEIVE)) {
            actions.add(new GiveAction(target, this));
        }

        return actions;
    }
}