package game.items.affectionitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.GiveAction;
import game.actors.npcs.NPC;
import game.capabilities.Receivable;
import game.enums.Ability;

/**
 * Abstract base class for all items that can influence NPC affection
 * Implements the Affection interface and extends Item
 * Follows Template Method pattern for common affection item behavior
 *
 * @author Chong Jun Hao
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
        this.addCapability(Ability.GIVE);
    }
    
    public int getAffectionValue() {
        return affectionValue;
    }
    
    public String applyAffection(Actor giver, NPC receiver, GameMap map) {
        StringBuilder result = new StringBuilder();
        
        // Apply the specific affection effect
        result.append(applySpecificEffect(giver, receiver, map));
        
        // Update NPC affection level if they support it
        if (receiver.hasCapability(Ability.RECEIVE)) {
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
        if (receiver.hasCapability(Ability.RECEIVE)) {
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
    
    public GiveAction getGiftAction(Receivable targetNPC) {
        return new GiveAction(targetNPC, this);
    }
    
    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Example #1: a weapon can return an attacking action to the other actor.
     * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
     * Example #3: a food can return an action to feed the other actor.
     *
     * @param targetNPC the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor targetNPC, Location location){
        ActionList actions = new ActionList();
        if (targetNPC.hasCapability(Ability.RECEIVE)) {
            actions.add(new GiveAction((Receivable) targetNPC, this));
        }
        return actions;
    }
}