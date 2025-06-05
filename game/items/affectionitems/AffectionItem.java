package game.items.affectionitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Giftable;
import game.enums.Ability;
import game.actors.npcs.NPC;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Abstract base class for all items that can influence NPC affection
 * Implements the Affection interface and extends Item
 * Follows Template Method pattern for common affection item behavior
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public abstract class AffectionItem extends Item {
    
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
        this.addCapability(Ability.CAN_AFFECT);
    }
    
    public int getAffectionValue() {
        return affectionValue;
    }
    
    // @Override
    // public String gift(NPC receiver, Actor giver, GameMap map) {
    //     if (receiver.hasCapability(Ability.CAN_BE_AFFECTED)) {
    //         giver.removeItemFromInventory(this);
    //         receiver.addItemToInventory(this);
    //         return applyAffection(giver, receiver, map);
    //     } 
    //     return giver + " cannot affect " + receiver + " with this item.";
    // }

    public String applyAffection(Actor giver, NPC receiver, GameMap map) {
        StringBuilder result = new StringBuilder();
        
        // Apply the specific affection effect
        result.append(applySpecificEffect(giver, receiver, map));
        
        // Update NPC affection level if they support it
        if (receiver.hasCapability(Ability.CAN_BE_AFFECTED)) {
            updateNPCAffection(receiver, giver);
            result.append("\n").append(getAffectionMessage(receiver));
        }
        
        return result.toString();
    }
    
    /**
     * Template method for specific affection effects
     * Subclasses override this for unique behaviors
     */
    protected abstract String applySpecificEffect(Actor giver, Actor receiver, GameMap map);

    /**
     * Updates the NPC's affection level towards the giver
     */
    private void updateNPCAffection(NPC receiver, Actor giver) {
        if (receiver.hasCapability(Ability.CAN_BE_AFFECTED)) {
            int newAffectionLevel = receiver.getAffectionLevel() + affectionValue;
            receiver.setAffectionLevel(newAffectionLevel);
        }
    }

    /**
     * Gets the appropriate affection message
     */
    private String getAffectionMessage(NPC receiver) {
        if (receiver.affectionIsPositive()) {
            return receiver + " seems pleased with the gift";
        } else {
            return receiver + " looks disgusted with the gift";
        }
    }

    public GiftAction getGiftAction(NPC target, String direction) {
        return new GiftAction(target, this, direction);
    }

    /**
	 * List of allowable actions that the item allows its owner do to other actor.
	 * Example #1: a weapon can return an attacking action to the other actor.
	 * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
	 * Example #3: a food can return an action to feed the other actor.
	 * 
	 * @param otherActor the other actor
	 * @param location the location of the other actor
	 * @return an unmodifiable list of Actions
	 */
    @Override
	public ActionList allowableActions(Actor otherActor, Location location){
		ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.CAN_BE_AFFECTED)) {
            actions.add(this.getGiftAction(otherActor, location));
        }
        return actions;
	}
}
    
    